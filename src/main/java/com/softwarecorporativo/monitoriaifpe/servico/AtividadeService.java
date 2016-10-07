/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.modelo.grupo.Grupo;
import static com.softwarecorporativo.monitoriaifpe.modelo.grupo.Grupo.ALUNO;
import static com.softwarecorporativo.monitoriaifpe.modelo.grupo.Grupo.PROFESSOR;
import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.modelo.relatorio.frequencia.DiaDTO;
import com.softwarecorporativo.monitoriaifpe.modelo.relatorio.frequencia.RelatorioDTO;
import com.softwarecorporativo.monitoriaifpe.modelo.relatorio.frequencia.SemanaDTO;
import com.softwarecorporativo.monitoriaifpe.modelo.util.RelatorioUtil;
import com.softwarecorporativo.monitoriaifpe.modelo.util.Util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Edmilson Santana
 */
@DeclareRoles({PROFESSOR, ALUNO})
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AtividadeService extends GenericService<Atividade> {

    private static final String RELATORIO_JASPER_ATIVIDADE = "relatorioFrequencia.jasper";

    //@RolesAllowed({ALUNO})
    @PermitAll
    @Override
    public Atividade salvar(Atividade entidadeNegocio) throws NegocioException {

        return super.salvar(entidadeNegocio);
    }

    //@RolesAllowed({ALUNO})
    @PermitAll
    @Override
    public void atualizar(Atividade entidadeNegocio) throws NegocioException {

        super.atualizar(entidadeNegocio);

    }

   // @RolesAllowed({ALUNO})
    @PermitAll
    @Override
    public void remover(Atividade entidadeNegocio) throws NegocioException {
        super.remover(entidadeNegocio);
    }

    //@RolesAllowed({ALUNO, PROFESSOR})
    @PermitAll
    public byte[] obterRelatorioFrequencia(Monitoria monitoria, Date dataInicialMes, Date dataFinalMes) {
        //Integer mes = Util.getMonthOfDate(dataInicialMes, dataFinalMes);
        //List<Atividade> atividades = this.consultarAtividadesMensaisDaMonitoria(monitoria, mes);
       // List<RelatorioDTO> dadosRelatorio = converterAtividadesEmRelatorio(monitoria, atividades, mes);
        try {
            return RelatorioUtil.gerarRelatorioPDF(Collections.EMPTY_LIST, null, RELATORIO_JASPER_ATIVIDADE);
        } catch (JRException ex) {
            Logger.getLogger(AtividadeService.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    //@RolesAllowed({ALUNO, PROFESSOR})
    @PermitAll
    public List<RelatorioDTO> converterAtividadesEmRelatorio(Monitoria monitoria, List<Atividade> atividades, Integer mes) {

        RelatorioDTO relatorio = new RelatorioDTO();

        relatorio.setAno(monitoria.getAnoMonitoria());
        relatorio.setEdital(monitoria.getEditalMonitoria());
        relatorio.setDisciplina(monitoria.getDisciplina().getComponenteCurricular().getDescricao());
        relatorio.setMatricula(monitoria.getAluno().getMatricula());
        relatorio.setCurso(monitoria.getAluno().getCurso().getDescricao());
        relatorio.setNome(monitoria.getNomeMonitor());
        relatorio.setOrientador(monitoria.getNomeOrientador());
        relatorio.setMes(Util.obterNomeMes(mes));
        StringBuilder descricaoAcumulada = new StringBuilder();
        StringBuilder observacaoAcumulada = new StringBuilder();

        List<SemanaDTO> semanas = new ArrayList<>();

        List<DiaDTO> dias = new ArrayList<>();

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        SemanaDTO semana = new SemanaDTO();

        int quantidadeAtividades = 0;
        for (Atividade atividade : atividades) {

            DiaDTO dia = new DiaDTO();

            String horarioInicial = format.format(atividade.getDataInicio());
            String horarioFinal = format.format(atividade.getDataFim());

            dia.setHorario(horarioInicial + " - " + horarioFinal);
            dia.setData(atividade.getDataInicio());
            dias.add(dia);

            if (!StringUtils.isEmpty(atividade.getDescricao())) {
                descricaoAcumulada.append(atividade.getDescricao());
                descricaoAcumulada.append("; ");
            }
            if (!StringUtils.isEmpty(atividade.getObservacoes())) {
                observacaoAcumulada.append(atividade.getObservacoes());
                observacaoAcumulada.append("; ");
            }

            quantidadeAtividades += 1;
            if (((quantidadeAtividades % 5) == 0)
                    || quantidadeAtividades == atividades.size()) {
                semana.setDias(dias);
                semana.setDescricao(descricaoAcumulada.toString());
                semana.setObservacao(observacaoAcumulada.toString());
                dias = new ArrayList<>();
                semanas.add(semana);
                semana = new SemanaDTO();
                descricaoAcumulada.delete(0, descricaoAcumulada.capacity());
                observacaoAcumulada.delete(0, observacaoAcumulada.capacity());

            }
        }

        relatorio.setSemanas(semanas);

        List<RelatorioDTO> relatorios = new ArrayList<>();
        relatorios.add(relatorio);

        return relatorios;
    }

   // @RolesAllowed({ALUNO, PROFESSOR})
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

    //@RolesAllowed({ALUNO, PROFESSOR})
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

   // @RolesAllowed({ALUNO, PROFESSOR})
    @PermitAll
    public List<Atividade> consultarAtividadesMensaisDaMonitoria(Monitoria monitoria,
            Date dataInicialMes, Date dataFinalMes) {

        Integer mes = Util.getMonthOfDate(dataInicialMes, dataFinalMes);
        System.out.println("Mes: " + mes);
        return this.consultarAtividadesMensaisDaMonitoria(monitoria, mes);
    }

    @PermitAll
    @Override
    public Atividade verificarExistencia(Atividade entidadeNegocio) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select a from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as a where a.monitoria = ?1 ");
        jpql.append(" and a.dataInicio = ?2 ");
        TypedQuery<Atividade> query = super.entityManager.createQuery(jpql.toString(), getClasseEntidade());
        query.setParameter(1, entidadeNegocio.getMonitoria());
        query.setParameter(2, entidadeNegocio.getDataInicio(), TemporalType.DATE);
        return query.getSingleResult();
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
