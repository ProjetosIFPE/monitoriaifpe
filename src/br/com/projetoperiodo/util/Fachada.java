
package br.com.projetoperiodo.util;

import java.util.List;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.instituto.aluno.controller.ControladorAluno;
import br.com.projetoperiodo.model.instituto.aluno.controller.impl.ControladorAlunoImpl;
import br.com.projetoperiodo.model.instituto.curso.controller.ControladorCurso;
import br.com.projetoperiodo.model.instituto.curso.controller.impl.ControladorCursoImpl;
import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.model.instituto.disciplina.controller.ControladorDisciplina;
import br.com.projetoperiodo.model.instituto.disciplina.controller.impl.ControladorDisciplinaImpl;
import br.com.projetoperiodo.model.instituto.monitor.Monitor;
import br.com.projetoperiodo.model.instituto.monitor.controller.ControladorMonitor;
import br.com.projetoperiodo.model.instituto.monitor.controller.impl.ControladorMonitorImpl;
import br.com.projetoperiodo.model.instituto.periodo.controller.ControladorPeriodo;
import br.com.projetoperiodo.model.instituto.periodo.controller.impl.ControladorPeriodoImpl;
import br.com.projetoperiodo.model.instituto.professor.Professor;
import br.com.projetoperiodo.model.instituto.professor.controller.ControladorProfessor;
import br.com.projetoperiodo.model.instituto.professor.controller.impl.ControladorProfessorImpl;
import br.com.projetoperiodo.model.negocio.entidade.EntidadeNegocio;
import br.com.projetoperiodo.model.relatorio.atividade.controller.ControladorAtividade;
import br.com.projetoperiodo.model.relatorio.atividade.controller.impl.ControladorAtividadeImpl;
import br.com.projetoperiodo.model.relatorio.frequencia.RelatorioFrequencia;
import br.com.projetoperiodo.model.relatorio.frequencia.controller.ControladorRelatorio;
import br.com.projetoperiodo.model.relatorio.frequencia.controller.impl.ControladorRelatorioImpl;
import br.com.projetoperiodo.model.relatorio.semana.controller.ControladorSemana;
import br.com.projetoperiodo.model.relatorio.semana.controller.impl.ControladorSemanaImpl;
import br.com.projetoperiodo.model.usuario.Usuario;
import br.com.projetoperiodo.model.usuario.controller.ControladorUsuario;
import br.com.projetoperiodo.model.usuario.controller.impl.ControladorUsuarioImpl;
import br.com.projetoperiodo.util.constantes.enumeracoes.Modalidade;
import br.com.projetoperiodo.util.exception.NegocioException;
import br.com.projetoperiodo.util.persistencia.CreatorFabrica;

public class Fachada {

	private static Fachada fachada = null;

	private Fachada() { }

	public synchronized static Fachada getInstance() {

		if (fachada == null) {
			fachada = new Fachada();
		}
		return fachada;
	}

	public ControladorUsuario getControladorUsuario() {

		return new ControladorUsuarioImpl();
	}

	public ControladorAtividade getControladorAtividade() {

		return new ControladorAtividadeImpl();
	}

	public ControladorSemana getControladorSemana() {

		return new ControladorSemanaImpl();
	}

	
	public ControladorMonitor getControladorMonitor() {

		return new ControladorMonitorImpl();
	}

	public ControladorAluno getControladorAluno() {

		return new ControladorAlunoImpl();
	}
	
	public ControladorProfessor getControladorProfessor(){
		
		return new ControladorProfessorImpl();
	}

	public ControladorDisciplina getControladorDisciplina() {

		return new ControladorDisciplinaImpl();
	}

	public ControladorCurso getControladorCurso() {

		return new ControladorCursoImpl();
	}

	public ControladorPeriodo getControladorPeriodo() {

		return new ControladorPeriodoImpl();
	}

	public ControladorRelatorio getControladorRelatorio() {

		return new ControladorRelatorioImpl();
	}

	public EntidadeNegocio criarDisciplina() {
		return this.getControladorDisciplina().criarEntidadeNegocio();
	}

	public EntidadeNegocio criarAluno() {
		return this.getControladorAluno().criarEntidadeNegocio();
	}
	
	public EntidadeNegocio criarProfessor(){
		return this.getControladorProfessor().criarEntidadeNegocio();
	}
	
	public EntidadeNegocio criarUsuario() {
		return this.getControladorUsuario().criarEntidadeNegocio();
	}
	public List<RelatorioFrequencia> buscarRelatorios(long chavePrimaria) {

		Monitor monitor = (Monitor) this.getControladorMonitor().criarEntidadeNegocio();
		monitor.setChavePrimaria(chavePrimaria);
		ControladorRelatorio controladorRelatorio = this.getControladorRelatorio();
		return controladorRelatorio.buscarRelatoriosDeMonitor(monitor);
	}

	public List<Disciplina> listarDisciplinasDeAluno(EntidadeNegocio entidade) {
		Aluno aluno = (Aluno) entidade;
		return this.getControladorDisciplina().listarDisciplinasDeAluno(aluno);
	}

	public boolean validarCadastroMonitoria(EntidadeNegocio entidade) {
		Monitor monitor = (Monitor) entidade;
		return this.getControladorMonitor().validarCadastroMonitoria(monitor);
	}

	public EntidadeNegocio criarMonitoria(Usuario usuario, EntidadeNegocio entidade, Modalidade modalidade) {
		Aluno aluno = (Aluno) usuario;
		Disciplina disciplina = (Disciplina) entidade;
		return this.getControladorMonitor().criarMonitoriaDeAluno(aluno, disciplina, modalidade);
	}

	public EntidadeNegocio cadastrarMonitoria(EntidadeNegocio entidade) {
		Monitor monitor = (Monitor) entidade;
		return this.getControladorMonitor().cadastrarMonitoria(monitor);
	}

	public EntidadeNegocio buscarDisciplina(String descricao) throws NegocioException {
		return this.getControladorDisciplina().buscarDisciplina(descricao);
	}

	public void preCadastroRelatoriosMonitor(EntidadeNegocio entidadeNegocio) {

		Monitor monitor = (Monitor) entidadeNegocio;
		ControladorRelatorio controladorRelatorio = this.getControladorRelatorio();
		controladorRelatorio.prepararRelatoriosDoMonitor(monitor);
	}

	public List<Monitor> buscarMonitorias(Aluno aluno) {

		ControladorMonitor controladorMonitor = this.getControladorMonitor();
		return controladorMonitor.listarMonitorias(aluno);
	}

	public RelatorioFrequencia buscarRelatorioMensal(EntidadeNegocio entidade, int mes) {

		Monitor monitor = (Monitor) entidade;
		ControladorRelatorio controladorFrequencia = this.getControladorRelatorio();
		return controladorFrequencia.buscarRelatoriosDeMonitorPorMes(monitor, mes);
	}

	public EntidadeNegocio buscarMonitoria(long chavePrimaria) {

		ControladorMonitor controladorMonitor = this.getControladorMonitor();
		return controladorMonitor.buscarMonitoria(chavePrimaria);
	}
	public EntidadeNegocio autenticarUsuario(Usuario usuario) throws NegocioException {
		return this.getControladorUsuario().autenticarUsuario(usuario);
	}
	public void cadastrarAluno(EntidadeNegocio entidade) {
		Aluno aluno = (Aluno)entidade;
		this.getControladorAluno().cadastrarAluno(aluno);
	}
	public void cadastrarProfessor(EntidadeNegocio entidade){
		Professor professor = (Professor) entidade;
		this.getControladorProfessor().cadastrarProfessor(professor);
	}
	
	public void atualizarRelatorio(EntidadeNegocio entidade) {

		RelatorioFrequencia relatorio = (RelatorioFrequencia) entidade;
		this.getControladorRelatorio().atualizarRelatorio(relatorio);
	}

	public byte[] gerarDocumentoDeRelatorio(EntidadeNegocio entidade) {

		RelatorioFrequencia relatorio = (RelatorioFrequencia) entidade;
		return this.getControladorRelatorio().gerarDocumentoDeRelatorio(relatorio);
	}

	public EntidadeNegocio buscarCursoPadraoDeAluno() {

		return this.getControladorCurso().buscarCursoPadrao();
	}

	public void removerMonitoriaDeAluno(long chavePrimaria) {
		this.getControladorMonitor().removerMonitoria(chavePrimaria);
	}
	
	public void alterarEstrategiaDePersistencia(String tipo) {
		CreatorFabrica.criarFabricaDAO(tipo);
	}
	
	public void alterarSenhaUsuario(EntidadeNegocio entidade) {
		Usuario usuario = (Usuario)entidade;
		this.getControladorUsuario().alterarSenha(usuario);
	}
	
	public List<Disciplina> listarDisciplinasDeProfessor(EntidadeNegocio entidade) {
		Professor professor = (Professor)entidade;
		return this.getControladorDisciplina().listarDisciplinasDeProfessor(professor);
	}
	
	public boolean verificaPapelDoUsuario(Usuario usuario) {
		return this.getControladorAluno().verificarPapelDeAlunoDoUsuario(usuario);
	}
	
	public EntidadeNegocio alterarSenhaUsuario(String senhaAntiga, String senhaNova) {
		Usuario usuario = (Usuario)entidade;
		this.getControladorUsuario().alterarSenha(senhaAntiga, novaSenha);
	}

}
