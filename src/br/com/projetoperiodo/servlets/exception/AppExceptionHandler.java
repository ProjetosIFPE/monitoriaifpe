package br.com.projetoperiodo.servlets.exception;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.exception.ProjetoException;
import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class AppExceptionHandler
 */
public class AppExceptionHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MENSAGEM_ERRO_EXCECAO = "Um erro inesperado ocorreu. "
					+ "Um e-mail foi enviado para o administrador do sistema com as informações do ocorrido.";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppExceptionHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}
	
	private void processError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		String mensagemExcecao = "";
		String paginaRequisitada = (String) request.getAttribute("javax.servlet.error.request_uri");
		String nomeServlet = (String) request.getAttribute("javax.servlet.error.servlet_name");
		if ( paginaRequisitada == null ) {
			paginaRequisitada = "Página desconhecida.";
		}
		if ( nomeServlet == null ) {
			nomeServlet = "Nome do Servlet Desconhecido.";
		}
		if ( throwable != null ) {
			mensagemExcecao = throwable.toString();
		}
		if ( 404 == statusCode ) {
			request.getRequestDispatcher("/WEB-INF/jsp/PaginaErro.jsp").forward(request, response);
		} else {
			StringBuilder builder = new StringBuilder();
			builder.append("Status Code: ");
			builder.append(statusCode);
			builder.append("\n");
			builder.append("Requested URI: ");
			builder.append(paginaRequisitada);
			builder.append("\n");
			builder.append("Servlet Name: ");
			builder.append(nomeServlet);
			builder.append("\n");
			builder.append("Exception Message: ");
			builder.append(mensagemExcecao);
			builder.append("\n");
			try {
				Fachada.getInstance().enviarEmailDeLogDoSistema(builder.toString());
				request.setAttribute(Constantes.MENSAGEM_ERRO, MENSAGEM_ERRO_EXCECAO );
			} catch (ProjetoException e) {
				request.setAttribute(Constantes.MENSAGEM_ERRO, e.getMessage() );
			}
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		}
		
	}
	
	

}
