/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.monitoria;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.instituto.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.instituto.monitoria.impl.MonitoriaImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Modalidade;
import static org.junit.Assert.*;
import org.junit.Test;


/**
 *
 * @author Matheus Levi
 */
public class TesteMonitoria extends MonitoriaTestCase{
    
    @Test
    public void testeCadastrarMonitoria() {
        Monitoria monitoria = new MonitoriaImpl();
        monitoria.setAluno(super.entityManager.find(Aluno.class, 1));
        monitoria.setModalidade(Modalidade.BOLSISTA);
        monitoria.setDisciplina(super.entityManager.find(Disciplina.class, 1));
        monitoria.setPeriodo(super.entityManager.find(Periodo.class,1));
        monitoria.setHabilitado(true);
        monitoria.setHorarioEntrada("08:00");
        monitoria.setHorarioSaida("11:50");
        super.entityManager.persist(monitoria);
        assertTrue(monitoria.getChavePrimaria() > 0);
    }
    
}
