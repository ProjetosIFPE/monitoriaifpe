
package br.com.projetoperiodo.servlets.apresentacao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.instituto.monitor.Monitoria;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class ServletAluno
 */
public class ServletAluno extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String LISTA_MONITORIAS = "listaMonitorias";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAluno() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(Boolean.FALSE);
		if( session == null ) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		}
		Aluno alunoLogado;
		synchronized(session) {
			alunoLogado = (Aluno) request.getSession().getAttribute(Constantes.ATRIBUTO_USUARIO_LOGADO);	
		}
		List<Monitoria> monitores = Fachada.getInstance().buscarMonitorias(alunoLogado);
		request.setAttribute(LISTA_MONITORIAS, monitores);
		request.getRequestDispatcher("/WEB-INF/jsp/MonitoriasAluno.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
