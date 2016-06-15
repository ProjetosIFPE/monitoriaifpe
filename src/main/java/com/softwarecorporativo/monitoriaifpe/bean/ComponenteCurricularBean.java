/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.ComponenteCurricular;
import com.softwarecorporativo.monitoriaifpe.servico.ComponenteCurricularService;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class ComponenteCurricularBean extends GenericBean<ComponenteCurricular> {

    private static final long serialVersionUID = -3700833037294884488L;

    @EJB
    private ComponenteCurricularService componenteCurricularService;

    @Override
    void inicializarEntidadeNegocio() {
        setEntidadeNegocio(componenteCurricularService.getEntidadeNegocio());

    }

    @Override
    void inicializarServico() {
        setService(componenteCurricularService);
    }
}
