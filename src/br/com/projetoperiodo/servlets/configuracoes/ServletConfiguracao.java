
package br.com.projetoperiodo.servlets.configuracoes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class ServletConfiguracao
 */
public class ServletConfiguracao extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String BANCO = "banco";

	private static final String ESTRATEGIA = "estrategia";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletConfiguracao() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(Boolean.FALSE);
		if ( session != null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/jsp/Configuracoes.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(Boolean.FALSE);
		if ( session != null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		} else {
			String banco = request.getParameter(BANCO);
			String estrategia = request.getParameter(ESTRATEGIA);
			if (banco != null && estrategia != null && !banco.isEmpty() && !estrategia.isEmpty()) {
				String[] configuracoes = {estrategia, banco};
				Fachada.getInstance().alterarConfiguracao(configuracoes);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
		}
	}

}
