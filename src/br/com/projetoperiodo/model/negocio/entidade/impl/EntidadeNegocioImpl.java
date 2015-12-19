package br.com.projetoperiodo.model.negocio.entidade.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import br.com.projetoperiodo.model.negocio.entidade.EntidadeNegocio;

@MappedSuperclass
public abstract class EntidadeNegocioImpl implements EntidadeNegocio, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1874851173773909132L;

	@Column( name = "ULTIMA_ALTERACAO", nullable = true)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date ultimaAlteracao;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	protected long chavePrimaria;

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public long getChavePrimaria() {
		return chavePrimaria;
	}

	public void setChavePrimaria(long chavePrimaria) {
		this.chavePrimaria = chavePrimaria;
	}
	
}
