/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.servico.AtividadeService;
import com.softwarecorporativo.monitoriaifpe.servico.MonitoriaService;
import java.time.Year;
import java.util.Calendar;

import java.util.Date;
import java.util.GregorianCalendar;

import java.util.List;
import java.util.TimeZone;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;

import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class AtividadeBean extends GenericBean<Atividade> {

    private static final long serialVersionUID = -3272784032346171935L;

    private ScheduleModel calendarioAtividades = new DefaultScheduleModel();

    private ScheduleEvent evento = new DefaultScheduleEvent();

    private StreamedContent relatorio;

    @EJB
    private AtividadeService atividadeService;

    @EJB
    private MonitoriaService monitoriaService;

    private Monitoria monitoria;

    @Override
    void inicializarEntidadeNegocio() {
        FacesContext context = FacesContext.getCurrentInstance();
        this.monitoria = (Monitoria) context.getExternalContext()
                .getFlash().get("monitoria");
        setEntidadeNegocio(atividadeService.getEntidadeNegocio());
    }

    @Override
    void inicializarServico() {
        setService(atividadeService);
    }

    @Override
    protected void inicializar() {
        super.inicializar();
        
        inicializarCalendarioAtividades();
    }

    private void inicializarCalendarioAtividades() {
        List<Atividade> atividades = atividadeService.consultarAtividadesMensaisDaMonitoria(monitoria);
        this.adicionarAtividadesNoCalendario(atividades);
    }

    private void adicionarAtividadesNoCalendario(List<Atividade> atividades) {
        for (Atividade atividade : atividades) {
            
            DefaultScheduleEvent scheduleEvent = new DefaultScheduleEvent();
            Calendar calendar = new GregorianCalendar();
           
            scheduleEvent.setId(String.valueOf(atividade.getChavePrimaria()));
            scheduleEvent.setDescription(atividade.getDescricao());
            
            calendar.setTime(atividade.getDataInicio());
            System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));
            scheduleEvent.setStartDate(calendar.getTime());
            
            calendar.setTime(atividade.getDataFim());
            scheduleEvent.setEndDate(calendar.getTime());
            
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
        entidadeNegocio.setMonitoria(monitoria);
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

    public Monitoria getMonitoria() {
        return monitoria;
    }

    public void setMonitoria(Monitoria monitoria) {
        this.monitoria = monitoria;
    }

    public StreamedContent getRelatorio() {
        byte[] bytes = atividadeService.obterRelatorioFrequencia(monitoria, Integer.BYTES);
        return new ByteArrayContent(bytes, "application/pdf", "relatorioFrequencia.pdf");

    }

}
