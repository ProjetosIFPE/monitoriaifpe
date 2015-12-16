
package br.com.projetoperiodo.servlets.cadastro;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.model.instituto.monitor.Monitoria;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.constantes.enumeracoes.Modalidade;
import br.com.projetoperiodo.util.exception.NegocioException;
import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class ServletCadastroMonitoria
 */
public class ServletCadastroMonitoria extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String LISTA_DISCIPLINAS = "listaDisciplinas";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCadastroMonitoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		}
		Aluno aluno;
		synchronized(session) {
			aluno = (Aluno) session.getAttribute(Constantes.ATRIBUTO_USUARIO_LOGADO);
		}
		List<Disciplina> listaDisciplinas = Fachada.getInstance().listarDisciplinasDeAluno(aluno);
		request.setAttribute(LISTA_DISCIPLINAS, listaDisciplinas);
		request.getRequestDispatcher("/WEB-INF/jsp/CadastroMonitoria.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(Boolean.FALSE);
		if (session == null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		}
		Aluno aluno;
		synchronized(session) {
			aluno = (Aluno) session.getAttribute(Constantes.ATRIBUTO_USUARIO_LOGADO);
		}
		boolean cadastroValido;
		Disciplina disciplina = null;
		Modalidade modalidade = Modalidade.valueOf(request.getParameter("modalidade"));
		String horarioEntrada = request.getParameter("entrada");
		String horarioSaida = request.getParameter("saida");
		try {
			disciplina = (Disciplina) Fachada.getInstance().buscarDisciplina(request.getParameter("disciplina"));
		} catch (NegocioException e) {
			// TODO Tratar erro de inexistencia de disciplina
			e.printStackTrace();
		}
		Monitoria monitor = (Monitoria) Fachada.getInstance().criarMonitoria(aluno, disciplina, modalidade);
		monitor.setHorarioEntrada(horarioEntrada);
		monitor.setHorarioSaida(horarioSaida);
		cadastroValido = Fachada.getInstance().validarCadastroMonitoria(monitor);

		if (cadastroValido) {
			monitor = (Monitoria) Fachada.getInstance().cadastrarMonitoria(monitor);
			Fachada.getInstance().preCadastroRelatoriosMonitor(monitor);
		} else {
			// TODO Tratar invalidade do cadastro
		}
		request.getRequestDispatcher("/acesso.do").forward(request, response);
	}

}
