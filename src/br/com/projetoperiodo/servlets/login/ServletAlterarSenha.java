
package br.com.projetoperiodo.servlets.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projetoperiodo.model.usuario.Usuario;
import br.com.projetoperiodo.model.usuario.controller.ControladorUsuario;
import br.com.projetoperiodo.util.Util;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.exception.NegocioException;
import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class Servlet_alterarSenha
 */
public class ServletAlterarSenha extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String SENHA_ANTIGA = "senhaAntiga";

	private static final String SENHA_NOVA = "senhaNova";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAlterarSenha() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/AlterarSenha.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		synchronized (session) {
			if (session == null) {
				request.getRequestDispatcher("/acesso.do").forward(request, response);
			}
		}
		String senhaAntiga = request.getParameter(SENHA_ANTIGA);
		String senhaNova = request.getParameter(SENHA_NOVA);
		Usuario usuarioLogado;
		session = request.getSession(false);
		synchronized (session) {
			usuarioLogado = (Usuario) session.getAttribute(Constantes.ATRIBUTO_USUARIO_LOGADO);
		}

		boolean podeAlterar = Fachada.getInstance().compararSenhasDeUsuario(senhaAntiga, usuarioLogado); 
		if (podeAlterar) {
			Usuario usuarioAlterado = Fachada.getInstance().alterarSenhaUsuario(usuarioLogado, senhaNova);
			session = request.getSession(false);
			synchronized (session) {
				session.setAttribute(Constantes.ATRIBUTO_USUARIO_LOGADO, usuarioAlterado);
			}
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		} else {
			// TODO: fluxo de erro
		}

	}

}
