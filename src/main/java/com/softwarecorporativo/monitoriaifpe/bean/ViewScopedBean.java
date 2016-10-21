
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
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Edmilson Santana
 * @param <T>
 */
public abstract class ViewScopedBean<T extends EntidadeNegocio> extends Bean implements Serializable {

    private static final long serialVersionUID = 2056768767918963874L;

    protected T entidadeNegocio;

    protected GenericService<T> service;

    protected List<T> entidades = new ArrayList<>();

    @Override
    protected void inicializar() {
        inicializarServico();
        inicializarListaEntidades();
        super.inicializar();
    }

    public T getEntidadeNegocio() {
        return entidadeNegocio;
    }

    public void setEntidadeNegocio(T entidadeNegocio) {
        this.entidadeNegocio = entidadeNegocio;
    }

    public void alterar() {
        super.alterar(service, entidadeNegocio);
    }

    public void cadastrar() {
        super.cadastrar(service, entidadeNegocio);
        inicializarEntidades();
        popularListaEntidades();

    }

    /**
     * Inicializa listas com entidades de negocio, uma única vez, durante a
     * construção do Bean 
     *
     */
    protected void inicializarListaEntidades() {
        this.popularListaEntidades();
    }

    /**
     * Carrega as listas com entidades de negocio 
     *
     */
    protected void popularListaEntidades() {
        entidades = this.service.listarTodos();
    }

    public List<T> getEntidades() {
        if (entidades.isEmpty()) {
            inicializarListaEntidades();
        }
        return entidades;
    }

    public void remover(T entidadeNegocio) {
        super.remover(service, entidadeNegocio);
        popularListaEntidades();
    }

    public GenericService<T> getService() {
        return service;
    }

    protected void setService(GenericService<T> service) {
        this.service = service;
    }

    public void alterarEntidadeCadastrada(RowEditEvent editEvent) {
        entidadeNegocio = (T) editEvent.getObject();
        alterar();
    }

    abstract void inicializarServico();

}
