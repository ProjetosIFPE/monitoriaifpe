
package br.com.projetoperiodo.util.fachada;

import java.util.HashMap;
import java.util.List;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.model.instituto.monitor.Monitoria;
import br.com.projetoperiodo.model.instituto.periodo.Periodo;
import br.com.projetoperiodo.model.instituto.professor.Professor;
import br.com.projetoperiodo.model.negocio.entidade.EntidadeNegocio;
import br.com.projetoperiodo.model.relatorio.atividade.Atividade;
import br.com.projetoperiodo.model.relatorio.frequencia.RelatorioFrequencia;
import br.com.projetoperiodo.model.relatorio.semana.Semana;
import br.com.projetoperiodo.model.usuario.Usuario;
import br.com.projetoperiodo.util.constantes.enumeracoes.Semestre;
import br.com.projetoperiodo.util.constantes.enumeracoes.Situacao;
import br.com.projetoperiodo.util.exception.NegocioException;
import br.com.projetoperiodo.util.exception.ProjetoException;
import br.com.projetoperiodo.util.persistencia.fabrica.CreatorFabrica;
import br.com.projetoperiodo.util.persistencia.fabrica.FabricaDAO;
import br.com.projetoperiodo.util.persistencia.persistencia.CreatorDatabaseUnit;
import br.com.projetoperiodo.util.persistencia.persistencia.DatabaseUnit;

public class Persistencia {

	private static Persistencia instance = null;

	private FabricaDAO fabrica;

	private DatabaseUnit databaseUnit;

	private Persistencia() {
		databaseUnit = CreatorDatabaseUnit.criarDatabaseUnit(CreatorDatabaseUnit.ORACLE_LOCAL_UNIT);
		fabrica = CreatorFabrica.criarFabricaDAO(CreatorFabrica.FABRICA_JPA, databaseUnit, null);
	}

	public static synchronized Persistencia getInstance() {

		if (instance == null) {
			instance = new Persistencia();
		}
		return instance;
	}
	
	public EntidadeNegocio buscarUsuario(String login) throws NegocioException {
		return fabrica.criarUsuarioDAO().buscar(login);
	}
	
	public EntidadeNegocio atualizarUsuario(Usuario usuario) {
		return fabrica.criarUsuarioDAO().atualizar(usuario);
	}
	
	public EntidadeNegocio buscarUsuario(HashMap<String, Object> filter) throws NegocioException {
		return fabrica.criarUsuarioDAO().buscar(filter);
	}
	
	public void salvarUsuario(Usuario usuario) {
		fabrica.criarUsuarioDAO().salvar(usuario);
	}
	
	public EntidadeNegocio buscarCurso(long chave) {
		return fabrica.criarCursoDAO().buscar(chave);
	}
	
	public EntidadeNegocio salvarProfessor(Usuario usuario) {
		Professor professor = (Professor)usuario;
		return fabrica.criarProfessorDao().salvar(professor);
	}
	
	public EntidadeNegocio salvarAluno(Usuario usuario) {
		Aluno aluno = (Aluno)usuario;
		return fabrica.criarAlunoDAO().salvar(aluno);
	}
	
	public EntidadeNegocio buscarAluno(long chave) {
		return fabrica.criarAlunoDAO().buscar(chave);
	}
	
	public EntidadeNegocio buscarAluno(String matricula) {
		return fabrica.criarAlunoDAO().buscarPelaMatricula(matricula);
	}
	
	public Long buscarQuantidadeDeUsuariosPorLogin(String login) {
		return fabrica.criarUsuarioDAO().buscarCadastroPorLogin(login);
	}
	
	public Long buscarQuantidadeDeUsuariosPorEmail(String email) {
		return fabrica.criarUsuarioDAO().buscarCadastroPorEmail(email);
	}
	
	
	public Long buscarQuantidadeDeAlunos(long chave) {
		return fabrica.criarAlunoDAO().buscarQuantidadeDeAlunosPorChave(chave);
	}
	public Long buscarQuantidadeDeAlunos(String matricula) {
		return fabrica.criarAlunoDAO().buscarQuantidadeDeAlunosPorMatricula(matricula);
	}
	
	public void atualizarDisciplina(EntidadeNegocio entidade) {
		Disciplina disciplina = (Disciplina)entidade;
		fabrica.criarDisciplinaDAO().atualizar(disciplina);
	}
	public EntidadeNegocio buscarDisciplina(long chave) {
		return fabrica.criarDisciplinaDAO().buscar(chave);
	}
	
	public EntidadeNegocio buscarDisciplina(HashMap<String, Object> filtro) throws NegocioException {
		return fabrica.criarDisciplinaDAO().buscar(filtro);
	}
	
	public List<Disciplina> listarDisciplinas() {
		return fabrica.criarDisciplinaDAO().listar();
	}
	
	public List<Disciplina> buscarDisciplinasDeProfessor(EntidadeNegocio entidade) {
		return fabrica.criarDisciplinaDAO().listarDisciplinasDeProfessor(entidade.getChavePrimaria());
	}
	
	public List<Disciplina> buscarDisciplinasSemProfessor() {
		return fabrica.criarDisciplinaDAO().listarDisciplinasSemProfessor();
	}
	
	public List<Disciplina> buscarDisciplinasComProfessor() {
		return fabrica.criarDisciplinaDAO().listarDisciplinasComProfessor();
	}
	
	public List<Disciplina> buscarDisciplinasDeAluno(EntidadeNegocio entidade) {
		return fabrica.criarDisciplinaDAO().listarDisciplinasDeAluno(entidade.getChavePrimaria());
	}
	
	public EntidadeNegocio buscarMonitoria(long chave) {
		return fabrica.criarMonitoriaDAO().buscar(chave);
	}
	
	public EntidadeNegocio salvarMonitoria(EntidadeNegocio entidade) {
		Monitoria monitoria = (Monitoria)entidade;
		return fabrica.criarMonitoriaDAO().salvar(monitoria);
	}
	
	public void removerMonitoria(EntidadeNegocio entidade) {
		Monitoria monitoria = (Monitoria)entidade;
		fabrica.criarMonitoriaDAO().remover(monitoria);
	}
	
	public void removerProfessorDeDisciplina(long chaveDisciplina) {
		fabrica.criarDisciplinaDAO().removerProfessorDaDisciplina(chaveDisciplina);
	}
	
	public List<Monitoria> buscarMonitoriasDeDisciplina(long chave, Periodo periodo) {
		return fabrica.criarMonitoriaDAO().buscarMonitoriasDeDisciplina(chave);
	}
	
	public List<Monitoria> buscarMonitoriasDeDisciplinaDeUmPeriodo(long chaveDisciplina, long chavePeriodo) {
		return fabrica.criarMonitoriaDAO().buscarMonitoriasDeDisciplinaDeUmPeriodo(chaveDisciplina, chavePeriodo);
	}
	
	public List<Monitoria> buscarMonitoriasDeAluno(long chave) {
		return fabrica.criarMonitoriaDAO().buscarMonitoriasDeAluno(chave);
	}
	
	public Long quantidadeMonitoriasDeAluno(long chave) {
		return fabrica.criarMonitoriaDAO().buscarQuantidadeMonitoriasDeAluno(chave);
	}
	
	public List<Monitoria> verificarCadastroDeMonitoria(EntidadeNegocio entidade) {
		Monitoria monitoria = (Monitoria)entidade;
		return fabrica.criarMonitoriaDAO().buscarMonitoriaCadastrada(monitoria);
	}
	
	public EntidadeNegocio salvarPeriodo(EntidadeNegocio entidade) {
		Periodo periodo = (Periodo)entidade;
		return fabrica.criarPeriodoDao().salvar(periodo);
	}
	
	public List<Periodo> buscarPeriodo(int ano, Semestre semestre) {
		return fabrica.criarPeriodoDao().buscarPeriodoPorAnoEsemestre(ano, semestre);
	}
	
	public EntidadeNegocio buscarProfessor(long chave) {
		return fabrica.criarProfessorDao().buscar(chave);
	}
	
	public void salvarAtividade(EntidadeNegocio entidade) {
		Atividade atividade = (Atividade)entidade;
		fabrica.criarAtividadeDAO().salvar(atividade);
	}
	
	public void removerAtividade(EntidadeNegocio entidade) {
		Atividade atividade  = (Atividade)entidade;
		fabrica.criarAtividadeDAO().remover(atividade);
	}
	
	public List<Atividade> buscarAtividadesDeSemana( long chave ) {
		return fabrica.criarAtividadeDAO().buscarAtividadesDeSemana(chave);
	}
	
	public EntidadeNegocio salvarSemana(EntidadeNegocio entidade) {
		Semana semana = (Semana)entidade;
		return fabrica.criarSemanaDAO().salvar(semana);
	}
	
	public void removerSemana(EntidadeNegocio entidade) {
		Semana semana = (Semana)entidade;
		fabrica.criarSemanaDAO().remover(semana);
	}
	
	public List<Semana> buscarSemanasDeRelatorio( long chave ) {
		return fabrica.criarSemanaDAO().buscarSemanasDeRelatorio(chave);
	}
	
	public void removerRelatorio( EntidadeNegocio entidade ) {
		RelatorioFrequencia relatorio = (RelatorioFrequencia)entidade;
		fabrica.criarRelatorioFrequenciaDAO().remover(relatorio);
	}
	
	public EntidadeNegocio salvarRelatorio( EntidadeNegocio entidade ) {
		RelatorioFrequencia relatorio = (RelatorioFrequencia)entidade;
		return fabrica.criarRelatorioFrequenciaDAO().salvar(relatorio);
	}
	
	public EntidadeNegocio atualizarRelatorio(EntidadeNegocio entidade) {
		RelatorioFrequencia relatorio = (RelatorioFrequencia)entidade;
		return fabrica.criarRelatorioFrequenciaDAO().atualizar(relatorio);
	}
	
	public List<Situacao> listarSituacaoDeRelatorioDeMonitoria(long chave) {
		return fabrica.criarRelatorioFrequenciaDAO().listarSituacaoDosRelatorios(chave);
	}
	
	public EntidadeNegocio buscarRelatoriosMonitoriaPorMes(long chave, int mes) {
		return fabrica.criarRelatorioFrequenciaDAO().buscarRelatoriosDeMonitoriaPorMes(chave, mes);
	}
	
	public List<RelatorioFrequencia> buscarRelatoriosMonitoria(long chave) {
		return fabrica.criarRelatorioFrequenciaDAO().buscarRelatoriosDeMonitoria(chave);
	}
	
	public void atualizarCadastroDeAluno(Aluno aluno) {
		this.fabrica.criarAlunoDAO().atualizar(aluno);
	}
	
	
	public void alterarFabrica(String tipo, String unidade) {
		FabricaDAO fabricaJPA = null;
		
		this.desalocarRecursos();
		this.databaseUnit = CreatorDatabaseUnit.criarDatabaseUnit(unidade);
		if( CreatorFabrica.FABRICA_JDBC.equals(tipo)) {
			fabricaJPA = CreatorFabrica.criarFabricaDAO(CreatorFabrica.FABRICA_JPA, databaseUnit, null);
		}
		this.fabrica = CreatorFabrica.criarFabricaDAO(tipo, databaseUnit, fabricaJPA);
	}
	
	private void desalocarRecursos() {
		fabrica.fecharFabrica();
	}
	
	public static void destroyInstance() {
		if( instance != null ) {
			instance.desalocarRecursos();
			instance = null;
		}
	}
}
