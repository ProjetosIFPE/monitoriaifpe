/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.servico.AtividadeService;
import com.softwarecorporativo.monitoriaifpe.servico.MonitoriaService;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class AtividadeBean extends GenericBean<Atividade> {

    private ScheduleModel calendarioAtividades;
    
    private StreamedContent relatorio;
    
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
        calendarioAtividades = new DefaultScheduleModel();
        super.inicializar();
    }

    public ScheduleModel getCalendarioAtividades() {
        return calendarioAtividades;
    }

    public void setCalendarioAtividades(ScheduleModel calendarioAtividades) {
        this.calendarioAtividades = calendarioAtividades;
    }

    public StreamedContent getRelatorio() {
        byte[] bytes = atividadeService.obterRelatorioFrequencia(monitoriaService.buscarEntidade(1L), Integer.BYTES);
        return new ByteArrayContent(bytes, "application/pdf", "relatorioFrequencia.pdf");
    }

}
