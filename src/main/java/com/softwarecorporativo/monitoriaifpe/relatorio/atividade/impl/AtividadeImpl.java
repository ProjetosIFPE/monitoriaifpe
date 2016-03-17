package com.softwarecorporativo.monitoriaifpe.relatorio.atividade.impl;



import com.softwarecorporativo.monitoriaifpe.relatorio.semana.impl.SemanaImpl;
import com.softwarecorporativo.monitoriaifpe.negocio.impl.EntidadeNegocioImpl;
import com.softwarecorporativo.monitoriaifpe.relatorio.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.relatorio.semana.Semana;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "ATIVIDADE")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "ATIVIDADE_ID"))})
public class AtividadeImpl extends EntidadeNegocioImpl implements Atividade {

    @Column(name = "HORARIO_SAIDA", nullable = true)
    private String horario_entrada;
    @Column(name = "HORARIO_ENTRADA", nullable = true)
    private String horario_saida;
    @Column(name = "ATIVIDADE_DATA", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;
    @ManyToOne(fetch = FetchType.LAZY, optional = true, targetEntity = SemanaImpl.class)
    @JoinColumn(name = "SEMANA_ID", referencedColumnName = "SEMANA_ID")
    private Semana semana;

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.atividade.impl.Atividade#getData()
     */
    public Date getData() {
        return this.data;
    }

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.atividade.impl.Atividade#setData(java.util.Date)
     */
    public void setData(Date data) {
        this.data = data;
    }

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.atividade.impl.Atividade#getHorarioEntrada()
     */
    public String getHorarioEntrada() {

        return horario_entrada;
    }

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.atividade.impl.Atividade#setHorarioEntrada(java.lang.String)
     */
    public void setHorarioEntrada(String horarioEntrada) {

        this.horario_entrada = horarioEntrada;
    }

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.atividade.impl.Atividade#getHorarioSaida()
     */
    public String getHorarioSaida() {

        return horario_saida;
    }

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.atividade.impl.Atividade#setHorarioSaida(java.lang.String)
     */
    public void setHorarioSaida(String horarioSaida) {

        this.horario_saida = horarioSaida;
    }

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.atividade.impl.Atividade#getSemana()
     */
    public Semana getSemana() {

        return semana;
    }

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.atividade.impl.Atividade#setSemana(br.com.projetoperiodo.model.relatorio.semana.Semana)
     */
    public void setSemana(Semana semana) {

        this.semana = semana;
    }

}
