/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.disciplina;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.instituto.professor.Professor;
import javax.persistence.EntityTransaction;
import org.apache.log4j.Level;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Matheus Levi
 */
public class TesteDisciplina extends MonitoriaTestCase {

    @Test
    public void testeCadastrarDisciplina() {
        EntityTransaction transaction = null;
        try {
            transaction = super.entityManager.getTransaction();
            transaction.begin();

            Disciplina disciplina = new Disciplina();
            Professor professor = super.entityManager.find(Professor.class, 2l);
            Curso curso = super.entityManager.find(Curso.class, 1l);
            disciplina.setProfessor(professor);
            disciplina.setCurso(curso);
            disciplina.setDescricao("Desenvolvimento de Sofware Corporativo");
            super.entityManager.persist(disciplina);

            transaction.commit();

            assertTrue(disciplina.getChavePrimaria() > 0);
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
