/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.modelo.atividade;

import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.SituacaoAtividade;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import com.softwarecorporativo.monitoriaifpe.modelo.atividade.validation.ValidaAtividade;

/**
 *
 * @author Edmilson
 */
@Entity
@Table(name = "TB_ATIVIDADE")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "ATIVIDADE_ID"))})
@Access(AccessType.FIELD)
@ValidaAtividade
public class Atividade extends EntidadeNegocio {

    @NotBlank
    @Size(max = 140)
    @Column(name = "ATIVIDADE_DESCRICAO", nullable = false)
    private String descricao;

    @Size(max = 140)
    @Column(name = "ATIVIDADE_OBSERVACAO", nullable = true)
    private String observacoes;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO_ATIVIDADE", nullable = false)
    private SituacaoAtividade situacao;
    
    @NotNull
    @Column(name = "HORARIO_ENTRADA", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horarioEntrada;

    @NotNull
    @Column(name = "HORARIO_SAIDA", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horarioSaida;
    
    @NotNull
    @Column(name = "ATIVIDADE_DATA", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MONITORIA_ID", referencedColumnName = "MONITORIA_ID")
    private Monitoria monitoria;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public SituacaoAtividade getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoAtividade situacao) {
        this.situacao = situacao;
    }

    public Date getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(Date horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public Date getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(Date horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Monitoria getMonitoria() {
        return monitoria;
    }

    public void setMonitoria(Monitoria monitoria) {
        this.monitoria = monitoria;
    }

}
