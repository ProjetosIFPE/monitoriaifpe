
package br.com.projetoperiodo.servlets.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projetoperiodo.model.usuario.Usuario;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.exception.ProjetoException;
import br.com.projetoperiodo.util.fachada.Fachada;

public class ServletLogin extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3706103113838427192L;

	private static final String FORM_LOGIN = "login";

	private static final String FORM_SENHA = "senha";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(Boolean.FALSE);
		if (session != null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		} else {
			RequestDispatcher requestDispatcher;

			String login = request.getParameter(FORM_LOGIN);
			String senha = request.getParameter(FORM_SENHA);
			Usuario usuario = (Usuario) Fachada.getInstance().criarUsuario();
			usuario.setLogin(login);
			usuario.setSenha(senha);
			try {
				Usuario usuarioAutenticado = (Usuario) Fachada.getInstance().autenticarUsuario(usuario);
				session = request.getSession();
				synchronized(session) {
					session.setAttribute(Constantes.ATRIBUTO_USUARIO_LOGADO, usuarioAutenticado);
				}
				if ("ALUNO".equals(usuarioAutenticado.getPapelUsuario())) {
					requestDispatcher = request.getRequestDispatcher("/aluno.do");
				} else {
					requestDispatcher = request.getRequestDispatcher("/professor.do");
				}
				requestDispatcher.forward(request, response);
			} catch (ProjetoException e) {
				request.setAttribute(Constantes.MENSAGEM_ERRO, e.getMessage());
				requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
				requestDispatcher.forward(request, response);
			}
		}

	}
}
