/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.aluno;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.util.Util;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Constantes;
import java.util.logging.Level;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author douglas
 */
public class TesteAluno extends MonitoriaTestCase {

    @Test
    public void testePersistAluno() {

        LOGGER.log(Level.INFO, "Teste de Persistência Aluno - {0}", name.getMethodName());
        try {
            Aluno aluno = montarObjetoAluno();
            super.entityManager.persist(aluno);
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
    public void testeUpdateAluno() {
        LOGGER.log(Level.INFO, "Teste de Atualização Email de um Aluno - {1}", name.getMethodName());
        try {
            //super.entityTransaction.begin();
            Aluno alunoBuscado = super.entityManager.find(Aluno.class, 1L);
            String emailAntigo = alunoBuscado.getEmail();
            alunoBuscado.setEmail("edmilson@hotmail.com");
            super.entityTransaction.commit();
            assertEquals(true, true);
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                entityTransaction.rollback();
            }
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testeDeleteAluno() {
        LOGGER.log(Level.INFO, "Teste de Remoção de um Aluno - {2}", name.getMethodName());
        try {
            Aluno alunoBuscado = super.entityManager.find(Aluno.class, 3L);
            alunoBuscado = super.entityManager.merge(alunoBuscado);
            super.entityManager.remove(alunoBuscado);
            super.entityTransaction.commit();
            assertEquals(true, true);
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                entityTransaction.rollback();
            }
            Assert.fail(e.getMessage());
        }
    }

    private Aluno montarObjetoAluno() {
        Aluno aluno_criado = new Aluno();
        aluno_criado.setNome("Fulano");
        aluno_criado.setSobrenome("Silva");
        aluno_criado.setEmail("fulano@gmail.com");
        aluno_criado.setLogin("fulano21");
        aluno_criado.setMatricula("20142Y6-RC2222");
        String password = Util.criptografarSenha("123", "123", Constantes.CONSTANTE_CRIPTOGRAFIA);
        aluno_criado.setSenha(password);
        Curso curso = super.entityManager.find(Curso.class, 1L);
        aluno_criado.setCurso(curso);
        Disciplina disciplina = super.entityManager.find(Disciplina.class, 1L);
        aluno_criado.addDisciplina(disciplina);

        return aluno_criado;
    }

}
