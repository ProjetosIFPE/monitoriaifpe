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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.junit.Assert;
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

    private Aluno montarObjetoAluno() {
        Aluno aluno_criado = new Aluno();
        aluno_criado.setNome("Fulano");
        aluno_criado.setSobrenome("Santana");
        aluno_criado.setEmail("douglasalbuquerque@gmail.com");
        aluno_criado.setLogin("doug21");
        aluno_criado.setMatricula("20142Y6-RC2222");
        String password = Util.criptografarSenha("sport", "sport", Constantes.CONSTANTE_CRIPTOGRAFIA);
        aluno_criado.setSenha(password);
        Curso curso = super.entityManager.find(Curso.class, 1L);
        aluno_criado.setCurso(curso);
        Disciplina disciplina = super.entityManager.find(Disciplina.class, 1L);
        aluno_criado.addDisciplina(disciplina);

        return aluno_criado;
    }

}
