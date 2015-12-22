package br.com.projetoperiodo.servlets.cadastro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class ServletRemoveMonitoria
 */
public class ServletRemoveMonitoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String CHAVE_MONITOR = "chaveMonitor";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRemoveMonitoria() {
        super();
     
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(Boolean.FALSE);
		if ( session == null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		}
		long chavePrimariaMonitor = Long.valueOf(request.getParameter(CHAVE_MONITOR));
		Fachada.getInstance().removerMonitoriaDeAluno(chavePrimariaMonitor);
		request.getRequestDispatcher("/aluno.do").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
