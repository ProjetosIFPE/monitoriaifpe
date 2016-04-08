/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.relatorio;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.instituto.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.relatorio.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.relatorio.frequencia.RelatorioFrequencia;
import com.softwarecorporativo.monitoriaifpe.relatorio.semana.Semana;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Situacao;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.TypedQuery;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author EdmilsonS
 */
public class TesteRelatorioFrequencia extends MonitoriaTestCase {

    @Test
    public void testeInserirRelatorioFrequencia() {

        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());
        try {
            RelatorioFrequencia relatorio = montarObjetoRelatorioFrequencia();
            super.entityManager.persist(relatorio);
            super.entityTransaction.commit();
            assertTrue(Boolean.TRUE);
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                entityTransaction.rollback();
            }
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testeAlterarRelatorioFrequencia() {

    }

    @Test
    public void testeConsultarRelatoriosAprovados() {

        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());

        TypedQuery<RelatorioFrequencia> query = super.entityManager.createQuery(
                "SELECT r FROM RelatorioFrequencia r WHERE r.situacao = :situacao",
                RelatorioFrequencia.class);
        query.setParameter("situacao", Situacao.APROVADO);

        int quantidadeEsperada = 5;
        List<RelatorioFrequencia> relatoriosAprovados = query.getResultList();
        Assert.assertEquals(quantidadeEsperada, relatoriosAprovados.size());

        for (RelatorioFrequencia relatorio : relatoriosAprovados) {
            relatorio.getSituacao().equals(Situacao.APROVADO);
        }
    }

    @Test
    public void testeConsultarRelatoriosEmEspera() {

        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());

        TypedQuery<RelatorioFrequencia> query = super.entityManager.createQuery(
                "SELECT r FROM RelatorioFrequencia r WHERE r.situacao = :situacao",
                RelatorioFrequencia.class);
        query.setParameter("situacao", Situacao.ESPERA);

        int quantidadeEsperada = 1;
        List<RelatorioFrequencia> relatoriosAprovados = query.getResultList();
        Assert.assertEquals(quantidadeEsperada, relatoriosAprovados.size());

        for (RelatorioFrequencia relatorio : relatoriosAprovados) {
            relatorio.getSituacao().equals(Situacao.ESPERA);
        }
    }

    @Test
    public void testeConsultarRelatoriosPorMonitor() {
        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());
        //consulta de relatorios por monitor ordenado por mes
    }

    @Test
    public void testeConsultarRelatoriosPorMes() {
        //consulta de relatorios 
    }

    @Test
    public void testeConsultaRelatoriosComSemana() {
        //verifica se uma colecao de semanas do relatorio está vazia 
    }

    @Test
    public void testeConsultaRelatorios() {
        //
    }

    private RelatorioFrequencia montarObjetoRelatorioFrequencia() {
        RelatorioFrequencia relatorio = new RelatorioFrequencia();
        relatorio.setMes(01);
        relatorio.setSituacao(Situacao.APROVADO);
        relatorio.addSemana(montarObjetoSemana());
        Monitoria monitoria = super.entityManager.find(Monitoria.class, 1L);
        relatorio.setMonitoria(monitoria);
        return relatorio;
    }

    private Semana montarObjetoSemana() {
        Semana semana = new Semana();
        semana.setDescricao("Semana de Teste");
        semana.setObservacoes("Observações de Teste");
        semana.addAtividade(montarObjetoAtividade());
        return semana;
    }

    private Atividade montarObjetoAtividade() {
        Atividade atividade = new Atividade();
        atividade.setData(Calendar.getInstance().getTime());
        atividade.setHorarioEntrada(Calendar.getInstance().getTime());
        atividade.setHorarioSaida(Calendar.getInstance().getTime());
        return atividade;
    }

}
