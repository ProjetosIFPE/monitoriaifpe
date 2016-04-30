/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.aluno;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.disciplina.Disciplina;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author douglas
 */
public class TesteAluno extends MonitoriaTestCase {

    @Test
    public void testePersistAluno() {
        Aluno aluno = montarObjetoAluno();
        super.entityManager.persist(aluno);
        super.entityManager.flush();
        super.entityManager.refresh(aluno.getCurso());
        super.entityManager.refresh(aluno);
        assertNotNull(aluno.getChavePrimaria());

    }

    @Test
    public void testeUpdateAluno() {
        Aluno alunoBuscado = super.entityManager.find(Aluno.class, 1L);
        String emailAntigo = alunoBuscado.getEmail();
        alunoBuscado.setEmail("edmilson@hotmail.com");
        super.entityManager.merge(alunoBuscado);
        super.entityManager.flush();
        super.entityManager.clear();
        alunoBuscado = super.entityManager.find(Aluno.class, 1L);
        assertEquals("edmilson@hotmail.com", alunoBuscado.getEmail());

    }

    @Test
    public void testeDeleteAluno() {
        Aluno alunoBuscado = super.entityManager.find(Aluno.class, 11L);
        super.entityManager.remove(alunoBuscado);
        super.entityManager.flush();
        assertNull(super.entityManager.find(Aluno.class, 11L));
    }

    @Test
    public void testeJPQLSelecionarAlunosPeloAnoDaMatricula() {
        TypedQuery<Long> query = super.entityManager.createQuery(
                "SELECT COUNT(a.chavePrimaria) from Aluno a WHERE a.matricula LIKE :matricula", Long.class);
        query.setParameter("matricula", "2014%");
        Long resultado = query.getSingleResult();
        assertEquals(new Long(3), resultado);
    }

    @Test
    public void testeJPQLSelecionarAlunosPeloCurso() {
        TypedQuery<Long> query = super.entityManager.createQuery(
                "SELECT COUNT(a.chavePrimaria) from Aluno a WHERE a.curso = :curso", Long.class);
        Curso curso = super.entityManager.find(Curso.class, 1L);
        query.setParameter("curso", curso);
        Long resultado = query.getSingleResult();
        assertEquals(new Long(5), resultado);
    }

    @Test
    public void testeJPQLSelecionarAlunosPelaInicialDoNome() {
        TypedQuery<Long> query = super.entityManager.createQuery(
                "SELECT COUNT(a.chavePrimaria) from Aluno a WHERE a.nome LIKE :nome", Long.class);
        query.setParameter("nome", "M%");
        Long resultado = query.getSingleResult();
        assertEquals(new Long(2), resultado);
    }

    @Test
    public void testeJPQLQuantidadeDeAlunos() {
        TypedQuery<Long> query = super.entityManager.createQuery(
                "SELECT COUNT(a.chavePrimaria) from Aluno a WHERE a.curso = (SELECT c.chavePrimaria FROM Curso c WHERE c.grau = 'TECNICO')", Long.class);
        Long resultado = query.getSingleResult();
        assertEquals(new Long(2), resultado);
    }
    
    @Test
    public void testeCriarAlunoComMatriculaInvalida() {
        Aluno aluno = this.montarObjetoAluno();
        aluno.setMatricula("20141Y1-RC2222");
        String mensagemEsperada = "A matrícula do aluno não corresponde ao seu curso ou campus";
        Set<ConstraintViolation<Aluno>> constraintViolations = validator.validate(aluno);
        assertEquals(1, constraintViolations.size());
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(mensagemEsperada, mensagemObtida);
        
    }

    private Aluno montarObjetoAluno() {
        Aluno alunoCriado = new Aluno();
        alunoCriado.setNome("Fulano");
        alunoCriado.setSobrenome("Silva");
        alunoCriado.setEmail("fulano@gmail.com");
        alunoCriado.setLogin("fulano21");
        alunoCriado.setMatricula("20141Y6-RC2222");
        alunoCriado.setSenha("fulano123");
        Curso curso = super.entityManager.find(Curso.class, 1L);
        alunoCriado.setCurso(curso);
        Disciplina disciplina = super.entityManager.find(Disciplina.class, 1L);
        alunoCriado.addDisciplina(disciplina);

        return alunoCriado;
    }

}
