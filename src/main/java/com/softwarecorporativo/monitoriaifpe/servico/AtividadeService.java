/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.modelo.documento.Documento;
import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.modelo.documento.relatorio.DiaDTO;
import com.softwarecorporativo.monitoriaifpe.modelo.documento.relatorio.RelatorioDTO;
import com.softwarecorporativo.monitoriaifpe.modelo.documento.relatorio.SemanaDTO;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.Turma;
import com.softwarecorporativo.monitoriaifpe.modelo.util.DataUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Edmilson Santana
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AtividadeService extends GenericService<Atividade> {

    public static final String RELATORIO_JASPER_ATIVIDADE = "relatorioFrequencia.jasper";

    public static final String RELATORIO_JASPER_BACKGROUND_IMAGE = "template-relatorio.jpg";

    @EJB
    private DocumentoService documentoService;

    @PermitAll
    @Override
    public Atividade salvar(Atividade entidadeNegocio) throws NegocioException {

        return super.salvar(entidadeNegocio);
    }

    @PermitAll
    @Override
    public void atualizar(Atividade entidadeNegocio) throws NegocioException {

        super.atualizar(entidadeNegocio);

    }

    @PermitAll
    @Override
    public void remover(Atividade entidadeNegocio) throws NegocioException {
        super.remover(entidadeNegocio);
    }

    @PermitAll
    public Documento obterRelatorioFrequencia(Monitoria monitoria, Date dataInicialMes, Date dataFinalMes) {
        Integer mes = DataUtil.getMonthOfDate(dataInicialMes, dataFinalMes);

        List<Atividade> atividades = this.consultarAtividadesMensaisDaMonitoria(monitoria, mes);
        List<RelatorioDTO> dadosRelatorio = converterAtividadesEmRelatorio(monitoria, atividades, mes);

        Map<String, Object> parametros = criarParametrosRelatorio();

        return documentoService.gerarDocumentoPDF(dadosRelatorio, parametros, RELATORIO_JASPER_ATIVIDADE);

    }

    private Map<String, Object> criarParametrosRelatorio() {
        Map<String, Object> parametros = new HashMap<>();
        String reportsPath = File.separatorChar + "reports" + File.separatorChar
                + "img" + File.separatorChar + RELATORIO_JASPER_BACKGROUND_IMAGE;
        URL reportsAbsolutePath = getClass().getClassLoader().getResource(reportsPath);
        parametros.put(DocumentoService.TEMPLATE_REPORT, reportsAbsolutePath);
        return parametros;
    }

    @PermitAll
    public List<RelatorioDTO> converterAtividadesEmRelatorio(Monitoria monitoria, List<Atividade> atividades, Integer mes) {

        ByteArrayInputStream assinatura = null;
        if (!Objects.isNull(monitoria.getAssinaturaOrientador())) {
            assinatura = new ByteArrayInputStream(
                    monitoria.getAssinaturaOrientador());
        }
        RelatorioDTO relatorio = new RelatorioDTO.RelatorioDTOBuilder()
                .setAno(monitoria.getAnoMonitoria())
                .setEdital(monitoria.getEditalMonitoria())
                .setDisciplina(monitoria.getDescricaoTurmaMonitoria())
                .setMatricula(monitoria.getMatriculaMonitor())
                .setNome(monitoria.getNomeMonitor())
                .setOrientador(monitoria.getNomeOrientador())
                .setAssinaturaOrientador(assinatura)
                .setMes(DataUtil.obterNomeMes(mes))
                .setCurso(monitoria.getDescricaoCursoMonitoria())
                .setAtividades(atividades)
                .setCargaHorariaAtividades(atividades)
                .build();

        List<RelatorioDTO> relatorios = new ArrayList<>();
        relatorios.add(relatorio);

        return relatorios;
    }

    @PermitAll
    public List<Atividade> consultarAtividadesDaMonitoria(Monitoria monitoria) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select a from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as a where a.monitoria = :paramMonitoria ");
        Query query = super.entityManager.createQuery(jpql.toString(), getClasseEntidade());
        query.setParameter("paramMonitoria", monitoria);
        return query.getResultList();
    }

    @PermitAll
    public List<Atividade> consultarAtividadesMensaisDaMonitoria(Monitoria monitoria, Integer mes) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select a from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as a where a.monitoria = :paramMonitoria ");
        jpql.append(" and FUNC('MONTH', a.dataInicio) = :paramMes  ");
        jpql.append(" order by a.dataInicio ");
        Query query = super.entityManager.createQuery(jpql.toString(), getClasseEntidade());
        query.setParameter("paramMonitoria", monitoria);
        query.setParameter("paramMes", mes);
        return query.getResultList();
    }

    @PermitAll
    public List<Atividade> consultarAtividadesMensaisDaMonitoria(Monitoria monitoria,
            Date dataInicialMes, Date dataFinalMes) {

        Integer mes = DataUtil.getMonthOfDate(dataInicialMes, dataFinalMes);
        System.out.println("Mes: " + mes);
        return this.consultarAtividadesMensaisDaMonitoria(monitoria, mes);
    }

    @PermitAll
    @Override
    public Boolean verificarExistencia(Atividade entidadeNegocio) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select a from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as a where a.monitoria = ?1 ");
        jpql.append(" and a.dataInicio = ?2 ");
        TypedQuery<Long> query = super.entityManager.createQuery(
                jpql.toString(), Long.class);
        query.setParameter(1, entidadeNegocio.getMonitoria());
        query.setParameter(2, entidadeNegocio.getDataInicio(), TemporalType.DATE);
        Long count = query.getSingleResult();
        return count > 0;
    }

    @PermitAll
    @Override
    public Atividade getEntidadeNegocio() {
        return new Atividade();
    }

    @PermitAll
    @Override
    public Class<Atividade> getClasseEntidade() {
        return Atividade.class;
    }

}
