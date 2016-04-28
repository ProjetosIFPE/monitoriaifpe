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
        Monitoria monitoria = super.entityManager.find(Monitoria.class, 2L);
        monitoria.setModalidade(Modalidade.BOLSISTA);
        super.entityManager.merge(monitoria);
        super.entityManager.flush();
        super.entityManager.clear();
        Monitoria monitoriaAlterada = super.entityManager.find(Monitoria.class, 2L);
        assertEquals(monitoria.getModalidade(), monitoriaAlterada.getModalidade());

    }

    @Test
    public void testeRemoverMonitoria() {

        Monitoria monitoria = super.entityManager.find(Monitoria.class, 2L);
        super.entityManager.remove(monitoria);
        super.entityManager.flush();
        assertNull(super.entityManager.find(Monitoria.class, 2L));
    }

    public Monitoria montarObjetoMonitoria() {
        Monitoria monitoria = new Monitoria();
        monitoria.setAluno(super.entityManager.find(Aluno.class, 1l));
        monitoria.setModalidade(Modalidade.BOLSISTA);
        monitoria.setDisciplina(super.entityManager.find(Disciplina.class, 1l));
        monitoria.setPeriodo(super.entityManager.find(Periodo.class, 1l));
        monitoria.setHabilitado(Boolean.TRUE);
        return monitoria;
    }

}
