/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.monitoria;

import com.softwarecorporativo.monitoriaifpe.funcionais.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.Turma;
import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import java.util.Set;
import javax.validation.ConstraintViolation;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Matheus Levi
 */
public class TesteMonitoria extends MonitoriaTestCase {

    @Test
    public void testeCadastrarMonitoria() {
        Monitoria monitoria = montarObjetoMonitoria();
        super.entityManager.persist(monitoria);
        super.entityManager.flush();
        super.entityManager.refresh(monitoria);
        assertNotNull(monitoria.getChavePrimaria());
    }

    @Test
    public void testAlterarMonitoria() {
        Monitoria monitoria = super.entityManager.find(Monitoria.class, 1L);
        monitoria.reprovar();
        super.entityManager.merge(monitoria);
        super.entityManager.flush();
        super.entityManager.clear();
        Monitoria monitoriaAlterada = super.entityManager.find(Monitoria.class, 1L);
        assertTrue(monitoriaAlterada.estaReprovada());

    }

    @Test
    public void testeRemoverMonitoria() {

        Monitoria monitoria = super.entityManager.find(Monitoria.class, 2L);
        super.entityManager.remove(monitoria);
        super.entityManager.flush();
        assertNull(super.entityManager.find(Monitoria.class, 2L));
    }

    @Test
    public void testeMonitoriaComAtributosNulos() {
        Monitoria monitoria = new Monitoria();
        monitoria.setAluno(null);
        monitoria.setTurma(null);
        Set<ConstraintViolation<Monitoria>> constraintViolations = validator.validate(monitoria);
        assertEquals(3, constraintViolations.size());

    }

    @Test
    public void testeMonitoriaComDisciplinaNula() {
        Monitoria monitoria = new Monitoria();
        monitoria.setAluno(super.entityManager.find(Aluno.class, 1l));
        monitoria.setTurma(null);
        Set<ConstraintViolation<Monitoria>> constraintViolations = validator.validate(monitoria);
        assertEquals(2, constraintViolations.size());
    }

    @Test
    public void testeMonitoriaComAlunoNulo() {
        Monitoria monitoria = new Monitoria();
        monitoria.setAluno(null);
        monitoria.setTurma(super.entityManager.find(Turma.class, 1l));
        Set<ConstraintViolation<Monitoria>> constraintViolations = validator.validate(monitoria);
        assertEquals(2, constraintViolations.size());
    }

    @Test
    public void testeMonitoriaComPeriodoNulo() {
        Monitoria monitoria = new Monitoria();
        monitoria.setAluno(super.entityManager.find(Aluno.class, 1l));
        monitoria.setTurma(super.entityManager.find(Turma.class, 1l));
        Set<ConstraintViolation<Monitoria>> constraintViolations = validator.validate(monitoria);
        assertEquals(1, constraintViolations.size());
    }

    public Monitoria montarObjetoMonitoria() {
        Monitoria monitoria = new Monitoria();
        monitoria.aprovar();
        monitoria.setAluno(super.entityManager.find(Aluno.class, 1l));
        monitoria.setTurma(super.entityManager.find(Turma.class, 1l));
        return monitoria;
    }

}
