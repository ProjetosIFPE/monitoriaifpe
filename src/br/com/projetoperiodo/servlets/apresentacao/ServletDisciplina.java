package br.com.projetoperiodo.servlets.apresentacao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.model.instituto.monitor.Monitoria;
import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class ServletMonitoria
 */
public class ServletDisciplina extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String LISTA_MONITORIAS_DE_DISCIPLINA = "listaMonitoriasDisciplina";
    private static final String CHAVE_DISCIPLINA = "chaveDisciplina";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDisciplina() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(Boolean.FALSE);
		if (session == null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		}
		long chavePrimaria = Long.valueOf(request.getParameter(CHAVE_DISCIPLINA));
		Disciplina disciplina = (Disciplina) Fachada.getInstance().buscarDisciplina(chavePrimaria);
		List<Monitoria> monitoriasDisciplina = Fachada.getInstance().buscarMonitoriasDeDisciplina(disciplina);
		request.setAttribute(LISTA_MONITORIAS_DE_DISCIPLINA, monitoriasDisciplina);
		request.getRequestDispatcher("/WEB-INF/jsp/MonitoriasDisciplina.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
