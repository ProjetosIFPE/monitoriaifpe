
package br.com.projetoperiodo.model.relatorio.semana.controller.impl;

import java.util.Calendar;
import java.util.List;

import br.com.projetoperiodo.model.negocio.Mediador;
import br.com.projetoperiodo.model.negocio.controlador.ControladorNegocioImpl;
import br.com.projetoperiodo.model.negocio.entidade.EntidadeNegocio;
import br.com.projetoperiodo.model.relatorio.atividade.controller.ControladorAtividade;
import br.com.projetoperiodo.model.relatorio.frequencia.RelatorioFrequencia;
import br.com.projetoperiodo.model.relatorio.semana.Semana;
import br.com.projetoperiodo.model.relatorio.semana.controller.ControladorSemana;
import br.com.projetoperiodo.model.relatorio.semana.impl.SemanaImpl;
import br.com.projetoperiodo.util.fachada.Fachada;
import br.com.projetoperiodo.util.fachada.Persistencia;

public class ControladorSemanaImpl extends ControladorNegocioImpl implements ControladorSemana {

	public ControladorSemanaImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.relatorio.semana.controller.ControladorSemana#criarEntidadeNegocio()
	 */
	@Override
	public EntidadeNegocio criarEntidadeNegocio() {

		return new SemanaImpl();
	}

	@Override
	public void cadastrarSemanasComRelatorio(RelatorioFrequencia relatorio) {

		for (int i = 0; i < 5; i++) {
			Semana semana = (Semana) this.criarEntidadeNegocio();
			semana.setRelatorio(relatorio);
			semana.setUltimaAlteracao(Calendar.getInstance().getTime());
			Persistencia.getInstance().salvarSemana(semana);
			Mediador.getInstance().cadastrarAtividade(semana);

		}
	}

	@Override
	public List<Semana> buscarSemanasDeRelatorio(RelatorioFrequencia relatorio) {

		return Persistencia.getInstance().buscarSemanasDeRelatorio(relatorio.getChavePrimaria());
	}

	@Override
	public void removerSemana(Semana semana) {

		Persistencia.getInstance().removerSemana(semana);
	}

	@Override
	public String getNomeClasseEntidade() {

		return SemanaImpl.class.getSimpleName();
	}

}
