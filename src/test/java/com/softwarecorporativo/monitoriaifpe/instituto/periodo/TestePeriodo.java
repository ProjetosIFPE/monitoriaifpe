/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.instituto.periodo;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Semestre;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author EdmilsonS
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
    
    public Periodo montarObjetoPeriodo() {
        Periodo periodo = new Periodo();
        periodo.setAno(1970);
        periodo.setSemestre(Semestre.PRIMEIRO);
        return periodo;
    }
}
