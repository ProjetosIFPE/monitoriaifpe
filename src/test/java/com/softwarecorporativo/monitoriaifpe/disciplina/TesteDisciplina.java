/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.disciplina;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.impl.DisciplinaImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.instituto.professor.impl.ProfessorImpl;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Matheus Levi
 */

public class TesteDisciplina extends MonitoriaTestCase {
    
    @Test
    public void testeCadastrarDisciplina() {
        Disciplina disciplina = new DisciplinaImpl();
        Professor professor = super.entityManager.find(Professor.class, 2);
        Curso curso = super.entityManager.find(Curso.class, 1);
        disciplina.setProfessor(professor);
        disciplina.setCurso(curso);
        super.entityManager.persist(disciplina);
        assertTrue(disciplina.getChavePrimaria() > 0);
    }
    
}
