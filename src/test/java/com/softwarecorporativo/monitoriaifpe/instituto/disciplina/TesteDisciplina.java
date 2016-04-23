/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.instituto.disciplina;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.instituto.professor.Professor;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.TypedQuery;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Matheus Levi
 */
public class TesteDisciplina extends MonitoriaTestCase {

    @Test
    public void testeCadastrarDisciplina() {
        try {

            Disciplina disciplina = new Disciplina();
            Professor professor = super.entityManager.find(Professor.class, 2l);
            Curso curso = super.entityManager.find(Curso.class, 1l);
            disciplina.setProfessor(professor);
            disciplina.setCurso(curso);
            disciplina.setDescricao("Desenvolvimento de Sofware Corporativo");
            super.entityManager.persist(disciplina);
            assertTrue(Boolean.TRUE);
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                entityTransaction.rollback();
            }
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void consultarDisciplinaPorProfessor() {
        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());

        TypedQuery<Disciplina> query = super.entityManager.createQuery(
                "SELECT d FROM Disciplina d WHERE d.professor.chavePrimaria = :professor",
                Disciplina.class
        );
        query.setParameter("professor", 2l);

        List<Disciplina> disciplina = query.getResultList();
        assertEquals("Software Corporativo", disciplina.get(0).getDescricao());
    }
    
    @Test
    public void consultarQuantidadeDisciplinas()
    {
        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());
        
        TypedQuery<Disciplina> query = super.entityManager.createQuery("SELECT d FROM Disciplina d", Disciplina.class);
        List<Disciplina> disciplina = query.getResultList();
        assertEquals(4,disciplina.size());
        
        for(Disciplina disc : disciplina)
        {
           System.out.println(disc.getDescricao());
        }
      
    }
    
    @Test
    public void ConsultarDisciplinaPorNome()
    {
        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());
        
        TypedQuery<Disciplina> query = super.entityManager.createQuery("SELECT d FROM Disciplina d"
                + " WHERE d.descricao = :nomeDisciplina", Disciplina.class);
        
        query.setParameter("nomeDisciplina", "Teste de Software");
        Disciplina disciplina = query.getSingleResult();
             
        assertNotNull(disciplina);
        
                
    }

}