/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.instituto.professor;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.instituto.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.util.Util;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Constantes;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author douglas
 */
public class TesteProfessor extends MonitoriaTestCase {

    @Test
    public void testePersistProfessor() {
        // DUVIDA
        LOGGER.log(Level.INFO, "Teste de Persistência Professor - {0}", name.getMethodName());
        Professor professor = montarObjetoProfessor();
        Disciplina disciplina = montarObjetoDisciplina();
        disciplina.setProfessor(professor);
        //professor.addDisciplina(disciplina);
        super.entityManager.persist(professor);
        super.entityManager.flush();
        super.entityManager.refresh(professor);
        assertNotNull(professor.getChavePrimaria());

    }

    @Test
    public void testeUpdateProfessor() {
        LOGGER.log(Level.INFO, "Teste de Atualização Sobrenome de um Professor - {1}", name.getMethodName());
        Professor professorBuscado = super.entityManager.find(Professor.class, 8L);
        String sobrenomeAntigo = professorBuscado.getSobrenome();
        professorBuscado.setSobrenome("Araujo");
        super.entityManager.merge(professorBuscado);
        super.entityManager.flush();
        super.entityManager.clear();
        professorBuscado = super.entityManager.find(Professor.class, 8L);
        assertEquals("Araujo", professorBuscado.getSobrenome());
    }

    @Test
    public void testeJPQLQuantidadeProfessor() {
        LOGGER.log(Level.INFO, "Teste de JPQL COUNT de Professor - {1}", name.getMethodName());
        TypedQuery<Long> query = super.entityManager.createQuery(
                "SELECT COUNT(p) FROM Professor p WHERE p.login IS NOT NULL", Long.class);
        Long resultado = query.getSingleResult();
        assertEquals(new Long(4), resultado);

    }

    @Test
    public void testeJPQLQuantidadeProfessorLike() {
        LOGGER.log(Level.INFO, "Teste de JPQL COUNT de Professor - {1}", name.getMethodName());
        TypedQuery<Long> query = super.entityManager.createQuery(
                "SELECT COUNT(p) FROM Professor p WHERE p.sobrenome LIKE :sobrenome", Long.class);
        query.setParameter("sobrenome", "F%");
        Long resultado = query.getSingleResult();
        assertEquals(new Long(2), resultado);

    }

    @Test
    public void testeJPQLProfessorPeloCurso() {
        LOGGER.log(Level.INFO, "Teste de JPQL Nome de Professor por curso - {3}", name.getMethodName());
        TypedQuery<String> query = super.entityManager.createQuery(
                "SELECT u.nome FROM Usuario u WHERE u.chavePrimaria IN (SELECT d.professor FROM Disciplina d WHERE d.curso = :curso)", String.class);
        Curso curso = super.entityManager.find(Curso.class, 1L);
        query.setParameter("curso", curso);
        List<String> resultado = query.getResultList();
        assertEquals(4, resultado.size());
    }

    @Test
    public void testeJPQLProfessorPelaDisciplina() {
        LOGGER.log(Level.INFO, "Teste de JPQL Nome do Professor de uma disciplina - {3}", name.getMethodName());
        TypedQuery<String> query = super.entityManager.createQuery(
                "SELECT u.nome FROM Usuario u WHERE u.chavePrimaria IN (SELECT d.professor FROM Disciplina d WHERE d.descricao = :nomeDisciplina)", String.class);
        query.setParameter("nomeDisciplina", "Engenharia De Requisitos");
        String resultado = query.getSingleResult();
        assertEquals("Renata", resultado);
    }

    @Test
    public void testeJPQLVerificarCONCATProfessores() {
        LOGGER.log(Level.INFO, "Teste de JPQL CONCAT professor - {3}", name.getMethodName());
        TypedQuery<String> query = super.entityManager.createQuery(
                "SELECT CONCAT(u.nome,u.sobrenome) FROM Usuario u WHERE u.chavePrimaria IN (SELECT d.professor FROM Disciplina d WHERE d.descricao = :nomeDisciplina)", String.class);
        query.setParameter("nomeDisciplina", "Teste de Software");
        String resultado = query.getSingleResult();
        assertEquals("RamideDantas", resultado);

    }
    
    private Professor montarObjetoProfessor() {
        Professor professor_criado = new Professor();

        professor_criado.setNome("Paulo");
        professor_criado.setSobrenome("Abadie");
        professor_criado.setEmail("PauloAbadie@gmail.com");
        professor_criado.setLogin("PauloAbadie");
        String password = Util.criptografarSenha("senha", "ssenha", Constantes.CONSTANTE_CRIPTOGRAFIA);
        professor_criado.setSenha(password);

        return professor_criado;
    }

    private Disciplina montarObjetoDisciplina() {
        Disciplina disciplina = new Disciplina();
        Curso curso = super.entityManager.find(Curso.class, 1L);
        disciplina.setCurso(curso);
        disciplina.setDescricao("Sistemas De Tempo Real");
        return disciplina;
    }

}
