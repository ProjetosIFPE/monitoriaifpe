/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.funcionais.disciplina;

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
public class TesteDisciplina extends MonitoriaTestCase {

    @Test
    public void testeCadastrarDisciplina() {
        Turma disciplina = this.montarObjetoDisciplina();
        super.entityManager.persist(disciplina);
        super.entityManager.flush();
        super.entityManager.refresh(disciplina);
        assertNotNull(disciplina.getChavePrimaria());
    }

    @Test
    public void testAlterarDisciplina() {
        Turma disciplinaObtida = super.entityManager.find(Turma.class, 2L);
        disciplinaObtida.setProfessor(super.entityManager.find(Professor.class, 6L));
        super.entityManager.merge(disciplinaObtida);
        super.entityManager.flush();
        super.entityManager.clear();
        Turma disciplinaAlterada = super.entityManager.find(Turma.class, 2L);
        assertEquals(disciplinaObtida.getProfessor().getChavePrimaria(),
                disciplinaAlterada.getProfessor().getChavePrimaria());

    }

    @Test
    public void testeRemoverDisciplina() {
        Turma disciplina = super.entityManager.find(Turma.class, 5L);
        super.entityManager.remove(disciplina);
        super.entityManager.flush();
        assertNull(super.entityManager.find(Turma.class, 5L));
    }

    @Test
    public void consultarDisciplinaPorProfessor() {
        TypedQuery<Turma> query = super.entityManager.createQuery("SELECT d FROM Disciplina d WHERE d.professor.chavePrimaria = :professor",
                Turma.class
        );
        query.setParameter("professor", 2l);

        List<Turma> disciplina = query.getResultList();
        ComponenteCurricular componenteCurricular = disciplina.get(0).getComponenteCurricular();
        assertEquals("Engenharia de Software", componenteCurricular.getDescricao());
    }

    @Test
    public void consultarQuantidadeDisciplinas() {
        TypedQuery<Turma> query = super.entityManager.createQuery("SELECT d FROM Disciplina d", Turma.class);
        List<Turma> disciplina = query.getResultList();
        assertEquals(5, disciplina.size());

        for (Turma disc : disciplina) {
            ComponenteCurricular componenteCurricular = disc.getComponenteCurricular();
            LOGGER.info(componenteCurricular.getDescricao());
        }

    }

    @Test
    public void consultarDisciplinaPorNome() {
        TypedQuery<Turma> query = super.entityManager.createQuery("SELECT d FROM Disciplina d JOIN d.componenteCurricular cc WHERE cc.descricao = :nomeDisciplina", Turma.class);

        query.setParameter("nomeDisciplina", "Teste de Software");
        Turma disciplina = query.getSingleResult();

        assertNotNull(disciplina);
    }

    @Test
    public void testeConsultarDisciplinasPorCurso() {
        TypedQuery<Turma> query = super.entityManager.createQuery("SELECT d FROM Disciplina d JOIN d.componenteCurricular cc "
                + "JOIN cc.curso curso WHERE curso = :curso", Turma.class);
        Curso cursoEsperado = super.entityManager.find(Curso.class, 1L);
        int quantidadeEsperada = 4;
        query.setParameter("curso", cursoEsperado);
        List<Turma> disciplinas = query.getResultList();
        assertEquals(quantidadeEsperada, disciplinas.size());
        for (Turma disciplina : disciplinas) {
            Curso curso = disciplina.getComponenteCurricular().getCurso();
            assertEquals(cursoEsperado, curso);
        }
    }

    @Test
    public void testeCadastrarDisciplinaComDescricaoNula() {
        Turma disciplina = this.montarObjetoDisciplina();
        ComponenteCurricular componenteCurricular = disciplina.getComponenteCurricular();
        componenteCurricular.setDescricao(null);
        Set<ConstraintViolation<ComponenteCurricular>> constraintViolations = validator.validate(componenteCurricular);
        assertEquals(1, constraintViolations.size());

    }

    @Test
    public void testeCadastrarDisciplinaComTodosOsAtributosNulos() {
        Turma disciplina = new Turma();
        disciplina.setPeriodo(null);
        disciplina.setProfessor(null);
        disciplina.setComponenteCurricular(null);
        Set<ConstraintViolation<Turma>> constraintViolations = validator.validate(disciplina);
        assertEquals(3, constraintViolations.size());
    }

    @Test
    public void testeCadastrarDisciplinaComDescricaoEmBranco() {
        Turma disciplina = this.montarObjetoDisciplina();
        ComponenteCurricular componenteCurricular = disciplina.getComponenteCurricular();
        componenteCurricular.setDescricao("");
        Set<ConstraintViolation<ComponenteCurricular>> constraintViolations = validator.validate(componenteCurricular);
        assertEquals(3, constraintViolations.size());
    }

    @Test
    public void testeCadastrarDisciplinaComAtributosValidos() {
        Turma disciplina = montarObjetoDisciplina();
        Set<ConstraintViolation<Turma>> constraintViolations = validator.validate(disciplina);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testeCadastrarDisciplinaComDescricaoAcimaDoLimiteDeCaracteres() {
        Turma disciplina = montarObjetoDisciplina();
        ComponenteCurricular componenteCurricular = disciplina.getComponenteCurricular();
        componenteCurricular.setDescricao("TesteCadastrarDisciplinaComDescricaoAcimaDoLimiteDeCaracteres"
                + "TesteCadastrarDisciplinaComDescricaoAcimaDoLimiteDeCaracteres"
                + "TesteCadastrarDisciplinaComDescricaoAcimaDoLimiteDeCaracteres");
        Set<ConstraintViolation<ComponenteCurricular>> constraintViolations = validator.validate(componenteCurricular);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void testeCriarDisciplinaComDescricaoInvalida() {
        Turma disciplina = montarObjetoDisciplina();
        String mensagemEsperada = "A descrição da disciplina deve iniciar com "
                + "uma letra maiúscula, seguida de caracteres que não sejam dígitos";
        ComponenteCurricular componenteCurricular = disciplina.getComponenteCurricular();
        componenteCurricular.setDescricao("A2alise de Sistemas");
        Set<ConstraintViolation<ComponenteCurricular>> constraintViolations = validator.validate(componenteCurricular);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }

    public Turma montarObjetoDisciplina() {
        Turma disciplina = new Turma();
        Professor professor = super.entityManager.find(Professor.class, 2l);
        Curso curso = super.entityManager.find(Curso.class, 1l);
        Periodo periodo = super.entityManager.find(Periodo.class, 1l);
        disciplina.setProfessor(professor);
        disciplina.setPeriodo(periodo);
        ComponenteCurricular componenteCurricular = new ComponenteCurricular();
        componenteCurricular.setCodigoComponenteCurricular("Y687");
        componenteCurricular.setCurso(curso);
        componenteCurricular.setDescricao("Sofware Corporativo");
        disciplina.setComponenteCurricular(componenteCurricular);
        return disciplina;
    }

}
