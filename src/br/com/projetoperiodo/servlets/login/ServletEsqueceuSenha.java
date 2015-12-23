
package br.com.projetoperiodo.servlets.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projetoperiodo.model.usuario.Usuario;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.exception.ProjetoException;
import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class ServletEsqueceuSenha
 */
public class ServletEsqueceuSenha extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String FORM_LOGIN = "login";

	private static final String MENSAGEM_SUCESSO_ENVIO = "Email enviado com sucesso para ";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletEsqueceuSenha() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(Boolean.FALSE);
		if (session != null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		} else {
			request.getRequestDispatcher("WEB-INF/jsp/RequisitarSenha.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(Boolean.FALSE);
		if (session != null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		} else {
			String loginUsuario = request.getParameter(FORM_LOGIN);
			Usuario usuario = (Usuario) Fachada.getInstance().criarUsuario();
			usuario.setLogin(loginUsuario);
			try {
				StringBuilder builder = new StringBuilder();
				Usuario usuarioBuscado = (Usuario) Fachada.getInstance().buscarUsuario(usuario);
				builder.append(MENSAGEM_SUCESSO_ENVIO);
				builder.append(usuarioBuscado.getEmail());
				Fachada.getInstance().encaminharSenhaParaUsuario(usuarioBuscado);
				request.setAttribute(Constantes.MENSAGEM_SUCESSO, builder.toString());
			} catch (ProjetoException e) {
				request.setAttribute(Constantes.MENSAGEM_ERRO, e.getMessage());
			}
			request.getRequestDispatcher("WEB-INF/jsp/RequisitarSenha.jsp").forward(request, response);
		}

	}

}
