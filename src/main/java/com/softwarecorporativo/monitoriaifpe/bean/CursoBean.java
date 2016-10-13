/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.servico.CursoService;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class CursoBean extends ViewScopedBean<Curso> {

    private static final long serialVersionUID = -1771136068297478129L;

    @EJB
    private CursoService cursoService;

    @Override
    void inicializarEntidades() {
        super.setEntidadeNegocio(cursoService.getEntidadeNegocio());
    }


    @Override
    void inicializarServico() {
        setService(cursoService);
    }

   

}
