/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.servico.AtividadeService;

import java.util.Date;
import java.util.HashMap;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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

    private HashMap<ScheduleEvent, Atividade> mapaAtividades = new HashMap<>();

    private StreamedContent relatorio;

    
    
    @EJB
    private AtividadeService atividadeService;

    private Monitoria monitoria;

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
        super.inicializar();
        FacesContext context = FacesContext.getCurrentInstance();
        this.monitoria = (Monitoria) context.getExternalContext()
                .getFlash().get("monitoria");
        inicializarCalendarioAtividades();
    }

    private void inicializarCalendarioAtividades() {
        List<Atividade> atividades = atividadeService.consultarAtividadesDaMonitoria(monitoria);
        this.adicionarAtividadesNoCalendario(atividades);
    }

    private void adicionarAtividadesNoCalendario(List<Atividade> atividades) {
        for (Atividade atividade : atividades) {
            ScheduleEvent evento = new DefaultScheduleEvent(
                    atividade.getDescricao(), atividade.getDataInicio(), atividade.getDataFim());
            calendarioAtividades.addEvent(evento);
            mapaAtividades.put(evento, atividade);
        }
    }

    public void adicionarAtividade() throws NegocioException {

        entidadeNegocio.setMonitoria(monitoria);

        if (entidadeNegocio.getChavePrimaria() == null) {
            this.adicionarEventoCalendario();
            super.cadastrar();
        } else {
            super.alterar();
            atualizarEventoCalendario();
        }
        inicializarEntidadeNegocio();
        evento = new DefaultScheduleEvent();
    }

    public void adicionarEventoCalendario() {
        ScheduleEvent novoEvento = new DefaultScheduleEvent(entidadeNegocio.getDescricao(),
                entidadeNegocio.getDataInicio(), entidadeNegocio.getDataFim());
        calendarioAtividades.addEvent(novoEvento);
        mapaAtividades.put(novoEvento, entidadeNegocio);

    }

    public void atualizarEventoCalendario() {
        DefaultScheduleEvent eventoAtualizado = (DefaultScheduleEvent) evento;
        eventoAtualizado.setTitle(entidadeNegocio.getDescricao());
        eventoAtualizado.setStartDate(entidadeNegocio.getDataInicio());
        eventoAtualizado.setEndDate(entidadeNegocio.getDataFim());
        calendarioAtividades.updateEvent(eventoAtualizado);
    }

    public void removerAtividade() throws NegocioException {
        super.remover(entidadeNegocio);
        this.removerEventoCalendario();
        inicializarEntidadeNegocio();
    }

    public void removerEventoCalendario() {
        calendarioAtividades.deleteEvent(evento);
    }

    public void selecionarData(SelectEvent selectEvent) {
        inicializarEntidadeNegocio();
        Date dataSelecionada = (Date) selectEvent.getObject();
        entidadeNegocio.setDataInicio(dataSelecionada);
        entidadeNegocio.setDataFim(dataSelecionada);
    }

    public void selecionarEvento(SelectEvent selectEvent) {
        evento = (ScheduleEvent) selectEvent.getObject();
        entidadeNegocio = mapaAtividades.get(evento);
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

