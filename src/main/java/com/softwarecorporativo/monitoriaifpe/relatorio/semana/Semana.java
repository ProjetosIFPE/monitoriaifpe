package com.softwarecorporativo.monitoriaifpe.relatorio.semana;

import com.softwarecorporativo.monitoriaifpe.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.relatorio.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.relatorio.frequencia.RelatorioFrequencia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_SEMANA")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "SEMANA_ID"))})
@Access(AccessType.FIELD)
public class Semana extends EntidadeNegocio  {

    @Column(name = "SEMANA_DESCRICAO", nullable = true)
    private String descricao;
    @Column(name = "SEMANA_OBS", nullable = true)
    private String observacoes;
    @OneToMany(mappedBy = "semana",
            cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<Atividade> atividades;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "RELATORIO_ID",   referencedColumnName = "RELATORIO_ID")
    private RelatorioFrequencia relatorio;

   
    public String getDescricao() {
        return this.descricao;
    }

    
    public String getObservacoes() {
        return this.observacoes;
    }

   
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

   
    public Atividade getAtividade(int index) {
        if ( atividades == null ) {
            atividades = new ArrayList<>();
        }
        return atividades.get(index);
    }

    public List<Atividade> getAtividades() {
        return this.atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    
    public void addAtividade(Atividade atividade) {
        if ( atividades == null ) {
            atividades = new ArrayList<>();
        }
        atividade.setSemana(this);
        this.atividades.add(atividade);
    }

   
    public RelatorioFrequencia getRelatorio() {

        return relatorio;
    }

    
    public void setRelatorio(RelatorioFrequencia relatorio) {

        this.relatorio = relatorio;
    }

}
