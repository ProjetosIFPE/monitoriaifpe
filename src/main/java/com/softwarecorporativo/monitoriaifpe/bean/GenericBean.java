/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.servico.GenericService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

/**
 *
 * @author Edmilson Santana
 * @param <T>
 */
public abstract class GenericBean<T extends EntidadeNegocio> implements Serializable {

    private static final long serialVersionUID = 2056768767918963874L;

    protected T entidadeNegocio;

    protected GenericService<T> service;

    protected List<T> entidades = new ArrayList<>();

    @PostConstruct
    protected void inicializar() {
        inicializarEntidadeNegocio();
        inicializarServico();
    }

    public void adicionarMensagemView(String mensagem) {
        this.adicionarMensagemComponente(null, mensagem);
    }

    public void adicionarMensagemComponente(String componente, String mensagem) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(componente, new FacesMessage(mensagem));
    }

    public T getEntidadeNegocio() {
        return entidadeNegocio;
    }

    public void setEntidadeNegocio(T entidadeNegocio) {
        this.entidadeNegocio = entidadeNegocio;
    }

    public void gravar() {
        if (this.entidadeNegocio.getChavePrimaria() != null) {
            this.service.atualizar(entidadeNegocio);
        } else {
            this.service.salvar(entidadeNegocio);
        }

        popularEntidades();

        inicializarEntidadeNegocio();
    }

    protected void popularEntidades() {
        entidades = this.service.listarTodos();
    }

    public List<T> getEntidades() {

        if (entidades.isEmpty()) {
            popularEntidades();
        }
        return entidades;
    }

    public void remover(T entidadeNegocio) {
        this.service.remover(entidadeNegocio);
        popularEntidades();
    }

    public GenericService<T> getService() {
        return service;
    }

    protected void setService(GenericService<T> service) {
        this.service = service;
    }

    abstract void inicializarEntidadeNegocio();

    abstract void inicializarServico();

}
