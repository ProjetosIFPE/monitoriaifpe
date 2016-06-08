/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.ComponenteCurricular;
import com.softwarecorporativo.monitoriaifpe.servico.ComponenteCurricularService;
import com.softwarecorporativo.monitoriaifpe.servico.CursoService;
import java.util.ArrayList;
import java.util.List;
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

    @EJB
    private ComponenteCurricularService componenteCurricularService;

    @EJB
    private CursoService cursoService;

    public ArrayList<ComponenteCurricular> selectedsComponentesCurriculares;

    private Long cursoId;

    @Override
    void inicializarEntidadeNegocio() {
        setEntidadeNegocio(componenteCurricularService.getEntidadeNegocio());

    }

    @Override
    void inicializarServico() {
        setService(componenteCurricularService);
    }

    public void setSelectedsComponentesCurriculares(ArrayList<ComponenteCurricular> selectedsComponentesCurriculares) {
        this.selectedsComponentesCurriculares = selectedsComponentesCurriculares;
    }

    public ArrayList<ComponenteCurricular> getSelectedsComponentesCurriculares() {
        return selectedsComponentesCurriculares;
    }

    public List<Curso> listarTodosOsCursos() {
        return cursoService.listarTodos();
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
    
    @Override
    public void gravar() {
        Curso curso = cursoService.buscarEntidade(cursoId);
        entidadeNegocio.setCurso(curso);
        super.gravar(); 
    }
    
     
}
