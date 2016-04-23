/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.instituto.periodo;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Semestre;
import java.util.Set;
import java.util.logging.Level;
import javax.validation.ConstraintViolation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author EdmilsonS
 */
public class TestePeriodo extends MonitoriaTestCase {

    @Test
    public void testeInserirPeriodo() {

        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());

        Periodo periodo = this.montarObjetoPeriodo();
        super.entityManager.persist(periodo);
        super.entityManager.flush();
        super.entityManager.refresh(periodo);
        assertNotNull(periodo.getChavePrimaria());
    }

    @Test
    public void testAlterarPeriodo() {

        LOGGER.log(Level.INFO, "Iniciando Teste - ", name.getMethodName());

        Periodo periodoObtido = super.entityManager.find(Periodo.class, 1L);
        periodoObtido.setSemestre(Semestre.PRIMEIRO);
        periodoObtido.setAno(2014);
        super.entityManager.merge(periodoObtido);
        super.entityManager.flush();
        super.entityManager.clear();
        Periodo periodoAlterado = super.entityManager.find(Periodo.class, 1L);
        assertEquals(periodoObtido.getSemestre(), periodoAlterado.getSemestre());
        assertEquals(periodoObtido.getAno(), periodoAlterado.getAno());
    }

    @Test
    public void testeRemoverPeriodo() {

        LOGGER.log(Level.INFO, "Iniciando Teste - ", name.getMethodName());

        Periodo periodo = super.entityManager.find(Periodo.class, 2L);
        super.entityManager.remove(periodo);
        super.entityManager.flush();
        assertNull(super.entityManager.find(Periodo.class, 2L));
    }
        
    @Test
    public void testeCriarPeriodoAnoInvalido() {
        Periodo periodo = montarObjetoPeriodo();
        periodo.setAno(1969);
        Set<ConstraintViolation<Periodo>> constraintViolations = validator.validate(periodo);
        assertEquals(1, constraintViolations.size());
    }

    public Periodo montarObjetoPeriodo() {
        Periodo periodo = new Periodo();
        periodo.setAno(2000);
        periodo.setSemestre(Semestre.PRIMEIRO);
        return periodo;
    }
}