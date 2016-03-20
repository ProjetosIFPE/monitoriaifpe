/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.professor;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.instituto.professor.Professor;
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
public class TesteProfessor extends MonitoriaTestCase {

    @Test
    public void testePersistProfessor() {

        EntityTransaction transaction = null;
        
        try {
            transaction = super.entityManager.getTransaction();
            transaction.begin();
            
            Curso curso = montarObjetoCurso();
            super.entityManager.persist(curso);

            List<Professor> lista_de_professores;
            Disciplina disciplina = montarObjetoDisciplina();
            disciplina.setCurso(curso);
            super.entityManager.persist(disciplina);

            lista_de_professores = quantidadeProfessores();
            assertNotNull(lista_de_professores);

            int valor_pre_cadastro = lista_de_professores.size();

            Professor professor = montarObjetoProfessor();
            professor.addDisciplina(disciplina);

            super.entityManager.persist(professor);

            lista_de_professores = quantidadeProfessores();
            assertNotNull(lista_de_professores);

            int valor_pos_cadastro = lista_de_professores.size();

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

    private Professor montarObjetoProfessor() {
        Professor professor_criado = new Professor();

        professor_criado.setNome("Marcos");
        professor_criado.setSobrenome("Costa");
        professor_criado.setEmail("marcos_andre@gmail.com");
        professor_criado.setLogin("MarcosCosta");
        String password = Util.criptografarSenha("senha", "ssenha", Constantes.CONSTANTE_CRIPTOGRAFIA);
        professor_criado.setSenha(password);

        return professor_criado;
    }

    private Disciplina montarObjetoDisciplina() {
        Disciplina disciplina = new Disciplina();
        disciplina.setDescricao("Desenvolvimento WEB");
        return disciplina;
    }

    private Curso montarObjetoCurso() {
        Curso curso = new Curso();
        curso.setDescricao("TADS");
        curso.setModalidade(Grau.SUPERIOR);
        return curso;
    }

    private List<Professor> quantidadeProfessores() {
        Query query = super.entityManager.createQuery(" select u from Professor u ");
        List<Professor> lista_professores = query.getResultList();

        return lista_professores;
    }
}
