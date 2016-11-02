/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.servico.MonitoriaService;
import com.softwarecorporativo.monitoriaifpe.servico.TurmaService;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Douglas Albuquerque
 */
@ManagedBean
@ViewScoped
public class MonitoriaBean extends ViewScopedBean<Monitoria>{

    private static final long serialVersionUID = -4736071102515881964L;

    
    @EJB
    private MonitoriaService monitoriaService;

    @EJB
    private TurmaService turmaService;
    
    private List<Monitoria> todasMonitoriasAprovadas;
    
    @Override
    void inicializarServico() {
        setService(monitoriaService);
    }

    @Override
    void inicializarEntidades() {
        super.setEntidadeNegocio(monitoriaService.getEntidadeNegocio());
    }
    
    @Override
    protected void inicializarListaEntidades() {
        inicializarMonitoriasTodasAprovadas();
    }
    
    public List<Monitoria> getTodasMonitoriasAprovadas() {
        return todasMonitoriasAprovadas;
    }
    
    public void inicializarMonitoriasTodasAprovadas() {
        todasMonitoriasAprovadas = monitoriaService.consultarMonitoriasAprovadas();
    }
    
}
