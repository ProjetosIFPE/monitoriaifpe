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
import com.softwarecorporativo.monitoriaifpe.servico.MonitoriaService;

import java.util.Date;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class AtividadeBean extends GenericBean<Atividade> {

    private static final long serialVersionUID = -3272784032346171935L;

    @EJB
    private AtividadeService atividadeService;

    @EJB
    private MonitoriaService monitoriaService;

    private Monitoria monitoria;

    private Long monitoriaId;
  

    @Override
    void inicializarEntidadeNegocio() {
        setEntidadeNegocio(atividadeService.getEntidadeNegocio());
    }

    public void inicializarParametros() {
        if (monitoriaId != null) {
            monitoria = monitoriaService.buscarEntidade(monitoriaId);
        }
    }

    @Override
    void inicializarServico() {
        setService(atividadeService);
    }

    public List<Atividade> getAtividadeMonitoria() {
        return atividadeService.consultarAtividadesDaMonitoria(monitoria);
    }

    public List<Atividade> getAtividadesMensaisMonitoria(Date dataInicialMes, Date dataFinalMes) {
        return atividadeService.consultarAtividadesMensaisDaMonitoria(monitoria,
                dataInicialMes, dataFinalMes);
    }

    @Override
    public void cadastrar() {
        entidadeNegocio.setMonitoria(monitoria);
        super.cadastrar();
    }

    public Boolean isAtividadeCadastrada() {
        Boolean isCadastrada = Boolean.TRUE;
        if (entidadeNegocio.getChavePrimaria() == null) {
            isCadastrada = Boolean.FALSE;
        }
        return isCadastrada;
    }

    public void alterarDataInicioFimAtividade(Date data) {
        entidadeNegocio.setDataInicio(data);
        entidadeNegocio.setDataFim(data);
    }

    @Override
    public void alterar() {
        entidadeNegocio.setMonitoria(monitoria);
        super.alterar();
    }

    public void removerAtividade() throws NegocioException {
        super.remover(entidadeNegocio);
        inicializarEntidadeNegocio();
    }

    public Monitoria getMonitoria() {
        return monitoria;
    }

    public void setMonitoria(Monitoria monitoria) {
        this.monitoria = monitoria;
    }

    public StreamedContent getRelatorio(Date dataInicialMes, Date dataFinalMes) {
        byte[] bytes = atividadeService.obterRelatorioFrequencia(monitoria, dataInicialMes, dataFinalMes);
        return new ByteArrayContent(bytes, "application/pdf", "relatorioFrequencia.pdf");
    }

    public Long getMonitoriaId() {
        return monitoriaId;
    }

    public void setMonitoriaId(Long monitoriaId) {
        this.monitoriaId = monitoriaId;
    }

}
