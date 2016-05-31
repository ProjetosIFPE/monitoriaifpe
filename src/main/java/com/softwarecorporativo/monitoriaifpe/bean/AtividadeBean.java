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

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class AtividadeBean extends GenericBean<Atividade> {

    @EJB
    private AtividadeService atividadeService;

 
    public void cadastrarAtividade() {
        this.atividadeService.salvar(entidadeNegocio);
        this.inicializarEntidadeNegocio();
    }

    public List<Atividade> getAtividades() {
        return atividadeService.listarTodos();
    }

    public void removerAtividade(Atividade atividade) {
        this.atividadeService.remover(atividade);
    }

    public void alterarAtividade(Atividade atividade) {
       this.atividadeService.atualizar(atividade);
       this.inicializarEntidadeNegocio();
    }

    @Override
    void inicializarEntidadeNegocio() {
        setEntidadeNegocio(atividadeService.getEntidadeNegocio());
    }

}
