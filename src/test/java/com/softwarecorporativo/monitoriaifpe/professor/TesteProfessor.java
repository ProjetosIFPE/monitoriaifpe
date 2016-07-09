/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.funcionais.professor;

import com.softwarecorporativo.monitoriaifpe.funcionais.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.ComponenteCurricular;
import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import java.util.List;
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
 
        Professor professor = montarObjetoProfessor();
        super.entityManager.persist(professor);
        super.entityManager.flush();
        super.entityManager.refresh(professor);
        assertNotNull(professor.getChavePrimaria());

    }

    @Test
    public void testeUpdateProfessor() {
        Professor professorBuscado = super.entityManager.find(Professor.class, 8L);
        professorBuscado.setSobrenome("Araujo");
        super.entityManager.merge(professorBuscado);
        super.entityManager.flush();
        super.entityManager.clear();
        professorBuscado = super.entityManager.find(Professor.class, 8L);
        assertEquals("Araujo", professorBuscado.getSobrenome());
    }

    @Test
    public void testeJPQLQuantidadeProfessor() {
        TypedQuery<Long> query = super.entityManager.createQuery(
                "SELECT COUNT(p) FROM Professor p WHERE p.login IS NOT NULL", Long.class);
        Long resultado = query.getSingleResult();
        assertEquals(new Long(5), resultado);

    }

    @Test
    public void testeJPQLQuantidadeProfessorLike() {
        TypedQuery<Long> query = super.entityManager.createQuery(
                "SELECT COUNT(p) FROM Professor p WHERE p.sobrenome LIKE :sobrenome", Long.class);
        query.setParameter("sobrenome", "F%");
        Long resultado = query.getSingleResult();
        assertEquals(new Long(2), resultado);

    }

    @Test
    public void testeJPQLProfessorPeloCurso() {
        TypedQuery<String> query = super.entityManager.createQuery(
                "SELECT u.nome FROM Usuario u WHERE u.chavePrimaria IN (SELECT d.professor FROM Disciplina d JOIN d.componenteCurricular cc JOIN cc.curso curso WHERE curso  = :curso)", String.class);
        Curso curso = super.entityManager.find(Curso.class, 1L);
        query.setParameter("curso", curso);
        List<String> resultado = query.getResultList();
        assertEquals(4, resultado.size());
    }

    @Test
    public void testeJPQLProfessorPelaDisciplina() {
        TypedQuery<String> query = super.entityManager.createQuery(
                "SELECT u.nome FROM Usuario u WHERE u.chavePrimaria IN (SELECT d.professor FROM Disciplina d WHERE d.componenteCurricular.descricao = :nomeDisciplina)", String.class);
        query.setParameter("nomeDisciplina", "Engenharia De Requisitos");
        String resultado = query.getSingleResult();
        assertEquals("Renata", resultado);
    }

    @Test
    public void testeJPQLVerificarCONCATProfessores() {
        TypedQuery<String> query = super.entityManager.createQuery(
                "SELECT CONCAT(u.nome,u.sobrenome) FROM Usuario u WHERE u.chavePrimaria IN (SELECT d.professor FROM Disciplina d JOIN d.componenteCurricular cc WHERE cc.descricao = :nomeDisciplina)", String.class);
        query.setParameter("nomeDisciplina", "Teste de Software");
        String resultado = query.getSingleResult();
        assertEquals("RamideDantas", resultado);

    }
    
    private Professor montarObjetoProfessor() {
        Professor professor_criado = new Professor();

        professor_criado.setNome("Paulo");
        professor_criado.setSobrenome("Abadie");
        professor_criado.setEmail("PauloAbadie@gmail.com");
        professor_criado.setLogin("pauloabadie");
        professor_criado.setSenha("paulo123");
        professor_criado.addDisciplina(montarObjetoDisciplina());
        return professor_criado;
    }

    private Disciplina montarObjetoDisciplina() {
        Disciplina disciplina = new Disciplina();
        Curso curso = super.entityManager.find(Curso.class, 1L);
        ComponenteCurricular componenteCurricular = new ComponenteCurricular();
        componenteCurricular.setCurso(curso);
        componenteCurricular.setDescricao("Sistemas De Tempo Real");
        disciplina.setComponenteCurricular(componenteCurricular);
        return disciplina;
    }

}
