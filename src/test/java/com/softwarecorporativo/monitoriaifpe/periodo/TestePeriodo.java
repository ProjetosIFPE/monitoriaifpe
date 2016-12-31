/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.periodo;

import com.softwarecorporativo.monitoriaifpe.funcionais.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.modelo.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Semestre;
import java.util.Set;
import javax.validation.ConstraintViolation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author Edmilson Santana
 */
public class TestePeriodo extends MonitoriaTestCase {

    @Test
    public void testeInserirPeriodo() {
        Periodo periodo = this.montarObjetoPeriodo();
        super.entityManager.persist(periodo);
        super.entityManager.flush();
        super.entityManager.refresh(periodo);
        assertNotNull(periodo.getChavePrimaria());
    }

    @Test
    public void testAlterarPeriodo() {
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
        Periodo periodo = super.entityManager.find(Periodo.class, 2L);
        super.entityManager.remove(periodo);
        super.entityManager.flush();
        assertNull(super.entityManager.find(Periodo.class, 2L));
    }

    @Test
    public void testeCriarPeriodoComAtributosInvalidos() {
        Periodo periodo = montarObjetoPeriodo();
        String mensagemEsperada = "O período deve iniciar no mínimo no ano de 1970";
        periodo.setAno(1969);
        Set<ConstraintViolation<Periodo>> constraintViolations = validator.validate(periodo);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(mensagemEsperada, mensagemObtida);
    }

    public Periodo montarObjetoPeriodo() {
        Periodo periodo = new Periodo();
        periodo.setAno(2000);
        periodo.setSemestre(Semestre.PRIMEIRO);
        return periodo;
    }
}
