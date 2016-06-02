/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.servico.DisciplinaService;
import javax.ejb.EJB;

/**
 *
 * @author Edmilson Santana
 */
public class DisciplinaBean extends GenericBean<Disciplina> {
    
    @EJB
    private DisciplinaService disciplinaService;

    @Override
    void inicializarEntidadeNegocio() {
        super.setEntidadeNegocio(disciplinaService.getEntidadeNegocio());
    }

    @Override
    void inicializarServico() {
        setService(disciplinaService);
    }
    
    
}
