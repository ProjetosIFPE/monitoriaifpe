package com.softwarecorporativo.monitoriaifpe.relatorio.frequencia.impl;

import com.softwarecorporativo.monitoriaifpe.relatorio.semana.impl.SemanaImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.instituto.monitoria.impl.MonitoriaImpl;
import com.softwarecorporativo.monitoriaifpe.negocio.impl.EntidadeNegocioImpl;

import com.softwarecorporativo.monitoriaifpe.util.constantes.Situacao;
import com.softwarecorporativo.monitoriaifpe.relatorio.frequencia.RelatorioFrequencia;
import com.softwarecorporativo.monitoriaifpe.relatorio.semana.Semana;
import com.softwarecorporativo.monitoriaifpe.util.Util;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RELATORIO_FREQUENCIA")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "RELATORIO_ID"))})
public class RelatorioFrequenciaImpl extends EntidadeNegocioImpl implements RelatorioFrequencia {

    @Column(name = "RELATORIO_MES", nullable = false)
    private int mes;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = MonitoriaImpl.class)
    @JoinColumn(name = "MONITOR_ID", referencedColumnName = "MONITOR_ID")
    private Monitoria monitoria;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO_RELATORIO", nullable = false, columnDefinition = "ENUM('ESPERA', 'APROVADO')")
    private Situacao situacao;

    @OneToMany(mappedBy = "relatorio", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = SemanaImpl.class)
    private List<Semana> semanas;

    public RelatorioFrequenciaImpl() {
        
    }

    

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#getMes()
     */
    @Override
    public int getMes() {

        return mes;
    }

    @Override
    public String getDescricaoMes() {
        return Util.obterNomeMes(this.mes);
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#setMes(int)
     */
    @Override
    public void setMes(int mes) {

        this.mes = mes;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#getMonitor()
     */
    @Override
    public Monitoria getMonitoria() {

        return monitoria;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#setMonitor(br.com.projetoperiodo.model.instituto.monitor.impl.
	 * MonitorImpl)
     */
    @Override
    public void setMonitoria(Monitoria monitoria) {

        this.monitoria = monitoria;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#getSemanas(int)
     */
    @Override
    public Semana getSemana(int index) {
        if ( semanas == null ) {
            semanas = new ArrayList<>();
        }
        return semanas.get(index);
    }
    
    @Override
    public List<Semana> getSemanas() {
        return semanas;
    }
    
    @Override
    public void setSemanas(List<Semana> semanas) {
        this.semanas = semanas;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#setSemanas(br.com.projetoperiodo.model.relatorio.semana.Semana)
     */
    @Override
    public void setSemanas(Semana semana) {
        if ( semanas == null ) {
            semanas = new ArrayList<>();
        }
        semana.setRelatorio(this);
        this.semanas.add(semana);
    }

    @Override
    public Situacao getSituacao() {
        return situacao;
    }

    @Override
    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }
}
