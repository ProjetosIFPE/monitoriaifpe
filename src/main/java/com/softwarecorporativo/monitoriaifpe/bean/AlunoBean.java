/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.servico.AlunoService;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Douglas Albuquerque
 */
@ManagedBean
@ViewScoped
public class AlunoBean extends GenericBean<Aluno> {

    private static final long serialVersionUID = 2777583349263188203L;
    
    @EJB
    private AlunoService alunoService;
    
    @Override
    void inicializarEntidadeNegocio() {
        setEntidadeNegocio(alunoService.getEntidadeNegocio());
    }
    
    @Override
    void inicializarServico() {
        setService(alunoService);
    }
    
    
    
    
    
}
