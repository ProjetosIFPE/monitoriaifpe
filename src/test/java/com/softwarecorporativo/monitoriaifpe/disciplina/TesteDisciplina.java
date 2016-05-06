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
        ComponenteCurricular disciplina = this.montarObjetoDisciplina();
        super.entityManager.persist(disciplina);
        super.entityManager.flush();
        super.entityManager.refresh(disciplina);
        assertNotNull(disciplina.getChavePrimaria());
    }

    @Test
    public void testAlterarDisciplina() {
        ComponenteCurricular disciplinaObtida = super.entityManager.find(ComponenteCurricular.class, 2L);
        disciplinaObtida.setProfessor(super.entityManager.find(Professor.class, 6L));
        super.entityManager.merge(disciplinaObtida);
        super.entityManager.flush();
        super.entityManager.clear();
        ComponenteCurricular disciplinaAlterada = super.entityManager.find(ComponenteCurricular.class, 2L);
        assertEquals(disciplinaObtida.getProfessor().getChavePrimaria(),
                disciplinaAlterada.getProfessor().getChavePrimaria());

    }

    @Test
    public void testeRemoverDisciplina() {
        ComponenteCurricular disciplina = super.entityManager.find(ComponenteCurricular.class, 3L);
        super.entityManager.remove(disciplina);
        super.entityManager.flush();
        assertNull(super.entityManager.find(ComponenteCurricular.class, 3L));
    }

    @Test
    public void consultarDisciplinaPorProfessor() {
        TypedQuery<ComponenteCurricular> query = super.entityManager.createQuery(
                "SELECT d FROM Disciplina d WHERE d.professor.chavePrimaria = :professor",
                ComponenteCurricular.class
        );
        query.setParameter("professor", 2l);

        List<ComponenteCurricular> disciplina = query.getResultList();
        assertEquals("Software Corporativo", disciplina.get(0).getDescricao());
    }

    @Test
    public void consultarQuantidadeDisciplinas() {
        TypedQuery<ComponenteCurricular> query = super.entityManager.createQuery("SELECT d FROM Disciplina d", ComponenteCurricular.class);
        List<ComponenteCurricular> disciplina = query.getResultList();
        assertEquals(5, disciplina.size());

        for (ComponenteCurricular disc : disciplina) {
            LOGGER.info(disc.getDescricao());
        }

    }

    @Test
    public void consultarDisciplinaPorNome() {
        TypedQuery<ComponenteCurricular> query = super.entityManager.createQuery("SELECT d FROM Disciplina d"
                + " WHERE d.descricao = :nomeDisciplina", ComponenteCurricular.class);

        query.setParameter("nomeDisciplina", "Teste de Software");
        ComponenteCurricular disciplina = query.getSingleResult();

        assertNotNull(disciplina);
    }

    @Test
    public void testeConsultarDisciplinasPorCurso() {
        TypedQuery<ComponenteCurricular> query = super.entityManager.createQuery(
                "SELECT d FROM Disciplina d WHERE d.curso = :curso", ComponenteCurricular.class);
        Curso cursoEsperado = super.entityManager.find(Curso.class, 1L);
        int quantidadeEsperada = 4;
        query.setParameter("curso", cursoEsperado);
        List<ComponenteCurricular> disciplinas = query.getResultList();
        assertEquals(quantidadeEsperada, disciplinas.size());
        for (ComponenteCurricular disciplina : disciplinas) {
            assertEquals(cursoEsperado, disciplina.getCurso());
        }
    }
    
    @Test
    public void testeCadastrarDisciplinaComDescricaoNula()
    {
        ComponenteCurricular disciplina = new ComponenteCurricular();
        Professor professor = super.entityManager.find(Professor.class, 6l);
        Curso curso = super.entityManager.find(Curso.class, 1l);
        disciplina.setProfessor(professor);
        disciplina.setCurso(curso);
        disciplina.setDescricao(null);
        Set<ConstraintViolation<ComponenteCurricular>> constraintViolations = validator.validate(disciplina);
        assertEquals(1, constraintViolations.size());
    
    }
    
    @Test
    public void testeCadastrarDisciplinaComTodosOsAtributosNulos()
    {
        ComponenteCurricular disciplina = new ComponenteCurricular();
        disciplina.setProfessor(null);
        disciplina.setCurso(null);
        disciplina.setDescricao(null);
        Set<ConstraintViolation<ComponenteCurricular>> constraintViolations = validator.validate(disciplina);
        assertEquals(2, constraintViolations.size());
    }
    
    @Test
    public void testeCadastrarDisciplinaComDescricaoEmBranco()
    {
        ComponenteCurricular disciplina = new ComponenteCurricular();
        Professor professor = super.entityManager.find(Professor.class, 6l);
        Curso curso = super.entityManager.find(Curso.class, 1l);
        disciplina.setProfessor(professor);
        disciplina.setCurso(curso);
        disciplina.setDescricao("");
        Set<ConstraintViolation<ComponenteCurricular>> constraintViolations = validator.validate(disciplina);
        assertEquals(3, constraintViolations.size()); 
    }
    
    @Test
    public void testeCadastrarDisciplinaComAtributosValidos()
    {
        ComponenteCurricular disciplina = new ComponenteCurricular();
        Professor professor = super.entityManager.find(Professor.class, 6l);
        Curso curso = super.entityManager.find(Curso.class, 1l);
        disciplina.setProfessor(professor);
        disciplina.setCurso(curso);
        disciplina.setDescricao("Sistemas Embarcados");
        Set<ConstraintViolation<ComponenteCurricular>> constraintViolations = validator.validate(disciplina);
        assertEquals(0, constraintViolations.size()); 
    }
    
    @Test
    public void testeCadastrarDisciplinaComDescricaoAcimaDoLimiteDeCaracteres()
    {
         ComponenteCurricular disciplina = new ComponenteCurricular();
        Professor professor = super.entityManager.find(Professor.class, 6l);
        Curso curso = super.entityManager.find(Curso.class, 1l);
        disciplina.setProfessor(professor);
        disciplina.setCurso(curso);
        disciplina.setDescricao("TesteCadastrarDisciplinaComDescricaoAcimaDoLimiteDeCaracteres"
                + "TesteCadastrarDisciplinaComDescricaoAcimaDoLimiteDeCaracteres"
                + "TesteCadastrarDisciplinaComDescricaoAcimaDoLimiteDeCaracteres");
        Set<ConstraintViolation<ComponenteCurricular>> constraintViolations = validator.validate(disciplina);
        assertEquals(1, constraintViolations.size()); 
    }
    
     @Test
    public void testeCriarCursoComDescricaoInvalida() {
        ComponenteCurricular disciplina = montarObjetoDisciplina();
        String mensagemEsperada = "A descrição da disciplina deve iniciar com "
                + "uma letra maiúscula, seguida de caracteres que não sejam dígitos";
        disciplina.setDescricao("A2alise de Sistemas");
        Set<ConstraintViolation<ComponenteCurricular>> constraintViolations = validator.validate(disciplina);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }
   
    

    public ComponenteCurricular montarObjetoDisciplina() {
        ComponenteCurricular disciplina = new ComponenteCurricular();
        Professor professor = super.entityManager.find(Professor.class, 2l);
        Curso curso = super.entityManager.find(Curso.class, 1l);
        disciplina.setProfessor(professor);
        disciplina.setCurso(curso);
        disciplina.setDescricao("Desenvolvimento de Sofware Corporativo");
        return disciplina;
    }
    
    
}
