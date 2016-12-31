/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.turma;

import com.softwarecorporativo.monitoriaifpe.funcionais.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.ComponenteCurricular;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.Turma;
import com.softwarecorporativo.monitoriaifpe.modelo.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import java.util.List;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Matheus Levi
 */
public class TesteTurma extends MonitoriaTestCase {

    @Test
    public void testeCadastrarTurma() {
        Turma turma = this.montarObjetoTurma();
        ComponenteCurricular componenteCurricular = turma.getComponenteCurricular();
        super.entityManager.persist(componenteCurricular);
        super.entityManager.persist(turma);
        super.entityManager.flush();
        super.entityManager.refresh(turma);
        assertNotNull(turma.getChavePrimaria());
    }

    @Test
    public void testAlterarTurma() {
        Turma turmaObtida = super.entityManager.find(Turma.class, 2L);
        turmaObtida.setProfessor(super.entityManager.find(Professor.class, 6L));
        super.entityManager.merge(turmaObtida);
        super.entityManager.flush();
        super.entityManager.clear();
        Turma disciplinaAlterada = super.entityManager.find(Turma.class, 2L);
        assertEquals(turmaObtida.getProfessor().getChavePrimaria(),
                disciplinaAlterada.getProfessor().getChavePrimaria());

    }

    @Test
    public void testeRemoverTurma() {
        Turma turma = super.entityManager.find(Turma.class, 5L);
        super.entityManager.remove(turma);
        super.entityManager.flush();
        assertNull(super.entityManager.find(Turma.class, 5L));
    }

    @Test
    public void consultarTurmaPorProfessor() {
        TypedQuery<Turma> query = super.entityManager.createQuery("SELECT t FROM Turma t WHERE t.professor.chavePrimaria = :professor",
                Turma.class
        );
        query.setParameter("professor", 2l);

        List<Turma> disciplina = query.getResultList();
        ComponenteCurricular componenteCurricular = disciplina.get(0).getComponenteCurricular();
        assertEquals("Engenharia de Software", componenteCurricular.getDescricao());
    }

    @Test
    public void consultarQuantidadeTurmas() {
        TypedQuery<Turma> query = super.entityManager.createQuery("SELECT t FROM Turma t", Turma.class);
        List<Turma> turmas = query.getResultList();
        assertEquals(5, turmas.size());

        for (Turma turma : turmas) {
            ComponenteCurricular componenteCurricular = turma.getComponenteCurricular();
            LOGGER.info(componenteCurricular.getDescricao());
        }

    }

    @Test
    public void consultarTurmaPorDescricao() {
        TypedQuery<Turma> query = super.entityManager.createQuery("SELECT t FROM Turma t JOIN t.componenteCurricular cc WHERE cc.descricao = :nomeDisciplina", Turma.class);

        query.setParameter("nomeDisciplina", "Teste de Software");
        Turma turma = query.getSingleResult();

        assertNotNull(turma);
    }

    @Test
    public void testeConsultarTurmasPorCurso() {
        TypedQuery<Turma> query = super.entityManager.createQuery("SELECT t FROM Turma t JOIN t.componenteCurricular cc "
                + "JOIN cc.curso curso WHERE curso = :curso", Turma.class);
        Curso cursoEsperado = super.entityManager.find(Curso.class, 1L);
        int quantidadeEsperada = 4;
        query.setParameter("curso", cursoEsperado);
        List<Turma> turmas = query.getResultList();
        assertEquals(quantidadeEsperada, turmas.size());
        for (Turma turma : turmas) {
            Curso curso = turma.getComponenteCurricular().getCurso();
            assertEquals(cursoEsperado, curso);
        }
    }

    @Test
    public void testeCadastrarComponenteCurricularComDescricaoNula() {
        Turma turma = this.montarObjetoTurma();
        ComponenteCurricular componenteCurricular = turma.getComponenteCurricular();
        componenteCurricular.setDescricao(null);
        Set<ConstraintViolation<ComponenteCurricular>> constraintViolations = validator.validate(componenteCurricular);
        assertEquals(1, constraintViolations.size());

    }

    @Test
    public void testeCadastrarTurmaComTodosOsAtributosNulos() {
        Turma turma = new Turma();
        turma.setPeriodo(null);
        turma.setProfessor(null);
        turma.setComponenteCurricular(null);
        Set<ConstraintViolation<Turma>> constraintViolations = validator.validate(turma);
        assertEquals(4, constraintViolations.size());
    }

    @Test
    public void testeCadastrarTurmaComDescricaoEmBranco() {
        Turma turma = this.montarObjetoTurma();
        ComponenteCurricular componenteCurricular = turma.getComponenteCurricular();
        componenteCurricular.setDescricao("");
        Set<ConstraintViolation<ComponenteCurricular>> constraintViolations = validator.validate(componenteCurricular);
        assertEquals(3, constraintViolations.size());
    }

    @Test
    public void testeCadastrarTurmaComAtributosValidos() {
        Turma turma = montarObjetoTurma();
        Set<ConstraintViolation<Turma>> constraintViolations = validator.validate(turma);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testeCadastrarTurmaComDescricaoAcimaDoLimiteDeCaracteres() {
        Turma turma = montarObjetoTurma();
        ComponenteCurricular componenteCurricular = turma.getComponenteCurricular();
        componenteCurricular.setDescricao("TesteCadastrarDisciplinaComDescricaoAcimaDoLimiteDeCaracteres"
                + "TesteCadastrarDisciplinaComDescricaoAcimaDoLimiteDeCaracteres"
                + "TesteCadastrarDisciplinaComDescricaoAcimaDoLimiteDeCaracteres");
        Set<ConstraintViolation<ComponenteCurricular>> constraintViolations = validator.validate(componenteCurricular);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void testeCriarTurmaComDescricaoInvalida() {
        Turma turma = montarObjetoTurma();
        String mensagemEsperada = "A descrição da disciplina deve iniciar com "
                + "uma letra maiúscula, seguida de caracteres que não sejam dígitos";
        ComponenteCurricular componenteCurricular = turma.getComponenteCurricular();
        componenteCurricular.setDescricao("A2alise de Sistemas");
        Set<ConstraintViolation<ComponenteCurricular>> constraintViolations = validator.validate(componenteCurricular);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }

    public Turma montarObjetoTurma() {
        Turma turma = new Turma();
        Professor professor = super.entityManager.find(Professor.class, 2l);
        Curso curso = super.entityManager.find(Curso.class, 1l);
        Periodo periodo = super.entityManager.find(Periodo.class, 1l);
        turma.setProfessor(professor);
        turma.setPeriodo(periodo);
        turma.ofertar();
        ComponenteCurricular componenteCurricular = new ComponenteCurricular();
        componenteCurricular.setCodigoComponenteCurricular("Y687");
        componenteCurricular.setCurso(curso);
        componenteCurricular.setDescricao("Sofware Corporativo");
        turma.setComponenteCurricular(componenteCurricular);
        return turma;
    }

}
