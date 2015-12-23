package br.com.projetoperiodo.servlets.documento;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletEnviaDocumento
 */
public class ServletEnviaDocumento extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "relatorio.pdf";
    private static final String DOCUMENTO_RELATORIO = "documento_relatorio";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEnviaDocumento() {
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
			byte[] bytes = (byte[]) request.getAttribute(DOCUMENTO_RELATORIO);
			
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=" + FILE_NAME);
			response.setContentLength(bytes.length);

			OutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(bytes);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
