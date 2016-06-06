/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.modelo.relatorio.frequencia;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Edmilson Santana
 */
public class SemanaDTO implements Serializable {

    private static final long serialVersionUID = 1187906149482262433L;

    private String descricao;

    private String observacao;

    private List<DiaDTO> dias;

    public List<DiaDTO> getDias() {
        return dias;
    }

    public void setDias(List<DiaDTO> dias) {
        this.dias = dias;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
