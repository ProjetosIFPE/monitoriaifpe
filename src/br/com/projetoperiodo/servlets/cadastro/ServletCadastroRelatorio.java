
package br.com.projetoperiodo.servlets.cadastro;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.ws.transport.http.HttpMetadataPublisher;

import br.com.projetoperiodo.model.instituto.monitor.Monitoria;
import br.com.projetoperiodo.model.relatorio.atividade.Atividade;
import br.com.projetoperiodo.model.relatorio.frequencia.RelatorioFrequencia;
import br.com.projetoperiodo.model.relatorio.semana.Semana;
import br.com.projetoperiodo.util.Util;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.fachada.Fachada;

/**
 * Servlet implementation class ServletCadastroRelatorio
 */
public class ServletCadastroRelatorio extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String MES_RELATORIO = "mes";

	private static final String RELATORIO_MENSAL = "relatorio";

	private static final String DESCRICAO_MES = "descricaoMes";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCadastroRelatorio() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(Boolean.FALSE);
		if (session == null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		}
		int mesRelatorio = Integer.valueOf(request.getParameter(MES_RELATORIO));
		Monitoria monitor;
		RelatorioFrequencia relatorio;
		session = request.getSession(Boolean.FALSE);
		synchronized(session) {
			monitor = (Monitoria) session.getAttribute(Constantes.ATRIBUTO_MONITORIA);
			relatorio = Fachada.getInstance().buscarRelatorioMensal(monitor, mesRelatorio);
			session.setAttribute(RELATORIO_MENSAL, relatorio);
		}
		request.setAttribute(DESCRICAO_MES, relatorio.getDescricaoMes());
		request.getRequestDispatcher("/WEB-INF/jsp/CadastroRelatorio.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(Boolean.FALSE);
		if (session == null) {
			request.getRequestDispatcher("/acesso.do").forward(request, response);
		}
		session = request.getSession(Boolean.FALSE);
		RelatorioFrequencia relatorio;
		synchronized(session) {
			relatorio = (RelatorioFrequencia) session.getAttribute(RELATORIO_MENSAL);
		}
		Semana semana;
		Atividade atividade;
		for (int posicaoSemana = 1; posicaoSemana <= 5; posicaoSemana++) {
			semana = relatorio.getSemana(posicaoSemana - 1);
			semana.setDescricao(request.getParameter("descricaosemana".concat(String.valueOf(posicaoSemana))));
			for (int posicaoAtividade = 1; posicaoAtividade <= 5; posicaoAtividade++) {
				String dataStr = request.getParameter("semana" + posicaoSemana + "atividade" + posicaoAtividade);
				atividade = semana.getAtividade(posicaoAtividade - 1);

				Date data = null;
				try {
					data = Util.parseTextoData(dataStr);
					atividade.setData(data);
				} catch (ParseException e) {
					// TODO Tratar data invalida
					e.printStackTrace();
				}

			}
		}
		Fachada.getInstance().atualizarRelatorio(relatorio);
		request.getRequestDispatcher("/WEB-INF/jsp/CadastroRelatorio.jsp").forward(request, response);
	}

}
