/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.monitoria;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.instituto.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.instituto.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Modalidade;
import javax.persistence.EntityTransaction;
import org.apache.log4j.Level;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Matheus Levi
 */
public class TesteMonitoria extends MonitoriaTestCase {

    @Test
    public void testeCadastrarMonitoria() {
        EntityTransaction transaction = null;
        try {
            transaction = super.entityManager.getTransaction();
            transaction.begin();

            Monitoria monitoria = new Monitoria();
            monitoria.setAluno(super.entityManager.find(Aluno.class, 1l));
            monitoria.setModalidade(Modalidade.BOLSISTA);
            monitoria.setDisciplina(super.entityManager.find(Disciplina.class, 1l));
            monitoria.setPeriodo(super.entityManager.find(Periodo.class, 1l));
            monitoria.setHabilitado(Boolean.TRUE);
            super.entityManager.persist(monitoria);

            transaction.commit();

            assertTrue(monitoria.getChavePrimaria() > 0);
        } catch (Exception e) {
            fail();
            if (transaction != null && transaction.isActive()) {
                logger.log(Level.FATAL, "Cancelando Transação com erro. Mensagem: " + e.getMessage());
                transaction.rollback();
                logger.info("Transação Cancelada.");
            }

        }
    }

}