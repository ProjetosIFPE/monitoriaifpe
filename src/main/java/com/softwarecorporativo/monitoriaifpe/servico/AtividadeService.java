/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.modelo.relatorio.frequencia.RelatorioDTO;
import com.softwarecorporativo.monitoriaifpe.modelo.util.RelatorioUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;
import net.sf.jasperreports.engine.JRException;
import org.apache.poi.ss.formula.CollaboratingWorkbooksEnvironment;

/**
 *
 * @author Edmilson Santana
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AtividadeService extends GenericService<Atividade> {

    private static final String RELATORIO_JASPER_ATIVIDADE = "relatorioFrequencia.jasper";

    @Override
    public Atividade getEntidadeNegocio() {
        return new Atividade();
    }

    @Override
    public Class<Atividade> getClasseEntidade() {
        return Atividade.class;
    }

    public byte[] obterRelatorioFrequencia(Monitoria monitoria, Integer mes) {
        List<Atividade> atividades = this.consultarAtividadesMensaisDaMonitoria(monitoria);
        List<RelatorioDTO> dadosRelatorio = converterAtividadesEmRelatorio(monitoria, atividades);
        try {
            return RelatorioUtil.gerarRelatorioPDF(dadosRelatorio,null, RELATORIO_JASPER_ATIVIDADE);
        } catch (JRException ex) {
            Logger.getLogger(AtividadeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<RelatorioDTO> converterAtividadesEmRelatorio(Monitoria monitoria, List<Atividade> atividades) {
        RelatorioDTO relatorioDTO = new RelatorioDTO();
        relatorioDTO.setAno(monitoria.getAnoMonitoria());
        relatorioDTO.setEdital(monitoria.getEditalMonitoria());

        /*StringBuilder descricaoAcumulada = new StringBuilder();
        StringBuilder observacaoAcumulada = new StringBuilder();
       
        for ( Atividade atividade : atividades ) {
            descricaoAcumulada.append(atividade.getDescricao());
            descricaoAcumulada.append(", ");
            observacaoAcumulada.append(atividade.getObservacoes());
            observacaoAcumulada.append(", ");
        }*/
        List<RelatorioDTO> relatorio = new ArrayList<>();
        relatorio.add(relatorioDTO);
        return relatorio;
    }

    public List<Atividade> consultarAtividadesMensaisDaMonitoria(Monitoria monitoria) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select a from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as a where a.monitoria = :paramMonitoria ");
        Query query = super.entityManager.createQuery(jpql.toString(), Atividade.class);
        query.setParameter("paramMonitoria", monitoria);
        return query.getResultList();
    }

}
