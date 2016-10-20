/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.servico.MonitoriaService;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Douglas Albuquerque
 */
@ManagedBean
@RequestScoped
public class ProfessorMonitoriaBean extends ViewScopedBean<Monitoria> {

    private static final long serialVersionUID = 2777583349263188203L;

    @ManagedProperty(value = "#{usuarioBean}")
    private UsuarioBean usuarioBean;

    @EJB
    private MonitoriaService monitoriaService;

    private List<Monitoria> monitoriasAprovadas;

    private List<Monitoria> monitoriasSolicitadas;

    @Override
    protected void inicializarListaEntidades() {
        inicializarMonitoriasSolicitadas();
        inicializarMonitoriasAprovadas();
    }

    public void inicializarMonitoriasAprovadas() {
        Professor professor = (Professor) usuarioBean.getUsuario();
        monitoriasAprovadas = monitoriaService.consultarMonitoriasAprovadas(professor);
    }

    public void inicializarMonitoriasSolicitadas() {
        Professor professor = (Professor) usuarioBean.getUsuario();
        monitoriasSolicitadas = monitoriaService.consultarMonitoriasAguardandoAprovacao(professor);
    }

    public void aprovar() throws NegocioException {
        monitoriaService.aprovar(entidadeNegocio);
    }

    public void reprovar() throws NegocioException {
        monitoriaService.reprovar(entidadeNegocio);
    }

    public List<Monitoria> getMonitoriasSolicitadas() {
        return monitoriasSolicitadas;
    }

    public List<Monitoria> getMonitoriasAprovadas() {
        return monitoriasAprovadas;
    }

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    @Override
    void inicializarServico() {
        setService(monitoriaService);
    }

    @Override
    void inicializarEntidades() {
        setEntidadeNegocio(monitoriaService.getEntidadeNegocio());
    }

}
