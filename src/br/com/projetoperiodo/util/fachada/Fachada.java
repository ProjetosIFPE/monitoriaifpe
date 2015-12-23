
package br.com.projetoperiodo.util.fachada;

import java.util.List;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.instituto.aluno.controller.ControladorAluno;
import br.com.projetoperiodo.model.instituto.aluno.controller.impl.ControladorAlunoImpl;
import br.com.projetoperiodo.model.instituto.curso.controller.ControladorCurso;
import br.com.projetoperiodo.model.instituto.curso.controller.impl.ControladorCursoImpl;
import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.model.instituto.disciplina.controller.ControladorDisciplina;
import br.com.projetoperiodo.model.instituto.disciplina.controller.impl.ControladorDisciplinaImpl;
import br.com.projetoperiodo.model.instituto.monitor.Monitoria;
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
import br.com.projetoperiodo.util.constantes.enumeracoes.Situacao;
import br.com.projetoperiodo.util.exception.NegocioException;
import br.com.projetoperiodo.util.exception.ProjetoException;

public class Fachada {

	private static Fachada instance = null;

	private Fachada() { }

	public synchronized static Fachada getInstance() {

		if (instance == null) {
			instance = new Fachada();
		}
		return instance;
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
	
	public EntidadeNegocio criarCurso(){
		return this.getControladorCurso().criarEntidadeNegocio();
	}
	
	public EntidadeNegocio criarProfessor(){
		return this.getControladorProfessor().criarEntidadeNegocio();
	}
	
	public EntidadeNegocio criarUsuario() {
		return this.getControladorUsuario().criarEntidadeNegocio();
	}
	
	public EntidadeNegocio criarPeriodo() {
		return this.getControladorPeriodo().criarEntidadeNegocio();
	}
	public List<RelatorioFrequencia> buscarRelatorios(long chavePrimaria) {

		Monitoria monitor = (Monitoria) this.getControladorMonitor().criarEntidadeNegocio();
		monitor.setChavePrimaria(chavePrimaria);
		ControladorRelatorio controladorRelatorio = this.getControladorRelatorio();
		return controladorRelatorio.buscarRelatoriosDeMonitor(monitor);
	}

	public List<Disciplina> listarDisciplinasDeAluno(EntidadeNegocio entidade) throws ProjetoException {
		Aluno aluno = (Aluno) entidade;
		return this.getControladorDisciplina().listarDisciplinasDeAluno(aluno);
	}

	public boolean validarCadastroMonitoria(EntidadeNegocio entidade) {
		Monitoria monitor = (Monitoria) entidade;
		return this.getControladorMonitor().validarCadastroMonitoria(monitor);
	}

	public EntidadeNegocio criarMonitoria(Usuario usuario, EntidadeNegocio entidade, Modalidade modalidade) {
		Aluno aluno = (Aluno) usuario;
		Disciplina disciplina = (Disciplina) entidade;
		return this.getControladorMonitor().criarMonitoriaDeAluno(aluno, disciplina, modalidade);
	}

	public EntidadeNegocio cadastrarMonitoria(EntidadeNegocio entidade) {
		Monitoria monitor = (Monitoria) entidade;
		return this.getControladorMonitor().cadastrarMonitoria(monitor);
	}

	public EntidadeNegocio buscarDisciplina(String descricao) throws ProjetoException {
		return this.getControladorDisciplina().buscarDisciplina(descricao);
	}
	
	public EntidadeNegocio buscarDisciplina(long chavePrimaria) {
		return this.getControladorDisciplina().buscarDisciplina(chavePrimaria);
	}

	public void preCadastroRelatoriosMonitor(EntidadeNegocio entidadeNegocio) {

		Monitoria monitor = (Monitoria) entidadeNegocio;
		ControladorRelatorio controladorRelatorio = this.getControladorRelatorio();
		controladorRelatorio.prepararRelatoriosDoMonitor(monitor);
	}

	public List<Monitoria> buscarMonitorias(Aluno aluno) {

		ControladorMonitor controladorMonitor = this.getControladorMonitor();
		return controladorMonitor.listarMonitorias(aluno);
	}

	public RelatorioFrequencia buscarRelatorioMensal(EntidadeNegocio entidade, int mes) {

		Monitoria monitor = (Monitoria) entidade;
		ControladorRelatorio controladorFrequencia = this.getControladorRelatorio();
		return controladorFrequencia.buscarRelatoriosDeMonitoriaPorMes(monitor, mes);
	}

	public EntidadeNegocio buscarMonitoria(long chavePrimaria) throws NegocioException {

		ControladorMonitor controladorMonitor = this.getControladorMonitor();
		return controladorMonitor.buscarMonitoria(chavePrimaria);
	}
	
	public EntidadeNegocio buscarAluno(String matricula) throws ProjetoException{
		ControladorAluno controladorAluno = this.getControladorAluno();
		return controladorAluno.buscarAluno(matricula);
	}
	
	public EntidadeNegocio autenticarUsuario(Usuario usuario) throws ProjetoException {
		return this.getControladorUsuario().autenticarUsuario(usuario);
	}
	public void cadastrarAluno(EntidadeNegocio entidade) throws ProjetoException {
		Aluno aluno = (Aluno)entidade;
		this.getControladorAluno().cadastrarAluno(aluno);
	}
	public Professor cadastrarProfessor(EntidadeNegocio entidade) throws ProjetoException{
		Professor professor = (Professor) entidade;
		return this.getControladorProfessor().cadastrarProfessor(professor);
	}
	public void atualizarDisciplina(EntidadeNegocio entidade ) {
		Disciplina disciplina = (Disciplina)entidade;
		this.getControladorDisciplina().atualizarDisciplina(disciplina);
	}
	
	public void atualizarRelatorio(EntidadeNegocio entidade) {

		RelatorioFrequencia relatorio = (RelatorioFrequencia) entidade;
		this.getControladorRelatorio().atualizarRelatorio(relatorio);
	}

	public byte[] gerarDocumentoDeRelatorio(EntidadeNegocio entidade, Usuario requisitante) throws ProjetoException {

		RelatorioFrequencia relatorio = (RelatorioFrequencia) entidade;
		return this.getControladorRelatorio().gerarDocumentoDeRelatorio(relatorio, requisitante);
	}

	public EntidadeNegocio buscarCursoPadraoDeAluno() {

		return this.getControladorCurso().buscarCursoPadrao();
	}

	public void removerMonitoriaDeAluno(long chavePrimaria) {
		this.getControladorMonitor().removerMonitoria(chavePrimaria);
	}
	
	public void retirarProfessorDeDisciplina( long chaveDisciplina) {
		this.getControladorDisciplina().retirarVinculoDeProfessorDaDisciplina(chaveDisciplina);
	}
	
	public List<Disciplina> listarDisciplinasSemProfessor() {
		return this.getControladorDisciplina().buscarDisciplinasSemProfessor();
	}
	public Usuario alterarSenhaUsuario(EntidadeNegocio entidade, String senhaNova, String senhaAntiga) throws ProjetoException {
		Usuario usuario = (Usuario)entidade;
		return this.getControladorUsuario().alterarSenha(usuario, senhaNova, senhaAntiga);
	}
	
	public List<Disciplina> listarDisciplinasDeProfessor(EntidadeNegocio entidade) {
		Professor professor = (Professor)entidade;
		return this.getControladorDisciplina().listarDisciplinasDeProfessor(professor);
	}
	
	public boolean verificaPapelDoUsuario(Usuario usuario) {
		return this.getControladorAluno().verificarPapelDeAlunoDoUsuario(usuario);
	}
	
	public List<Monitoria> buscarMonitoriasDeDisciplina(EntidadeNegocio entidade) {
		Disciplina disciplina = (Disciplina)entidade;
		return this.getControladorMonitor().buscarMonitoriasDeDiscplina(disciplina);
	}
	
	public void aprovarRelatorio( EntidadeNegocio entidade ) {
		RelatorioFrequencia relatorio = (RelatorioFrequencia)entidade;
		this.getControladorRelatorio().aprovarRelatorio(relatorio);
	}
	
	public List<Situacao> buscarSituacaoDeRelatorios(EntidadeNegocio entidade) {
		Monitoria monitor = (Monitoria)entidade;
		return this.getControladorRelatorio().buscaSituacaoDosRelatoriosDeMonitoria(monitor);
	}
	
	public boolean compararSenhasDeUsuario(String senha, Usuario usuario) {
		return this.getControladorUsuario().compararSenhas(senha, usuario);
	}
	
	public List<Disciplina> listarDisciplinasCadastradas() {
		return this.getControladorDisciplina().listarDisciplinasCadastradas();
	}
	
	public List<Disciplina> listarDisciplinasCadastradasComProfessor() {
		return this.getControladorDisciplina().buscarDisciplinasComProfessor();
	}
	public void alterarConfiguracao( String[] configuracoes ) {
		this.getControladorUsuario().alterarConfiguracoesDePersistencia(configuracoes[0], configuracoes[1]);
	}
	public EntidadeNegocio buscarUsuario(Usuario usuario) throws ProjetoException {
		return this.getControladorUsuario().buscarCadastroDeUsuario(usuario);
	}
	
	public void encaminharSenhaParaUsuario(Usuario usuario) throws ProjetoException {
		this.getControladorUsuario().encaminharSenhaParaUsuario(usuario);
	}
	
	public void adicionarDisciplinaEmCadastroDeAluno(Usuario usuario, EntidadeNegocio entidade) {
		Aluno aluno = (Aluno)usuario;
		Disciplina disciplina = (Disciplina)entidade;
		this.getControladorAluno().adicionarDisciplinaEmCadastroDeAluno(aluno, disciplina);
	}
	
	public List<Disciplina> listarDisciplinasDisponiveisParaAluno(Usuario usuario) {
		Aluno aluno = (Aluno)usuario;
		return this.getControladorDisciplina().listarDisciplinasComProfessorDisponiveisParaAluno(aluno);
	}
	
	public void enviarEmailDeLogDoSistema(String log) throws ProjetoException {
		this.getControladorUsuario().envioEmailLogSistema(log);
	}
	
	public static void destroyInstance() {
		if( instance != null ) {
			instance = null;
		}
	}
}
