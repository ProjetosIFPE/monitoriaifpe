
package br.com.projetoperiodo.model.negocio.controlador;

import br.com.projetoperiodo.model.negocio.entidade.EntidadeNegocio;

public abstract class ControladorNegocioImpl implements ControladorNegocio {

	
	public ControladorNegocioImpl() {
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.negocio.controlador.ControladorNegocio#criarEntidadeNegocio()
	 */
	@Override
	public abstract EntidadeNegocio criarEntidadeNegocio();
	
	@Override
	public abstract String getNomeClasseEntidade();

}
