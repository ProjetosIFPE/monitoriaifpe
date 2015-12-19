package br.com.projetoperiodo.servlets.cadastro;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.instituto.curso.Curso;
import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.exception.ProjetoException;
import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class ServletCadastroMonitor
 */
public class ServletCadastroAluno extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LISTA_DISCIPLINAS = "listaDisciplinas";
	private static final String ATRIBUTO_ALUNO = "aluno";
	
	// TODO Modificar esta estrategia, pode implicar em problemas de
	// concorrencia
	private static final List<Disciplina> listaDisciplinas = Fachada.getInstance().listarDisciplinasCadastradasComProfessor();
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCadastroAluno() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession(Boolean.FALSE) != null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		}
		request.setAttribute(LISTA_DISCIPLINAS, listaDisciplinas);
		request.getRequestDispatcher("/WEB-INF/jsp/CadastroAluno.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		if (request.getSession(Boolean.FALSE) != null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		}
		Aluno aluno = (Aluno) Fachada.getInstance().criarAluno();
		aluno.setMatricula(request.getParameter("matricula"));
		aluno.setNome(request.getParameter("nome"));
		aluno.setSobrenome(request.getParameter("sobrenome"));
		aluno.setLogin(request.getParameter("login"));
		aluno.setEmail(request.getParameter("email"));
		aluno.setSenha(request.getParameter("senha"));

		String[] materias = request.getParameterValues("disciplinas");

		for (int x = 0; x < materias.length; x++) {
			Disciplina disciplina = (Disciplina) Fachada.getInstance().criarDisciplina();
			Disciplina disciplinaRetornada = (Disciplina) Fachada.getInstance().criarDisciplina();
			disciplina.setDescricao(materias[x]);

			disciplinaRetornada = comparaDisciplinas(disciplina);
			aluno.setDisciplinas(disciplinaRetornada);
		}
		Curso curso = (Curso) Fachada.getInstance().buscarCursoPadraoDeAluno();
		aluno.setCurso(curso);
		try {
			Fachada.getInstance().cadastrarAluno(aluno);
			rd = request.getRequestDispatcher("/acesso.do");
		} catch (ProjetoException e) {
			request.setAttribute(Constantes.CAMPOS_INVALIDOS, e.getParametrosDeErro());
			request.setAttribute(ATRIBUTO_ALUNO, aluno);
			request.setAttribute(LISTA_DISCIPLINAS, listaDisciplinas);
			rd = request.getRequestDispatcher("/WEB-INF/jsp/CadastroAluno.jsp");
		}

		rd.forward(request, response);

	}

	protected Disciplina comparaDisciplinas(Disciplina disc) {
		Disciplina objDisciplina = null;

		for (int i = 0; i < listaDisciplinas.size(); i++) {
			if (listaDisciplinas.get(i).getDescricao().equals(disc.getDescricao())) {
				objDisciplina = listaDisciplinas.get(i);
			}
		}

		return objDisciplina;
	}
}
