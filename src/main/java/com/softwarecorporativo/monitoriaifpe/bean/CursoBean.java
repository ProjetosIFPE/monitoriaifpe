/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Grau;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Modalidade;
import com.softwarecorporativo.monitoriaifpe.servico.CursoService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author EdmilsonS
 */
@ManagedBean
@ViewScoped
public class CursoBean extends GenericBean<Curso> {
    
    @EJB
    private CursoService cursoService;
    
    @Override
    void inicializarEntidadeNegocio() {
        super.setEntidadeNegocio(cursoService.getEntidadeNegocio());
    }
    
    public void cadastrarCurso() {
     
        this.cursoService.salvar(entidadeNegocio);
        inicializarEntidadeNegocio();
    }
    
    public List<Curso> getCursos() {
        return this.cursoService.listarTodos();
    }
    
    public Grau[] getModalidades() {
        
        return Grau.values();
    }
    
    public void remover(Curso curso) {
        this.cursoService.remover(curso);
    }
    
}
