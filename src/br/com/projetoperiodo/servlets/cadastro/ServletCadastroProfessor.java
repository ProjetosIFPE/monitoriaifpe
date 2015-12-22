
package br.com.projetoperiodo.servlets.cadastro;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.model.instituto.professor.Professor;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.exception.ProjetoException;
import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class ServletCadastroProfessor
 */
public class ServletCadastroProfessor extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String LISTA_DISCIPLINAS = "listaDisciplinas";
	private List<Disciplina> listaDisciplinas;
	
	private static final String MENSAGEM_CADASTRO_INVALIDO = "Cadastro inválido. Campos informados já possuem cadastro.";
	private static final String MENSAGEM_SUCESSO_CADASTRO = "Professor cadastrado com sucesso";
	private static final String ATRIBUTO_PROFESSOR = "professor";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCadastroProfessor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(Boolean.FALSE);
		if (session != null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		}
		listaDisciplinas = Fachada.getInstance().listarDisciplinasSemProfessor();
		request.setAttribute(LISTA_DISCIPLINAS, listaDisciplinas);
		request.getRequestDispatcher("/WEB-INF/jsp/CadastroProfessor.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(Boolean.FALSE);
		RequestDispatcher rd;
		if (session != null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		}
		Professor professor = (Professor) Fachada.getInstance().criarProfessor();
		professor.setLogin(request.getParameter("login"));
		professor.setNome(request.getParameter("nome"));
		professor.setSobrenome(request.getParameter("sobrenome"));
		professor.setEmail(request.getParameter("email"));
		professor.setSenha(request.getParameter("senha"));
		try {
			professor = Fachada.getInstance().cadastrarProfessor(professor);
			String[] materias = request.getParameterValues("disciplinas");
			if (materias != null) {
				for (int x = 0; x < materias.length; x++) {
					Disciplina disciplina = (Disciplina) Fachada.getInstance().criarDisciplina();
					Disciplina disciplinaRetornada = null;
					disciplina.setDescricao(materias[x]);

					disciplinaRetornada = comparaDisciplinas(disciplina);
					disciplinaRetornada.setProfessor(professor);
					Fachada.getInstance().atualizarDisciplina(disciplinaRetornada);
				}
			}
			request.setAttribute(Constantes.MENSAGEM_SUCESSO, MENSAGEM_SUCESSO_CADASTRO );
			rd = request.getRequestDispatcher("/acesso.do");
		} catch (ProjetoException e) {
			request.setAttribute(Constantes.MENSAGEM_ERRO, MENSAGEM_CADASTRO_INVALIDO);
			request.setAttribute(Constantes.CAMPOS_INVALIDOS, 
							e.getParametrosDeErro().get(Constantes.CAMPOS_INVALIDOS));
			request.setAttribute(ATRIBUTO_PROFESSOR, professor);
			request.setAttribute(LISTA_DISCIPLINAS, listaDisciplinas);
			rd = request.getRequestDispatcher("/WEB-INF/jsp/CadastroProfessor.jsp");
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
