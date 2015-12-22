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
import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class ServletBuscarAluno
 */
public class ServletBuscarAluno extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String OBJ_ALUNO = "aluno";
	private static final String LISTA_MONITORIAS = "monitoriasDoAluno";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBuscarAluno() {
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
		}
		request.getRequestDispatcher("/WEB-INF/jsp/PesquisaAluno.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HttpSession session = request.getSession(Boolean.FALSE);
		if (session == null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		}
		String matricula = request.getParameter("matricula");
		Aluno aluno = (Aluno) Fachada.getInstance().buscarAluno(matricula);
		List<Monitoria> monitorias = Fachada.getInstance().buscarMonitorias(aluno);
		request.setAttribute(OBJ_ALUNO, aluno);
		request.setAttribute(LISTA_MONITORIAS, monitorias);
		request.getRequestDispatcher("/WEB-INF/jsp/ExibeDadosAlunos.jsp").forward(request, response);
		
	}

}
