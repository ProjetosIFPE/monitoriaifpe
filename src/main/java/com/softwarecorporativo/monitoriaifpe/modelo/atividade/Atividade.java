/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.modelo.atividade;

import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tb_atividade")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "id_atividade"))})
@Access(AccessType.FIELD)
@ValidaAtividade
public class Atividade extends EntidadeNegocio {

    private static final long serialVersionUID = 5800521845344151539L;

    @NotBlank
    @Size(max = 140)
    @Column(name = "txt_descricao", nullable = false)
    private String descricao;

    @Size(max = 140)
    @Column(name = "txt_observacao", nullable = true)
    private String observacoes;

    @NotNull
    @Column(name = "dt_inicio_atividade", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;

    @NotNull
    @Column(name = "dt_fim_atividade", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFim;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_monitoria", referencedColumnName = "id_monitoria")
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

    public Monitoria getMonitoria() {
        return monitoria;
    }

    public void setMonitoria(Monitoria monitoria) {
        this.monitoria = monitoria;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public long getCargaHorariaAtividade() {
        long diferenca = getDataFim().getTime() - getDataInicio().getTime();
        return diferenca / (60 * 60 * 1000);
    }

}
