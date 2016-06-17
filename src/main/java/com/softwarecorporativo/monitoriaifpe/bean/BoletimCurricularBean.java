/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.boletim.BoletimCurricular;
import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.ComponenteCurricular;
import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.modelo.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Constantes;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Semestre;
import com.softwarecorporativo.monitoriaifpe.servico.BoletimCurricularService;
import com.softwarecorporativo.monitoriaifpe.servico.ComponenteCurricularService;
import com.softwarecorporativo.monitoriaifpe.servico.DisciplinaService;
import com.softwarecorporativo.monitoriaifpe.servico.PeriodoService;
import com.softwarecorporativo.monitoriaifpe.servico.ProfessorService;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

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

    @EJB
    private PeriodoService periodoService;

    private Periodo periodo;

    private Disciplina disciplina;

    @Override
    void inicializarEntidadeNegocio() {
      
        entidadeNegocio = boletimCurricularService.getEntidadeNegocio();
        disciplina = disciplinaService.getEntidadeNegocio();
        periodo = periodoService.getEntidadeNegocio();
       
        disciplina.setPeriodo(periodo);
        entidadeNegocio.setDisciplina(disciplina);
      
        setEntidadeNegocio(boletimCurricularService.getEntidadeNegocio());
    }

    @Override
    void inicializarServico() {
        setService(boletimCurricularService);
    }

    public List<ComponenteCurricular> listarTodosComponentesCurriculares() {
        return this.componenteCurriculoService.listarTodos();
    }

    public List<Professor> listarTodosProfessores() {
        return this.professorService.listarTodos();
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinaService.listarTodos();
    }
    
    public Semestre[] getSemestres() {
        return Semestre.values();
    }

    public void cadastrarBoletim() {

        FacesContext context = FacesContext.getCurrentInstance();
        Aluno aluno = (Aluno) context.getExternalContext().getSessionMap().get(Constantes.ATRIBUTO_USUARIO_LOGADO);
        disciplina.setPeriodo(periodo);
        entidadeNegocio.setDisciplina(disciplina);
        entidadeNegocio.setAluno(aluno);
        super.gravar();
//        inicializarEntidadeNegocio();
    }

    public BoletimCurricularService getBoletimCurricularService() {
        return boletimCurricularService;
    }

    public void setBoletimCurricularService(BoletimCurricularService boletimCurricularService) {
        this.boletimCurricularService = boletimCurricularService;
    }

    public void setDisciplinaService(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    public DisciplinaService getDisciplinaService() {
        return disciplinaService;
    }

    public String fluxoDeCadastro(FlowEvent event) {
        return event.getNewStep();
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public void testar() {
        System.out.println("Testando");
    }
}
