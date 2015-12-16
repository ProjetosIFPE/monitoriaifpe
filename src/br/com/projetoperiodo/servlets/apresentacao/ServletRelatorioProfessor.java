
package br.com.projetoperiodo.servlets.apresentacao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projetoperiodo.model.instituto.monitor.Monitoria;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.constantes.enumeracoes.Situacao;
import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class ServletRelatorioProfessor
 */
public class ServletRelatorioProfessor extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final String CHAVE_MONITOR = "chaveMonitor";
	public static final String SITUACAO_RELATORIOS_MONITORIA = "situacaoRelatorios";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletRelatorioProfessor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(Boolean.FALSE);
		if (session == null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		}
		long chavePrimariaMonitor = Long.valueOf(request.getParameter(CHAVE_MONITOR));
		Monitoria monitor = (Monitoria) Fachada.getInstance().buscarMonitoria(chavePrimariaMonitor);
		synchronized(session) {
			session.setAttribute(Constantes.ATRIBUTO_MONITORIA, monitor);
		}
		List<Situacao> listaSituacao = Fachada.getInstance().buscarSituacaoDeRelatorios(monitor);
		request.setAttribute(SITUACAO_RELATORIOS_MONITORIA, listaSituacao);
		request.getRequestDispatcher("/WEB-INF/jsp/RelatoriosProfessor.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
