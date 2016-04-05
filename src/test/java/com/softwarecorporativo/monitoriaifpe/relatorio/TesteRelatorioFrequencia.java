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
import java.util.logging.Level;
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
