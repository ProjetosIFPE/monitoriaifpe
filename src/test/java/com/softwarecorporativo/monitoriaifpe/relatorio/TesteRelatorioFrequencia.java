/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.relatorio;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.instituto.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.instituto.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.relatorio.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.relatorio.frequencia.RelatorioFrequencia;
import com.softwarecorporativo.monitoriaifpe.relatorio.semana.Semana;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Modalidade;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Situacao;
import java.util.Calendar;
import javax.persistence.EntityTransaction;
import org.apache.log4j.Level;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author EdmilsonS
 */
public class TesteRelatorioFrequencia extends MonitoriaTestCase {

    @Override
    public void setUp() {
        super.setUp();
        this.prepararCenarioInsercao();
    }

    
    @Test
    public void testeInserirRelatorioFrequencia() {
        EntityTransaction transaction = null;

        try {

            int index = 0;

            transaction = super.entityManager.getTransaction();
            transaction.begin();
      
            RelatorioFrequencia relatorio = montarObjetoRelatorioFrequencia();

            super.entityManager.persist(relatorio);

            assertTrue(relatorio.getChavePrimaria() > 0);

            entityManager.detach(relatorio);
            RelatorioFrequencia relatorioObtido = super.entityManager
                    .find(relatorio.getClass(), relatorio.getChavePrimaria());

            transaction.commit();

            assertEquals(relatorio, relatorioObtido);
            assertEquals(relatorio.getSemana(index), relatorioObtido.getSemana(index));
            assertEquals(relatorio.getSemana(index).getAtividade(index), relatorioObtido.getSemana(index).getAtividade(index));
            assertEquals(relatorio.getMonitoria(), relatorioObtido.getMonitoria());

        } catch (Exception e) {   
            fail();
            if (transaction != null && transaction.isActive()) {
                logger.log(Level.FATAL, "Cancelando Transação com erro. Mensagem: " + e.getMessage());
                transaction.rollback();
                logger.info("Transação Cancelada.");
            }
            
        }

    }

    private void prepararCenarioInsercao() {
        EntityTransaction transaction = null;
        try {
            transaction = super.entityManager.getTransaction();
            transaction.begin();
            super.entityManager.persist(montarObjetoMonitoria());
            transaction.commit();
        }catch(Exception e ) {
            if (transaction != null && transaction.isActive()) {
                logger.log(Level.FATAL, "Cancelando Transação com erro. Mensagem: " + e.getMessage());
                transaction.rollback();
                logger.info("Transação Cancelada.");
            }
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

    private Monitoria montarObjetoMonitoria() {
        Monitoria monitoria = new Monitoria();
        Aluno aluno = super.entityManager.find(Aluno.class, 1L);
        monitoria.setAluno(aluno);
        monitoria.setDisciplina(aluno.getDisciplina(0));
        monitoria.setModalidade(Modalidade.BOLSISTA);
        Periodo periodo = super.entityManager.find(Periodo.class, 1L);
        monitoria.setPeriodo(periodo);
        return monitoria;
    }

}
