/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.professor;

import com.softwarecorporativo.monitoriaifpe.funcionais.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.ComponenteCurricular;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.Turma;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import java.util.List;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author douglas
 */
@Ignore
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
        professorBuscado.setNome("Araujo");
        super.entityManager.merge(professorBuscado);
        super.entityManager.flush();
        super.entityManager.clear();
        professorBuscado = super.entityManager.find(Professor.class, 8L);
        assertEquals("Araujo", professorBuscado.getNome());
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
                "SELECT COUNT(p) FROM Professor p WHERE p.nome LIKE :nome", Long.class);
        query.setParameter("nome", "R%");
        Long resultado = query.getSingleResult();
        assertEquals(new Long(1), resultado);

    }

    @Test
    public void testeJPQLProfessorPeloCurso() {
        TypedQuery<String> query = super.entityManager.createQuery(
                "SELECT u.nome FROM Usuario u WHERE u.chavePrimaria IN (SELECT t.professor FROM Turma t JOIN t.componenteCurricular cc JOIN cc.curso curso WHERE curso  = :curso)", String.class);
        Curso curso = super.entityManager.find(Curso.class, 1L);
        query.setParameter("curso", curso);
        List<String> resultado = query.getResultList();
        assertEquals(4, resultado.size());
    }

    @Test
    public void testeJPQLProfessorPelaDisciplina() {
        TypedQuery<String> query = super.entityManager.createQuery(
                "SELECT u.nome FROM Usuario u WHERE u.chavePrimaria IN (SELECT t.professor FROM Turma t WHERE t.componenteCurricular.descricao = :nomeDisciplina)", String.class);
        query.setParameter("nomeDisciplina", "Engenharia De Requisitos");
        String resultado = query.getSingleResult();
        assertEquals("Renata", resultado);
    }

    
    private Professor montarObjetoProfessor() {
        Professor professor_criado = new Professor();

        professor_criado.setNome("Paulo");
        professor_criado.setEmail("PauloAbadie@gmail.com");
        professor_criado.setSenha("paulo123");
        professor_criado.setCpf("120.425.328-51");
        professor_criado.setSiape("987604321");
        professor_criado.addTurma(montarObjetoDisciplina());
        return professor_criado;
    }

    private Turma montarObjetoDisciplina() {
        Turma turma = new Turma();
        turma.ofertar();
        Curso curso = super.entityManager.find(Curso.class, 1L);
        ComponenteCurricular componenteCurricular = new ComponenteCurricular();
        componenteCurricular.setCurso(curso);
        componenteCurricular.setDescricao("Sistemas De Tempo Real");
        componenteCurricular.setCodigoComponenteCurricular("Z0");
        turma.setComponenteCurricular(componenteCurricular);
        return turma;
    }

}
