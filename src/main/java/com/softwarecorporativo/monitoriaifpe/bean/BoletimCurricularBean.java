/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.boletim.BoletimCurricular;
import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.ComponenteCurricular;
import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.servico.BoletimCurricularService;
import com.softwarecorporativo.monitoriaifpe.servico.ComponenteCurricularService;
import com.softwarecorporativo.monitoriaifpe.servico.DisciplinaService;
import com.softwarecorporativo.monitoriaifpe.servico.ProfessorService;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author douglas
 */
@ManagedBean
@ViewScoped
public class BoletimCurricularBean extends GenericBean<BoletimCurricular> {

    @EJB
    private BoletimCurricularService boletimCurricularService;

    @EJB
    private ProfessorService professorService;

    @EJB
    private DisciplinaService disciplinaService;

    @EJB
    private ComponenteCurricularService componenteCurriculoService;

    private String ano;
    private String semestre;

    @Override
    void inicializarEntidadeNegocio() {
        setEntidadeNegocio(boletimCurricularService.getEntidadeNegocio());
    }

    @Override
    void inicializarServico() {
        setService(boletimCurricularService);
    }

    public List<ComponenteCurricular> listarTodosComponentesCurriculares() {
        return this.componenteCurriculoService.listarTodos();
    }

    @Override
    public void gravar() {
        Disciplina disciplina = disciplinaService.salvarDisciplinaComPeriodoAntigo(entidadeNegocio.getDisciplina(), ano, semestre);
        entidadeNegocio.setDisciplina(disciplina);
        boletimCurricularService.salvar(entidadeNegocio);
    }

    public BoletimCurricularService getBoletimCurricularService() {
        return boletimCurricularService;
    }

    public void setBoletimCurricularService(BoletimCurricularService boletimCurricularService) {
        this.boletimCurricularService = boletimCurricularService;
    }

    public List<Professor> listarTodosProfessores() {
        return this.professorService.listarTodos();
    }

    public void setDisciplinaService(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    public DisciplinaService getDisciplinaService() {
        return disciplinaService;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

}
