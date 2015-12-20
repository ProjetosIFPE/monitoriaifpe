
package br.com.projetoperiodo.model.instituto.monitor.controller.impl;

import java.util.Calendar;
import java.util.List;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.model.instituto.monitor.Monitoria;
import br.com.projetoperiodo.model.instituto.monitor.controller.ControladorMonitor;
import br.com.projetoperiodo.model.instituto.monitor.impl.MonitorImpl;
import br.com.projetoperiodo.model.instituto.periodo.Periodo;
import br.com.projetoperiodo.model.instituto.periodo.controller.ControladorPeriodo;
import br.com.projetoperiodo.model.negocio.controlador.ControladorNegocioImpl;
import br.com.projetoperiodo.model.negocio.entidade.EntidadeNegocio;
import br.com.projetoperiodo.util.Util;
import br.com.projetoperiodo.util.constantes.enumeracoes.Modalidade;
import br.com.projetoperiodo.util.exception.NegocioException;
import br.com.projetoperiodo.util.fachada.Fachada;
import br.com.projetoperiodo.util.fachada.Persistencia;

public class ControladorMonitorImpl extends ControladorNegocioImpl implements ControladorMonitor {
	
	public ControladorMonitorImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.monitor.controller.impl.
	 * ControladorMonitor#criarEntidadeNegocio()
	 */
	@Override
	public EntidadeNegocio criarEntidadeNegocio() {

		return new MonitorImpl();
	}

	@Override
	public Monitoria buscarMonitoriasDeAluno(Aluno aluno) throws NegocioException {

		Monitoria monitor = (Monitoria) Persistencia.getInstance().buscarMonitoriasDeAluno(aluno.getChavePrimaria());
		return monitor;
	}

	@Override
	public Monitoria criarMonitoriaDeAluno(Aluno aluno, Disciplina disciplina, Modalidade modalidade) {

		ControladorPeriodo controladorPeriodo = Fachada.getInstance().getControladorPeriodo();
		Monitoria monitoria = (Monitoria) criarEntidadeNegocio();
		monitoria.setDisciplina(disciplina);
		monitoria.setModalidade(modalidade);
		monitoria.setAluno(aluno);
		monitoria.setHabilitado(Boolean.TRUE);
		monitoria.setUltimaAlteracao(Calendar.getInstance().getTime());
		Periodo periodoCorrente = controladorPeriodo.gerarNovoPeriodoCorrente();
		monitoria.setPeriodo(periodoCorrente);

		return monitoria;
	}

	@Override
	public Monitoria cadastrarMonitoria(Monitoria monitoria) {

		return (Monitoria) Persistencia.getInstance().salvarMonitoria(monitoria);
	}

	@Override
	public boolean validarCadastroMonitoria(Monitoria monitor) {

		boolean cadastroValido = Boolean.TRUE;
		boolean possuiCadastro;
		long qtdMonitoriasEmProgresso;

		possuiCadastro = verificaExistenciaCadastroMonitoria(monitor);
		qtdMonitoriasEmProgresso = buscarQuantidadeMonitoriasEmProgressoDeAluno(monitor);

		if (possuiCadastro || qtdMonitoriasEmProgresso > 0) {
			cadastroValido = Boolean.FALSE;
		}
		return cadastroValido;
	}

	@Override
	public boolean verificaExistenciaCadastroMonitoria(Monitoria monitor) {

		List<Monitoria> lista = Persistencia.getInstance().verificarCadastroDeMonitoria(monitor);
		if (lista.isEmpty()) {
			return false;
		}
		return true;
	}

	private Long buscarQuantidadeMonitoriasEmProgressoDeAluno(Monitoria monitor) {

		return Persistencia.getInstance().quantidadeMonitoriasDeAluno(monitor.getAluno().getChavePrimaria());
	}

	@Override
	public List<Monitoria> listarMonitorias(Aluno aluno) {

		return Persistencia.getInstance().buscarMonitoriasDeAluno(aluno.getChavePrimaria());
	}

	@Override
	public Monitoria buscarMonitoria(long chavePrimaria) {
		
		return (Monitoria) Persistencia.getInstance().buscarMonitoria(chavePrimaria);
	}

	@Override
	public void removerMonitoria(long chavePrimaria) {

		Monitoria monitoria = (Monitoria) this.criarEntidadeNegocio();
		monitoria.setChavePrimaria(chavePrimaria);
		Fachada.getInstance().getControladorRelatorio().removerRelatoriosDeMonitoria(monitoria);
		Persistencia.getInstance().removerMonitoria(monitoria);
	}

	@Override
	public List<Monitoria> buscarMonitoriasDeDiscplina(Disciplina disciplina) {

		return Persistencia.getInstance().buscarMonitoriasDeDisciplina(disciplina.getChavePrimaria());
	}

	@Override
	public String getNomeClasseEntidade() {

		return MonitorImpl.class.getSimpleName();
	}
}
