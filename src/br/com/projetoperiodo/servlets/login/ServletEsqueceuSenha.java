package br.com.projetoperiodo.servlets.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.result.Output;

import br.com.projetoperiodo.model.usuario.Usuario;
import br.com.projetoperiodo.model.usuario.controller.ControladorUsuario;
import br.com.projetoperiodo.util.exception.NegocioException;
import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class ServletEsqueceuSenha
 */
public class ServletEsqueceuSenha extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String FORM_LOGIN = "login";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletEsqueceuSenha() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/RequisitarSenha.jsp").forward(request, response);;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		ControladorUsuario controladorUsuario = Fachada.getInstance().getControladorUsuario();

		String loginUsuario = request.getParameter(FORM_LOGIN);
		Usuario usuario = (Usuario) controladorUsuario.criarEntidadeNegocio();
		usuario.setLogin(loginUsuario);
		try {
			Usuario usuarioBuscado = controladorUsuario.buscarCadastroDeUsuario(usuario);
			controladorUsuario.encaminharSenhaParaUsuario(usuarioBuscado);
			request.getRequestDispatcher("/acesso.do").forward(request, response);

		} catch (NegocioException e) {
			//TODO tratar erro de usuario inexistente
			e.printStackTrace();
		}

	}

}
