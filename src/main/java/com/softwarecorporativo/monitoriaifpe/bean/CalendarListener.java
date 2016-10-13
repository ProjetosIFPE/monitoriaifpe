/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class CalendarListener {

    @ManagedProperty(value = "#{atividadeBean}")
    private AtividadeBean atividadeBean;
    
    private ScheduleModel calendario;

    private ScheduleEvent evento;

    private Date dataInicialMes;

    private Date dataFinalMes;

    private HashMap<ScheduleEvent, Atividade> mapaEventoAtividade;
    

    @PostConstruct
    public void inicializarCalendario() {
        evento = new DefaultScheduleEvent();
        mapaEventoAtividade = new HashMap<>();
        calendario = criarCalendario();

    }

    public LazyScheduleModel criarCalendario() {
        return new LazyScheduleModel() {
            private static final long serialVersionUID = 1297279688835547588L;

            @Override
            public void loadEvents(Date start, Date end) {

                dataInicialMes = start;
                dataFinalMes = end;
                List<Atividade> atividades = atividadeBean
                        .getAtividadesMensaisMonitoria(dataInicialMes, dataFinalMes);

                popularCalendarioComAtividades(this, atividades);
            }

        };
    }

    private void popularCalendarioComAtividades(ScheduleModel calendario, List<Atividade> atividades) {
        for (Atividade atividade : atividades) {
            ScheduleEvent novoEvento = new DefaultScheduleEvent(
                    atividade.getDescricao(), atividade.getDataInicio(), atividade.getDataFim());
            calendario.addEvent(novoEvento);
            mapaEventoAtividade.put(novoEvento, atividade);
        }
    }

   
    public void adicionarAtividade() throws NegocioException {

        if (atividadeBean.isAtividadeCadastrada()) {
            atividadeBean.alterar();
            atualizarEventoCalendario();
        } else {
            atividadeBean.cadastrar();
            this.adicionarEventoCalendario();
        }
        atividadeBean.inicializarEntidades();
        evento = new DefaultScheduleEvent();
    }

    public void adicionarEventoCalendario() {
        Atividade atividade = atividadeBean.getEntidadeNegocio();
        ScheduleEvent novoEvento = new DefaultScheduleEvent(atividade.getDescricao(),
                atividade.getDataInicio(), atividade.getDataFim());
        calendario.addEvent(novoEvento);
        mapaEventoAtividade.put(novoEvento, atividade);

    }

    public void atualizarEventoCalendario() {
        DefaultScheduleEvent eventoAtualizado = (DefaultScheduleEvent) evento;

        Atividade atividade = atividadeBean.getEntidadeNegocio();

        eventoAtualizado.setTitle(atividade.getDescricao());
        eventoAtualizado.setStartDate(atividade.getDataInicio());
        eventoAtualizado.setEndDate(atividade.getDataFim());

        calendario.updateEvent(eventoAtualizado);
    }

    public void removerEventoCalendario() throws NegocioException {
        calendario.deleteEvent(evento);
        atividadeBean.removerAtividade();
    }

    public void selecionarData(SelectEvent selectEvent) {

        atividadeBean.inicializarEntidades();

        Date dataSelecionada = (Date) selectEvent.getObject();
        atividadeBean.alterarDataInicioFimAtividade(dataSelecionada);
    }

    public void selecionarEvento(SelectEvent selectEvent) {
        evento = (ScheduleEvent) selectEvent.getObject();
        Atividade atividade = mapaEventoAtividade.get(evento);
        atividadeBean.setEntidadeNegocio(atividade);
    }

    public StreamedContent gerarRelatorio() {
        return atividadeBean.getRelatorio(dataInicialMes, dataFinalMes);
    }

    public AtividadeBean getAtividadeBean() {
        return atividadeBean;
    }

    public void setAtividadeBean(AtividadeBean atividadeBean) {
        this.atividadeBean = atividadeBean;
    }

    public ScheduleModel getCalendario() {
        return calendario;
    }

    public void setCalendario(ScheduleModel calendario) {
        this.calendario = calendario;
    }

    public ScheduleEvent getEvento() {
        return evento;
    }

    public void setEvento(ScheduleEvent evento) {
        this.evento = evento;
    }

}
