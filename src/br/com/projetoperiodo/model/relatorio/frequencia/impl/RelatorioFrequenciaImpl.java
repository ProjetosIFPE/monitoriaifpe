
package br.com.projetoperiodo.model.relatorio.frequencia.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Temporal;

import br.com.projetoperiodo.model.instituto.monitor.Monitor;
import br.com.projetoperiodo.model.instituto.monitor.impl.MonitorImpl;
import br.com.projetoperiodo.model.instituto.professor.Professor;
import br.com.projetoperiodo.model.instituto.professor.impl.ProfessorImpl;
import br.com.projetoperiodo.model.negocio.entidade.impl.EntidadeNegocioImpl;
import br.com.projetoperiodo.model.relatorio.frequencia.RelatorioFrequencia;
import br.com.projetoperiodo.model.relatorio.semana.Semana;
import br.com.projetoperiodo.model.relatorio.semana.impl.SemanaImpl;

@Entity
@Table(name = "RELATORIO_FREQUENCIA")
@AttributeOverrides({@AttributeOverride(name = "chavePrimaria", column = @Column(name = "RELATORIO_ID") )})
public class RelatorioFrequenciaImpl extends EntidadeNegocioImpl implements RelatorioFrequencia {

	@Column(name = "RELATORIO_MES", nullable = false)
	private int mes;

	@Column(name = "RELATORIO_ANO", nullable = false)
	private int ano;

	@Column(name = "RELATORIO_CARGA_HORARIA", nullable = false)
	private int cargaHorariaMensal;

	@Column(name = "RELATORIO_EDITAL", nullable = false)
	private String edital;

	@ManyToOne(fetch = FetchType.LAZY, optional = true, targetEntity = MonitorImpl.class)
	@JoinColumn(name = "MONITOR_ID", referencedColumnName = "MONITOR_ID")
	private Monitor monitor;

	@Column(name = "DATA_ENTREGA", nullable = false)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dataEntregaRelatorio;

	@ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = ProfessorImpl.class)
	@JoinColumn(name = "PROFESSOR_ID", referencedColumnName = "PROFESSOR_ID")
	private Professor professor;

	@OneToMany(mappedBy = "relatorio", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = SemanaImpl.class)
	private List<Semana> semanas;

	public RelatorioFrequenciaImpl() {
		semanas = new ArrayList<Semana>();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#getMes()
	 */
	public int getMes() {

		return mes;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#setMes(int)
	 */
	public void setMes(int mes) {

		this.mes = mes;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#getAno()
	 */
	public int getAno() {

		return ano;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#setAno(int)
	 */
	public void setAno(int ano) {

		this.ano = ano;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#getCargaHorariaMensal()
	 */
	public int getCargaHorariaMensal() {

		return cargaHorariaMensal;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#setCargaHorariaMensal(int)
	 */
	public void setCargaHorariaMensal(int cargaHorariaMensal) {

		this.cargaHorariaMensal = cargaHorariaMensal;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#getEdital()
	 */
	public String getEdital() {

		return edital;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#setEdital(java.lang.String)
	 */
	public void setEdital(String edital) {

		this.edital = edital;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#getOrientador()
	 */
	public Professor getOrientador() {

		return professor;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#setOrientador(br.com.projetoperiodo.model.instituto.orientador.
	 * Orientador)
	 */
	public void setOrientador(Professor professor) {

		this.professor = professor;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#getMonitor()
	 */
	public Monitor getMonitor() {

		return monitor;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#setMonitor(br.com.projetoperiodo.model.instituto.monitor.impl.
	 * MonitorImpl)
	 */
	public void setMonitor(MonitorImpl monitor) {

		this.monitor = monitor;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#getDataEntregaRelatorio()
	 */
	public Date getDataEntregaRelatorio() {

		return dataEntregaRelatorio;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#setDataEntregaRelatorio(java.util.Date)
	 */
	public void setDataEntregaRelatorio(Date dataEntregaRelatorio) {

		this.dataEntregaRelatorio = dataEntregaRelatorio;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#getSemanas(int)
	 */
	public Semana getSemanas(int index) {

		return semanas.get(index);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequencia#setSemanas(br.com.projetoperiodo.model.relatorio.semana.Semana)
	 */
	public void setSemanas(Semana semana) {

		this.semanas.add(semana);
	}

	@Override
	public void setProfessor(Professor professor) {

		this.professor = professor;

	}

	@Override
	public Professor getProfessor() {

		return this.professor;
	}

}
