package com.softwarecorporativo.monitoriaifpe.relatorio.semana.impl;

import com.softwarecorporativo.monitoriaifpe.relatorio.frequencia.impl.RelatorioFrequenciaImpl;
import com.softwarecorporativo.monitoriaifpe.negocio.impl.EntidadeNegocioImpl;
import com.softwarecorporativo.monitoriaifpe.relatorio.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.relatorio.atividade.impl.AtividadeImpl;
import com.softwarecorporativo.monitoriaifpe.relatorio.frequencia.RelatorioFrequencia;
import com.softwarecorporativo.monitoriaifpe.relatorio.semana.Semana;
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
@Table(name = "SEMANA")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "SEMANA_ID"))})
@Access(AccessType.FIELD)
public class SemanaImpl extends EntidadeNegocioImpl implements Semana {

    @Column(name = "SEMANA_DESCRICAO", nullable = true)
    private String descricao;
    @Column(name = "SEMANA_OBS", nullable = true)
    private String observacoes;
    @OneToMany(mappedBy = "semana",
            cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.EAGER, targetEntity = AtividadeImpl.class)
    private List<Atividade> atividades;
    @ManyToOne(fetch = FetchType.LAZY, optional = true, targetEntity = RelatorioFrequenciaImpl.class)
    @JoinColumn(name = "RELATORIO_ID",   referencedColumnName = "RELATORIO_ID")
    private RelatorioFrequencia relatorio;

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.semana.impl.Semana#getDescricao()
     */
    @Override
    public String getDescricao() {
        return this.descricao;
    }

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.semana.impl.Semana#getObservacoes()
     */
    @Override
    public String getObservacoes() {
        return this.observacoes;
    }

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.semana.impl.Semana#setDescricao(java.lang.String)
     */
    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.semana.impl.Semana#setObservacoes(java.lang.String)
     */
    @Override
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.semana.impl.Semana#getAtividades(int)
     */
    @Override
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

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.semana.impl.Semana#setAtividades(br.com.projetoperiodo.model.relatorio.atividade.Atividade)
     */
    @Override
    public void setAtividades(Atividade atividade) {
        if ( atividades == null ) {
            atividades = new ArrayList<>();
        }
        atividade.setSemana(this);
        this.atividades.add(atividade);
    }

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.semana.impl.Semana#getRelatorio()
     */
    @Override
    public RelatorioFrequencia getRelatorio() {

        return relatorio;
    }

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.semana.impl.Semana#setRelatorio(br.com.projetoperiodo.model.relatorio.frequencia.RelatorioFrequencia)
     */
    @Override
    public void setRelatorio(RelatorioFrequencia relatorio) {

        this.relatorio = relatorio;
    }

}
