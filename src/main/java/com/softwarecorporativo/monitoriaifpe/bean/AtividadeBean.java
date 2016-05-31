/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.servico.AtividadeService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class AtividadeBean extends GenericBean<Atividade>  {

    @EJB
    private AtividadeService atividadeService;

    public AtividadeService getAtividadeService() {
        return atividadeService;
    }

    public void setAtividadeService(AtividadeService atividadeService) {
        this.atividadeService = atividadeService;
    }
    
    public AtividadeBean() {
        setEntidadeNegocio(new Atividade());
    }
    
    public void cadastrarAtividade() {
        System.out.println("Atividade: " + super.entidadeNegocio.getDescricao());
    }
    
    
    public List<Atividade> getAtividades() {
        List<Atividade> atividades = new ArrayList<>();
        Atividade atividade = new Atividade();
        atividade.setDescricao("Descrição da Atividade");
        atividades.add(atividade);
        atividades.add(atividade);
        return atividades;
    }
    
    public void removerAtividade(Atividade atividade) {
        super.adicionarMensagemView("Removendo Atividade");
    }
    
    public void alterarAtividade(Atividade atividade) {
        super.adicionarMensagemView("Alterando Atividade");
    }
    
    
    
}
