/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.servico.AtividadeService;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class AtividadeBean extends GenericBean<Atividade> {

    private ScheduleModel calendarioAtividades;

    @EJB
    private AtividadeService atividadeService;

    @Override
    void inicializarEntidadeNegocio() {
        setEntidadeNegocio(atividadeService.getEntidadeNegocio());
    }

    @Override
    void inicializarServico() {
        setService(atividadeService);
    }

    @Override
    protected void inicializar() {
        calendarioAtividades = new DefaultScheduleModel();
        super.inicializar();
    }

    public ScheduleModel getCalendarioAtividades() {
        return calendarioAtividades;
    }

    public void setCalendarioAtividades(ScheduleModel calendarioAtividades) {
        this.calendarioAtividades = calendarioAtividades;
    }

}
