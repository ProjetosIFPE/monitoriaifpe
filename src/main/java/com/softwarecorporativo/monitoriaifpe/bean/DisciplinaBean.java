/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.ComponenteCurricular;
import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.servico.ComponenteCurricularService;
import com.softwarecorporativo.monitoriaifpe.servico.DisciplinaService;
import com.softwarecorporativo.monitoriaifpe.servico.ProfessorService;
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
public class DisciplinaBean extends GenericBean<Disciplina> {
    
    @EJB
    private DisciplinaService disciplinaService;
    
    @EJB
    private ComponenteCurricularService componenteCurrService;

    @EJB
    private ProfessorService professorService;
    
    private Long professorId;
    
    private Long componenteCurricularID;
    
    @Override
    void inicializarEntidadeNegocio() {
        super.setEntidadeNegocio(disciplinaService.getEntidadeNegocio());
    }

    @Override
    void inicializarServico() {
        setService(disciplinaService);
    }
    
    public List<ComponenteCurricular> listarComponentesCurriculares(){
        return this.componenteCurrService.listarTodos();
    }
    
    public List<Professor> listarProfessores(){
        return this.professorService.listarTodos();
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public Long getComponenteCurricularID() {
        return componenteCurricularID;
    }


    public void setComponenteCurricularID(Long componenteCurricularID) {
        this.componenteCurricularID = componenteCurricularID;
    }

    
    
    @Override
    public void gravar(){
        Professor professor = professorService.buscarEntidade(professorId);
        ComponenteCurricular componenteCurricular = componenteCurrService.buscarEntidade(componenteCurricularID);
        entidadeNegocio.setProfessor(professor);
        entidadeNegocio.setComponenteCurricular(componenteCurricular);
        super.gravar();
    }
    
}
