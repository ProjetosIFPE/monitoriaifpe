/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import static com.softwarecorporativo.monitoriaifpe.modelo.grupo.Grupo.ALUNO;
import static com.softwarecorporativo.monitoriaifpe.modelo.grupo.Grupo.PROFESSOR;
import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.modelo.relatorio.frequencia.Dia;
import com.softwarecorporativo.monitoriaifpe.modelo.relatorio.frequencia.Relatorio;
import com.softwarecorporativo.monitoriaifpe.modelo.relatorio.frequencia.Semana;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.Turma;
import com.softwarecorporativo.monitoriaifpe.modelo.util.RelatorioUtil;
import com.softwarecorporativo.monitoriaifpe.modelo.util.Util;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
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

    public static final String RELATORIO_JASPER_ATIVIDADE = "relatorioFrequencia.jasper";

    public static final String RELATORIO_JASPER_BACKGROUND_IMAGE = "template-relatorio.jpg";

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
        Integer mes = Util.getMonthOfDate(dataInicialMes, dataFinalMes);
        List<Atividade> atividades = this.consultarAtividadesMensaisDaMonitoria(monitoria, mes);
        List<Relatorio> dadosRelatorio = converterAtividadesEmRelatorio(monitoria, atividades, mes);
        try {
            Map<String, Object> parametros = new HashMap<>();
            String reportsPath = File.separatorChar + "reports" + File.separatorChar
                    + "img" + File.separatorChar + RELATORIO_JASPER_BACKGROUND_IMAGE;
            URL reportsAbsolutePath = getClass().getClassLoader().getResource(reportsPath);
            parametros.put(RelatorioUtil.TEMPLATE_REPORT, reportsAbsolutePath);
            return RelatorioUtil.gerarRelatorioPDF(dadosRelatorio, parametros, RELATORIO_JASPER_ATIVIDADE);
        } catch (JRException ex) {
            Logger.getLogger(AtividadeService.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    //@RolesAllowed({ALUNO, PROFESSOR})
    @PermitAll
    public List<Relatorio> converterAtividadesEmRelatorio(Monitoria monitoria, List<Atividade> atividades, Integer mes) {

        Relatorio relatorio = new Relatorio();

        Turma turma = monitoria.getTurma();

        relatorio.setAno(monitoria.getAnoMonitoria());
        relatorio.setEdital(monitoria.getEditalMonitoria());
        relatorio.setDisciplina(monitoria.getTurma().getComponenteCurricular().getDescricao());
        relatorio.setMatricula(monitoria.getAluno().getMatricula());
        relatorio.setCurso(monitoria.getAluno().getCurso().getDescricao());
        relatorio.setNome(monitoria.getNomeMonitor());
        relatorio.setOrientador(monitoria.getNomeOrientador());
        relatorio.setMes(Util.obterNomeMes(mes));

        ByteArrayInputStream assinatura = new ByteArrayInputStream(
                monitoria.getAssinaturaOrientador());
        relatorio.setAssinaturaOrientador(assinatura);

        StringBuilder descricaoAcumulada = new StringBuilder();
        StringBuilder observacaoAcumulada = new StringBuilder();

        List<Semana> semanas = new ArrayList<>();

        List<Dia> dias = new ArrayList<>();

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        Semana semana = new Semana();

        int quantidadeAtividades = 0;
        for (Atividade atividade : atividades) {

            Dia dia = new Dia();

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
                semana = new Semana();
                descricaoAcumulada.delete(0, descricaoAcumulada.capacity());
                observacaoAcumulada.delete(0, observacaoAcumulada.capacity());

            }
        }

        relatorio.setSemanas(semanas);

        List<Relatorio> relatorios = new ArrayList<>();
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
