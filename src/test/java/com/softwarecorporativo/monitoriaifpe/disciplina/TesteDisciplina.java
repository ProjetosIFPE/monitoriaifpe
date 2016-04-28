/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.disciplina;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.professor.Professor;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.TypedQuery;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Matheus Levi
 */
public class TesteDisciplina extends MonitoriaTestCase {

    @Test
    public void testeCadastrarDisciplina() {
        Disciplina disciplina = this.montarObjetoDisciplina();
        super.entityManager.persist(disciplina);
        super.entityManager.flush();
        super.entityManager.refresh(disciplina);
        assertNotNull(disciplina.getChavePrimaria());
    }

    @Test
    public void testAlterarDisciplina() {
        Disciplina disciplinaObtida = super.entityManager.find(Disciplina.class, 2L);
        disciplinaObtida.setProfessor(super.entityManager.find(Professor.class, 6L));
        super.entityManager.merge(disciplinaObtida);
        super.entityManager.flush();
        super.entityManager.clear();
        Disciplina disciplinaAlterada = super.entityManager.find(Disciplina.class, 2L);
        assertEquals(disciplinaObtida.getProfessor().getChavePrimaria(),
                disciplinaAlterada.getProfessor().getChavePrimaria());

    }

    @Test
    public void testeRemoverDisciplina() {
        Disciplina disciplina = super.entityManager.find(Disciplina.class, 3L);
        super.entityManager.remove(disciplina);
        super.entityManager.flush();
        assertNull(super.entityManager.find(Disciplina.class, 3L));
    }

    @Test
    public void consultarDisciplinaPorProfessor() {
        TypedQuery<Disciplina> query = super.entityManager.createQuery(
                "SELECT d FROM Disciplina d WHERE d.professor.chavePrimaria = :professor",
                Disciplina.class
        );
        query.setParameter("professor", 2l);

        List<Disciplina> disciplina = query.getResultList();
        assertEquals("Software Corporativo", disciplina.get(0).getDescricao());
    }

    @Test
    public void consultarQuantidadeDisciplinas() {
        TypedQuery<Disciplina> query = super.entityManager.createQuery("SELECT d FROM Disciplina d", Disciplina.class);
        List<Disciplina> disciplina = query.getResultList();
        assertEquals(5, disciplina.size());

        for (Disciplina disc : disciplina) {
            LOGGER.info(disc.getDescricao());
        }

    }

    @Test
    public void consultarDisciplinaPorNome() {
        TypedQuery<Disciplina> query = super.entityManager.createQuery("SELECT d FROM Disciplina d"
                + " WHERE d.descricao = :nomeDisciplina", Disciplina.class);

        query.setParameter("nomeDisciplina", "Teste de Software");
        Disciplina disciplina = query.getSingleResult();

        assertNotNull(disciplina);
    }

    @Test
    public void testeConsultarDisciplinasPorCurso() {
        TypedQuery<Disciplina> query = super.entityManager.createQuery(
                "SELECT d FROM Disciplina d WHERE d.curso = :curso", Disciplina.class);
        Curso cursoEsperado = super.entityManager.find(Curso.class, 1L);
        int quantidadeEsperada = 4;
        query.setParameter("curso", cursoEsperado);
        List<Disciplina> disciplinas = query.getResultList();
        assertEquals(quantidadeEsperada, disciplinas.size());
        for (Disciplina disciplina : disciplinas) {
            assertEquals(cursoEsperado, disciplina.getCurso());
        }
    }

    public Disciplina montarObjetoDisciplina() {
        Disciplina disciplina = new Disciplina();
        Professor professor = super.entityManager.find(Professor.class, 2l);
        Curso curso = super.entityManager.find(Curso.class, 1l);
        disciplina.setProfessor(professor);
        disciplina.setCurso(curso);
        disciplina.setDescricao("Desenvolvimento de Sofware Corporativo");
        return disciplina;
    }
}
