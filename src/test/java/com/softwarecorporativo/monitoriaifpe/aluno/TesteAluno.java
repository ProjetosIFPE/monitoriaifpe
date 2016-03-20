/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.aluno;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.util.Util;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Constantes;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Grau;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.apache.log4j.Level;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author douglas
 */
public class TesteAluno extends MonitoriaTestCase {

    @Test
    public void testePersistAluno() {

        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            List<Aluno> lista_de_alunos;
            Curso curso = montarObjetoCurso();

            super.entityManager.persist(curso);

            lista_de_alunos = quantidadeAlunos();
            assertNotNull(lista_de_alunos);

            int valor_pre_cadastro = lista_de_alunos.size();

            Aluno aluno = montarObjetoAluno();
            aluno.setCurso(curso);

            super.entityManager.persist(aluno);

            lista_de_alunos = quantidadeAlunos();
            assertNotNull(lista_de_alunos);

            int valor_pos_cadastro = lista_de_alunos.size();

            transaction.commit();

            assertEquals(valor_pre_cadastro + 1, valor_pos_cadastro);
        } catch (Exception e) {
            fail();
            if (transaction != null && transaction.isActive()) {
                logger.log(Level.FATAL, "Cancelando Transação com erro. Mensagem: " + e.getMessage());
                transaction.rollback();
                logger.info("Transação Cancelada.");
            }

        }

    }

    private Aluno montarObjetoAluno() {
        Aluno aluno_criado = new Aluno();
        aluno_criado.setNome("Douglas");
        aluno_criado.setSobrenome("Albuquerque");
        aluno_criado.setEmail("douglasalbuquerque@gmail.com");
        aluno_criado.setLogin("doug21");
        aluno_criado.setMatricula("20142Y6-RC2222");
        String password = Util.criptografarSenha("sport", "sport", Constantes.CONSTANTE_CRIPTOGRAFIA);
        aluno_criado.setSenha(password);

        return aluno_criado;
    }

    private List<Aluno> quantidadeAlunos() {
        Query query = super.entityManager.createQuery(" select u from Aluno u ");
        List<Aluno> lista_alunos = query.getResultList();

        return lista_alunos;
    }

    private Curso montarObjetoCurso() {
        Curso curso = new Curso();
        curso.setDescricao("Tec Edificacoes");
        curso.setModalidade(Grau.TECNICO);
        return curso;
    }

}
