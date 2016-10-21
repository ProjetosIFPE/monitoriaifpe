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
import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Constantes;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class TurmaBean extends ViewScopedBean<Turma> {

    private static final long serialVersionUID = -3627257725939951177L;

    @ManagedProperty("#{usuarioBean}")
    private UsuarioBean usuarioBean;

    @Inject
    private TurmaService turmaService;

    private List<Turma> turmasOfertadas;

    @Inject
    private ComponenteCurricularService componenteCurricularService;

    private List<ComponenteCurricular> componentesCurriculares;

    @Override
    void inicializarServico() {
        super.setService(turmaService);
    }

    @Override
    public void cadastrar() {
        Professor professor = (Professor) usuarioBean.getUsuario();
        entidadeNegocio.setProfessor(professor);
        super.cadastrar();
    }

    @Override
    void inicializarEntidades() {
        setEntidadeNegocio(service.getEntidadeNegocio());
    }

    @Override
    protected void popularListaEntidades() {
        super.popularListaEntidades();
        inicializarTurmasOfertadas();
    }

    @Override
    protected void inicializarListaEntidades() {
        super.inicializarListaEntidades();
        inicializarComponentesCurriculares();
    }

    public void inicializarTurmasOfertadas() {
        Professor professor = (Professor) usuarioBean.getUsuario();
        turmasOfertadas = turmaService.consultarTurmasOfertadas(professor);
    }

    private void inicializarComponentesCurriculares() {
        Professor professor = (Professor) usuarioBean.getUsuario();
        Curso curso = professor.getCurso();
        componentesCurriculares = componenteCurricularService.consultarComponentes(curso);
    }

    public Boolean possuiComponentesCurriculares() {
        return !getComponentesCurriculares().isEmpty();
    }

    public List<ComponenteCurricular> getComponentesCurriculares() {
        return componentesCurriculares;
    }

    public List<Turma> getTurmasOfertadas() {
        return turmasOfertadas;
    }

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

}
