
package br.com.projetoperiodo.servlets.cadastro;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.exception.NegocioException;
import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class ServletAdicionaDisciplinaAluno
 */
public class ServletAdicionaDisciplinaAluno extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String LISTA_DISCIPLINAS = "listaDisciplinasComProfessor";

	private static final String MENSAGEM_ADICIONADO_SUCESSO = "Disciplina adicionada com sucesso";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAdicionaDisciplinaAluno() {
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
		} else {
			Aluno aluno;
			session = request.getSession(Boolean.FALSE);
			synchronized(session) {
				aluno = (Aluno) session.getAttribute(Constantes.ATRIBUTO_USUARIO_LOGADO);
			}
			List<Disciplina> listaDisciplinas = Fachada.getInstance().listarDisciplinasDisponiveisParaAluno(aluno);
			request.setAttribute(LISTA_DISCIPLINAS, listaDisciplinas);
			request.getRequestDispatcher("/WEB-INF/jsp/AdicionarDisciplina.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(Boolean.FALSE);
		if (session == null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		} else {
			String descricaoDisciplina = request.getParameter("disciplina");
			Aluno aluno;
			try {
				Disciplina disciplina = (Disciplina) Fachada.getInstance().buscarDisciplina(descricaoDisciplina);
				session = request.getSession(Boolean.FALSE);
				synchronized(session) {
					aluno = (Aluno) session.getAttribute(Constantes.ATRIBUTO_USUARIO_LOGADO);
				}
				Fachada.getInstance().adicionarDisciplinaEmCadastroDeAluno(aluno, disciplina);
				request.setAttribute(Constantes.MENSAGEM_SUCESSO, MENSAGEM_ADICIONADO_SUCESSO);
				request.getRequestDispatcher("/aluno.do").forward(request, response);
			} catch (NegocioException e) {

				e.printStackTrace();
			}
		}
		
	}

}
