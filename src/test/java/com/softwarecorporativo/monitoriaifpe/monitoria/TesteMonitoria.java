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
import com.softwarecorporativo.monitoriaifpe.instituto.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Modalidade;
import java.util.logging.Level;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Matheus Levi
 */
public class TesteMonitoria extends MonitoriaTestCase {

    @Test
    public void testeCadastrarMonitoria() {
        try {
            Monitoria monitoria = new Monitoria();
            monitoria.setAluno(super.entityManager.find(Aluno.class, 1l));
            monitoria.setModalidade(Modalidade.BOLSISTA);
            monitoria.setDisciplina(super.entityManager.find(Disciplina.class, 1l));
            monitoria.setPeriodo(super.entityManager.find(Periodo.class, 1l));
            monitoria.setHabilitado(Boolean.TRUE);
            super.entityManager.persist(monitoria);
            super.entityTransaction.commit();
            assertTrue(Boolean.TRUE);
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                entityTransaction.rollback();
            }
            Assert.fail(e.getMessage());
        }

    }

}
