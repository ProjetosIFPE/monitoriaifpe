package br.com.projetoperiodo.servlets.apresentacao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.model.instituto.professor.Professor;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class ServletProfessor
 */
public class ServletProfessor extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String DISCIPLINAS_PROFESSOR_PERIODO_CORRENTE = "disciplinasProfessor";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProfessor() {
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
		Professor professorLogado;
		synchronized(session) {
			professorLogado = (Professor) request.getSession().getAttribute(Constantes.ATRIBUTO_USUARIO_LOGADO);	
		}
		List<Disciplina> discplinas = Fachada.getInstance().listarDisciplinasDeProfessor(professorLogado);
		request.setAttribute(DISCIPLINAS_PROFESSOR_PERIODO_CORRENTE, discplinas);
		request.getRequestDispatcher("/WEB-INF/jsp/DisciplinasProfessor.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
