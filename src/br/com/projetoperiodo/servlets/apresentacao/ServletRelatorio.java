package br.com.projetoperiodo.servlets.apresentacao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projetoperiodo.model.instituto.monitor.Monitoria;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class ServletRelatorio
 */
public class ServletRelatorio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String RELATORIOS_MONITOR = "relatoriosMonitor";
    public static final String CHAVE_MONITOR = "chaveMonitor";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRelatorio() {
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
		request.getRequestDispatcher("/WEB-INF/jsp/RelatoriosAluno.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
