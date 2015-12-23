package br.com.projetoperiodo.servlets.cadastro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class ServletRemoveDisciplina
 */
public class ServletRemoveDisciplina extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHAVE_DISCIPLINA = "chaveDisciplina";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRemoveDisciplina() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(Boolean.FALSE);
		if (session == null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		} else {
			Long chaveDisciplina = Long.valueOf(request.getParameter(CHAVE_DISCIPLINA));
			Fachada.getInstance().retirarProfessorDeDisciplina(chaveDisciplina);
			request.getRequestDispatcher("/professor.do").forward(request, response);
		}	
	}

}
