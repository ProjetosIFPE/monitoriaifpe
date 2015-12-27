package br.com.projetoperiodo.model.negocio;

import br.com.projetoperiodo.model.instituto.monitor.Monitoria;
import br.com.projetoperiodo.model.instituto.periodo.Periodo;
import br.com.projetoperiodo.model.instituto.periodo.controller.ControladorPeriodo;
import br.com.projetoperiodo.model.instituto.periodo.controller.impl.ControladorPeriodoImpl;
import br.com.projetoperiodo.model.relatorio.atividade.controller.ControladorAtividade;
import br.com.projetoperiodo.model.relatorio.atividade.controller.impl.ControladorAtividadeImpl;
import br.com.projetoperiodo.model.relatorio.frequencia.RelatorioFrequencia;
import br.com.projetoperiodo.model.relatorio.frequencia.controller.ControladorRelatorio;
import br.com.projetoperiodo.model.relatorio.frequencia.controller.impl.ControladorRelatorioImpl;
import br.com.projetoperiodo.model.relatorio.semana.Semana;
import br.com.projetoperiodo.model.relatorio.semana.controller.ControladorSemana;
import br.com.projetoperiodo.model.relatorio.semana.controller.impl.ControladorSemanaImpl;
import br.com.projetoperiodo.model.usuario.Usuario;
import br.com.projetoperiodo.model.usuario.controller.ControladorUsuario;
import br.com.projetoperiodo.model.usuario.controller.impl.ControladorUsuarioImpl;

public class Mediador {
	private static Mediador instance = null;
	private ControladorUsuario controladorUsuario;
	private ControladorPeriodo controladorPeriodo;
	private ControladorSemana controladorSemana;
	private ControladorAtividade controladorAtividade;
	private ControladorRelatorio controladorRelatorio;

	private Mediador() {
		controladorUsuario = new ControladorUsuarioImpl();
		controladorPeriodo = new ControladorPeriodoImpl();
		controladorSemana = new ControladorSemanaImpl();
		controladorAtividade = new ControladorAtividadeImpl();
		controladorRelatorio = new ControladorRelatorioImpl();
	}
	public  static synchronized Mediador getInstance() {
		if ( instance == null ) {
			instance = new Mediador();
		}
		return instance;
	}
	
	public boolean verificarCadastroDeUsuarioPorLogin(Usuario usuario) {
		return controladorUsuario.verificarCadastroDeUsuarioPorLogin(usuario);
	}
	
	public boolean verificarCadastroDeUsuarioPorEmail(Usuario usuario) {
		return controladorUsuario.verificarCadastroDeUsuarioPorEmail(usuario);
	}
	
	public Periodo gerarPeriodo() {
		return controladorPeriodo.gerarNovoPeriodoCorrente();
	}
	
	public void cadastrarSemanas(RelatorioFrequencia relatorio) {
		controladorSemana.cadastrarSemanasComRelatorio(relatorio);
	}
	
	public void cadastrarAtividade(Semana semana) {
		controladorAtividade.cadastrarAtividadesComSemanaDeRelatorio(semana);
	}
	
	public void removerRelatorios(Monitoria monitoria) {
		controladorRelatorio.removerRelatoriosDeMonitoria(monitoria);
	}
	
}
