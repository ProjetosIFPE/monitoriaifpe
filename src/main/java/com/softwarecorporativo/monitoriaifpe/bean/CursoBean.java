/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Grau;
import com.softwarecorporativo.monitoriaifpe.servico.CursoService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.component.tabview.TabView;

/**
 *
 * @author EdmilsonS
 */
@ManagedBean
@ViewScoped
public class CursoBean extends GenericBean<Curso> {

    @EJB
    private CursoService cursoService;

    private Integer tabViewIndex;


    @Override
    void inicializarEntidadeNegocio() {
        super.setEntidadeNegocio(cursoService.getEntidadeNegocio());
    }

    public Grau[] getModalidades() {
        return Grau.values();
    }

    @Override
    void inicializarServico() {
        setService(cursoService);
    }

    @Override
    public void gravar() {
        tabViewIndex = 1;
        super.gravar();
    }

    @Override
    public void setEntidadeNegocio(Curso entidadeNegocio) {
        tabViewIndex = 0;
        super.setEntidadeNegocio(entidadeNegocio); 
    }
    
    
    public Integer getTabViewIndex() {
        return tabViewIndex;
    }

    public void setTabViewIndex(Integer tabViewIndex) {
        this.tabViewIndex = tabViewIndex;
    }
    
    



}
