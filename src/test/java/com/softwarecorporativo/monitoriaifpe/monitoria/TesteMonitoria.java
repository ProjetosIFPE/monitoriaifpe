/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.monitoria;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Modalidade;
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
        monitoria.setModalidade(Modalidade.BOLSISTA);
        super.entityManager.merge(monitoria);
        super.entityManager.flush();
        super.entityManager.clear();
        Monitoria monitoriaAlterada = super.entityManager.find(Monitoria.class, 1L);
        assertEquals(monitoria.getModalidade(), monitoriaAlterada.getModalidade());

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
        monitoria.setModalidade(null);
        monitoria.setDisciplina(null);
        monitoria.setPeriodo(null);
        Set<ConstraintViolation<Monitoria>> constraintViolations = validator.validate(monitoria);
        assertEquals(5, constraintViolations.size());

    }

    @Test
    public void testeMonitoriaComDisciplinaNula() {
        Monitoria monitoria = new Monitoria();
        monitoria.setAluno(super.entityManager.find(Aluno.class, 1l));
        monitoria.setModalidade(Modalidade.BOLSISTA);
        monitoria.setDisciplina(null);
        monitoria.setPeriodo(super.entityManager.find(Periodo.class, 1l));
        Set<ConstraintViolation<Monitoria>> constraintViolations = validator.validate(monitoria);
        assertEquals(2, constraintViolations.size());
    }

    @Test
    public void testeMonitoriaComAlunoNulo() {
        Monitoria monitoria = new Monitoria();
        monitoria.setAluno(null);
        monitoria.setModalidade(Modalidade.BOLSISTA);
        monitoria.setDisciplina(super.entityManager.find(Disciplina.class, 1l));
        monitoria.setPeriodo(super.entityManager.find(Periodo.class, 1l));
        Set<ConstraintViolation<Monitoria>> constraintViolations = validator.validate(monitoria);
        assertEquals(2, constraintViolations.size());
    }

    @Test
    public void testeMonitoriaComPeriodoNulo() {
        Monitoria monitoria = new Monitoria();
        monitoria.setAluno(super.entityManager.find(Aluno.class, 1l));
        monitoria.setModalidade(Modalidade.BOLSISTA);
        monitoria.setDisciplina(super.entityManager.find(Disciplina.class, 1l));
        monitoria.setPeriodo(null);
        Set<ConstraintViolation<Monitoria>> constraintViolations = validator.validate(monitoria);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void testeMonitoriaComModalidadeNula() {
        Monitoria monitoria = new Monitoria();
        monitoria.setAluno(super.entityManager.find(Aluno.class, 1l));
        monitoria.setModalidade(null);
        monitoria.setDisciplina(super.entityManager.find(Disciplina.class, 1l));
        monitoria.setPeriodo(super.entityManager.find(Periodo.class, 1l));
        Set<ConstraintViolation<Monitoria>> constraintViolations = validator.validate(monitoria);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void testeCriarMonitoriaComDisciplinaInvalida() {
        Monitoria monitoria = montarObjetoMonitoria();
        String mensagemEsperada = "O aluno monitor não pode ter sido reprovado no componente curricular da disciplina e deve possuir aprovação com média maior ou igual a 7.0";
        monitoria.setDisciplina(super.entityManager.find(Disciplina.class, 3l));
        Set<ConstraintViolation<Monitoria>> constraintViolations = validator.validate(monitoria);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }

    public Monitoria montarObjetoMonitoria() {
        Monitoria monitoria = new Monitoria();
        monitoria.setAluno(super.entityManager.find(Aluno.class, 1l));
        monitoria.setModalidade(Modalidade.BOLSISTA);
        monitoria.setDisciplina(super.entityManager.find(Disciplina.class, 1l));
        monitoria.setPeriodo(super.entityManager.find(Periodo.class, 1l));
        return monitoria;
    }

}
