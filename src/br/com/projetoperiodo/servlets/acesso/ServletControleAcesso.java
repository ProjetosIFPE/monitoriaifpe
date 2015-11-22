package br.com.projetoperiodo.servlets.acesso;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.usuario.Usuario;
import br.com.projetoperiodo.util.constantes.Constantes;

public class ServletControleAcesso extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5189852811554330041L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd;
		if (request.getSession(Boolean.FALSE) == null) {
			rd = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
			rd.forward(request, response);
		} else {
			
			Usuario usuarioLogado = (Usuario)request.getSession(false).getAttribute(Constantes.ATRIBUTO_USUARIO_LOGADO);
			if (usuarioLogado.getPapelUsuario().equals(Aluno.PAPEL_ALUNO)) {
				rd = request.getRequestDispatcher("/aluno.do");
			} else {
				rd = request.getRequestDispatcher("/professor.do");
			}
			rd.forward(request, response);
		}
	}

}
