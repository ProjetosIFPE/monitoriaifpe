package br.com.projetoperiodo.servlets.documento;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projetoperiodo.model.instituto.monitor.Monitoria;
import br.com.projetoperiodo.model.relatorio.frequencia.RelatorioFrequencia;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.constantes.enumeracoes.Situacao;
import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class ServletAprovaRelatorio
 */
public class ServletAprovaRelatorio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MES_RELATORIO = "mes";
	public static final String SITUACAO_RELATORIOS_MONITORIA = "situacaoRelatorios";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAprovaRelatorio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session  = request.getSession(false);
		if (session == null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		}
		int mesRelatorio = Integer.valueOf(request.getParameter(MES_RELATORIO));
		Monitoria monitor;
		synchronized(session) {
			monitor = (Monitoria) session.getAttribute(Constantes.ATRIBUTO_MONITORIA);
		}
		RelatorioFrequencia relatorio = Fachada.getInstance().buscarRelatorioMensal(monitor, mesRelatorio);
		Fachada.getInstance().aprovarRelatorio(relatorio);
		List<Situacao> listaSituacao = Fachada.getInstance().buscarSituacaoDeRelatorios(monitor);
		request.setAttribute(SITUACAO_RELATORIOS_MONITORIA, listaSituacao);
		request.getRequestDispatcher("/WEB-INF/jsp/RelatoriosProfessor.jsp").forward(request, response);
	}

}
