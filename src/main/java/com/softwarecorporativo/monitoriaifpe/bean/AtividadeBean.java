/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.SituacaoAtividade;
import com.softwarecorporativo.monitoriaifpe.servico.AtividadeService;
import com.softwarecorporativo.monitoriaifpe.servico.MonitoriaService;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class AtividadeBean extends GenericBean<Atividade> {

    private ScheduleModel calendarioAtividades;

    public ScheduleEvent evento;

    public Monitoria monitoria;

    @EJB
    private AtividadeService atividadeService;
    
    @EJB
    private MonitoriaService monitoriaService;

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

        inicializarCalendarioAtividades();
        super.inicializar();
    }

    private void inicializarCalendarioAtividades() {
        calendarioAtividades = new DefaultScheduleModel();
        evento = new DefaultScheduleEvent();
        List<Atividade> atividades = atividadeService.consultarAtividadesDaMonitoria(monitoria);
        for (Atividade atividade : atividades) {
            DefaultScheduleEvent scheduleEvent = new DefaultScheduleEvent();
            scheduleEvent.setId(String.valueOf(atividade.getChavePrimaria()));
            scheduleEvent.setDescription(atividade.getDescricao());
            scheduleEvent.setStartDate(atividade.getDataInicio());
            scheduleEvent.setEndDate(atividade.getDataFim());
            calendarioAtividades.addEvent(evento);
        }

    }

    public void adicionarAtividade(ActionEvent actionEvent) {
        if (evento.getId() == null) {
            evento = new DefaultScheduleEvent(entidadeNegocio.getDescricao(),
                    entidadeNegocio.getDataInicio(), entidadeNegocio.getDataFim());
            calendarioAtividades.addEvent(evento);
        } else {
            calendarioAtividades.updateEvent(evento);
        }
        List<Monitoria> monitorias = monitoriaService.listarTodos();
        entidadeNegocio.setMonitoria(monitorias.get(0));
        super.gravar();
        evento = new DefaultScheduleEvent();
    }

    public void selecionarData(SelectEvent selectEvent) {
        Date dataSelecionada = (Date) selectEvent.getObject();
        entidadeNegocio.setDataInicio(dataSelecionada);
        entidadeNegocio.setDataFim(dataSelecionada);
    }

    public void selecionarEvento(SelectEvent selectEvent) {
        evento = (ScheduleEvent) selectEvent.getObject();
    }

    public ScheduleModel getCalendarioAtividades() {
        return calendarioAtividades;
    }

    public void setCalendarioAtividades(ScheduleModel calendarioAtividades) {
        this.calendarioAtividades = calendarioAtividades;
    }

    public ScheduleEvent getAtividade() {
        return evento;
    }

    public void setAtividade(ScheduleEvent atividade) {
        this.evento = atividade;
    }

}
