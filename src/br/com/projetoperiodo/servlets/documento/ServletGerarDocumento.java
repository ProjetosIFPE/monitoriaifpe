package br.com.projetoperiodo.servlets.documento;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.instituto.monitor.Monitoria;
import br.com.projetoperiodo.model.relatorio.frequencia.RelatorioFrequencia;
import br.com.projetoperiodo.model.usuario.Usuario;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.constantes.enumeracoes.Situacao;
import br.com.projetoperiodo.util.exception.NegocioException;
import br.com.projetoperiodo.util.exception.ProjetoException;
import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class ServletGerarDocumento
 */
public class ServletGerarDocumento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MES_RELATORIO = "mes";
	private static final String DOCUMENTO_RELATORIO = "documento_relatorio";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGerarDocumento() {
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
		int mesRelatorio = Integer.valueOf(request.getParameter(MES_RELATORIO));
		Monitoria monitor;
		Usuario usuarioLogado;
		session = request.getSession(Boolean.FALSE);
		synchronized(session) {
			monitor = (Monitoria) session.getAttribute(Constantes.ATRIBUTO_MONITORIA);
			usuarioLogado = (Usuario)session.getAttribute(Constantes.ATRIBUTO_USUARIO_LOGADO);
		}
		RelatorioFrequencia relatorio = Fachada.getInstance().buscarRelatorioMensal(monitor, mesRelatorio);
		try {
			request.setAttribute(DOCUMENTO_RELATORIO, Fachada.getInstance().gerarDocumentoDeRelatorio(relatorio, usuarioLogado));
			request.getRequestDispatcher("/enviarDocumento.do").forward(request, response);
		} catch ( ProjetoException e ) {
			request.setAttribute(Constantes.MENSAGEM_INFO, e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/CadastroRelatorio.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
