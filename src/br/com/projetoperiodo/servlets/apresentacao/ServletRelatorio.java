package br.com.projetoperiodo.servlets.apresentacao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projetoperiodo.model.instituto.monitor.Monitoria;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.exception.ProjetoException;
import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class ServletRelatorio
 */
public class ServletRelatorio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String RELATORIOS_MONITOR = "relatoriosMonitor";
    public static final String CHAVE_MONITORIA = "chaveMonitor";
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
		if (request.getSession(false) == null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		}
		long chavePrimariaMonitoria = Long.valueOf(request.getParameter(CHAVE_MONITORIA));
		Monitoria monitor;
		RequestDispatcher rd;
		try {
			monitor = (Monitoria) Fachada.getInstance().buscarMonitoria(chavePrimariaMonitoria);
			request.getSession(false).setAttribute(Constantes.ATRIBUTO_MONITORIA, monitor);
			rd  = request.getRequestDispatcher("/WEB-INF/jsp/RelatoriosAluno.jsp");
		} catch (ProjetoException e) {
			request.setAttribute(Constantes.MENSAGEM_ERRO, e.getMessage());
			rd = request.getRequestDispatcher("/aluno.do");
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
