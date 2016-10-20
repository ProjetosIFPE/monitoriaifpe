/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.Turma;
import com.softwarecorporativo.monitoriaifpe.servico.ComponenteCurricularService;
import com.softwarecorporativo.monitoriaifpe.servico.TurmaService;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.ComponenteCurricular;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class TurmaBean extends ViewScopedBean<Turma> {

    private static final long serialVersionUID = -3627257725939951177L;

    @ManagedProperty(value = "#{usuarioBean}")
    private UsuarioBean usuarioBean;

    private TurmaService turmaService;

    private ComponenteCurricularService componenteCurricularService;

    private List<ComponenteCurricular> componentesCurriculares;

    @Override
    protected void inicializar() {
        super.inicializar();
        setComponentesCurriculares(this.listarComponentes());
    }

    @Override
    void inicializarServico() {
        super.setService(turmaService);
    }

    @Override
    void inicializarEntidades() {
        setEntidadeNegocio(service.getEntidadeNegocio());
    }

    @Override
    public void inicializarListaEntidades() {
        Professor professor = (Professor) usuarioBean.getUsuario();
        super.entidades = turmaService.consultarTurmasOfertadas(professor);
    }

    private List<ComponenteCurricular> listarComponentes() {
        Professor professor = (Professor) usuarioBean.getUsuario();
        Curso curso = professor.getCurso();
        return componenteCurricularService.consultarComponentes(curso);
    }

    public Boolean possuiComponentesCurriculares() {
        return !getComponentesCurriculares().isEmpty();
    }

    public List<ComponenteCurricular> getComponentesCurriculares() {
        return componentesCurriculares;
    }

    protected void setComponentesCurriculares(List<ComponenteCurricular> componentesCurriculares) {
        this.componentesCurriculares = componentesCurriculares;
    }

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

}
