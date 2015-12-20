
package br.com.projetoperiodo.model.documento;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.DateFormat;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import br.com.projetoperiodo.model.instituto.monitor.Monitoria;
import br.com.projetoperiodo.model.relatorio.atividade.Atividade;
import br.com.projetoperiodo.model.relatorio.frequencia.RelatorioFrequencia;
import br.com.projetoperiodo.model.relatorio.semana.Semana;
import br.com.projetoperiodo.util.Util;

public class ConstrutorDocumento {

	
	private static ConstrutorDocumento instance;

	private FontSelector seletorFonte;

	public static final int NOME_MONITOR_X = 124;

	public static final int NOME_MONITOR_Y = 675;

	public static final int QUANTIDADE_ATIVIDADES = 5;

	public static final int QUANTIDADE_SEMANAS = 5;

	public static final int NOME_DISCIPLINA_X = 132;

	public static final int NOME_DISCIPLINA_Y = 658;

	public static final int NOME_ORIENTADOR_X = 142;

	public static final int NOME_ORIENTADOR_Y = 641;

	public static final int MATRICULA_X = 546;

	public static final int MATRICULA_Y = 675;

	public static final int NOME_CURSO_X = 534;

	public static final int NOME_CURSO_Y = 658;

	public static final int CARGA_HORARIA_X = 240;

	public static final int CARGA_HORARIA_Y = 233;

	public static final int MES_X = 487;

	public static final int MES_Y = 718;

	public static final int ANO_X = 521;

	public static final int ANO_Y = 718;

	public static final int EDITAL_X = 509;

	public static final int EDITAL_Y = 696;

	public static final int ATIVIDADE_Y = 588;

	public static final int HORARIO_ENTRADA_X = 340;

	public static final int HORARIO_SAIDA_X = 390;

	public static final int DATA_ATIVIDADE_X = 307;

	public static final int SEMANA_DESCRICAO_X = 250;

	public static final int SEMANA_DESCRICAO_Y = 588;

	private ConstrutorDocumento() {
		super();
		configurarFonteDocumento();
	}

	private void configurarFonteDocumento() {

		this.seletorFonte = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 11);
		this.seletorFonte.addFont(font);
	}

	public static ConstrutorDocumento getInstancia() {

		if (instance == null) {
			instance = new ConstrutorDocumento();
		}
		return instance;
	}

	private void preencherNomeMonitor(String nome, PdfContentByte conteudoDocumento) {

		Phrase phrase = seletorFonte.process(nome);
		ColumnText.showTextAligned(conteudoDocumento, Element.ALIGN_LEFT, phrase, NOME_MONITOR_X, NOME_MONITOR_Y, 0);
	}

	private void preencherNomeDisciplina(String nome, PdfContentByte conteudoDocumento) {

		Phrase phrase = seletorFonte.process(nome);
		ColumnText.showTextAligned(conteudoDocumento, Element.ALIGN_LEFT, phrase, NOME_DISCIPLINA_X, NOME_DISCIPLINA_Y, 0);
	}

	private void preencherCampoCargaHoraria(String cargaHoraria, PdfContentByte conteudoDocumento) {

		Phrase phrase = seletorFonte.process(cargaHoraria);
		ColumnText.showTextAligned(conteudoDocumento, Element.ALIGN_RIGHT, phrase, CARGA_HORARIA_X, CARGA_HORARIA_Y, 0);
	}

	private void preencherCampoMes(String nome, PdfContentByte conteudoDocumento) {

		Phrase phrase = seletorFonte.process(nome);
		ColumnText.showTextAligned(conteudoDocumento, Element.ALIGN_RIGHT, phrase, MES_X, MES_Y, 0);
	}

	private void preencherNomeOrientador(String nome, PdfContentByte conteudoDocumento) {

		Phrase phrase = seletorFonte.process(nome);
		ColumnText.showTextAligned(conteudoDocumento, Element.ALIGN_LEFT, phrase, NOME_ORIENTADOR_X, NOME_ORIENTADOR_Y, 0);
	}

	private void preencherCampoEdital(String nome, PdfContentByte conteudoDocumento) {

		Phrase phrase = seletorFonte.process(nome);
		ColumnText.showTextAligned(conteudoDocumento, Element.ALIGN_LEFT, phrase, EDITAL_X, EDITAL_Y, 0);
	}

	private void preencherCampoAno(String nome, PdfContentByte conteudoDocumento) {

		Phrase phrase = seletorFonte.process(nome);
		ColumnText.showTextAligned(conteudoDocumento, Element.ALIGN_LEFT, phrase, ANO_X, ANO_Y, 0);
	}

	private void preencherMatricula(String nome, PdfContentByte conteudoDocumento) {

		Phrase phrase = seletorFonte.process(nome);
		ColumnText.showTextAligned(conteudoDocumento, Element.ALIGN_RIGHT, phrase, MATRICULA_X, MATRICULA_Y, 0);
	}

	private void preencherNomeCurso(String nome, PdfContentByte conteudoDocumento) {

		Phrase phrase = seletorFonte.process(nome);
		ColumnText.showTextAligned(conteudoDocumento, Element.ALIGN_RIGHT, phrase, NOME_CURSO_X, NOME_CURSO_Y, 0);
	}

	private void preencherHorarioEntradaAtividade(Monitoria monitor, int decrementoPosicaoRelativaY, PdfContentByte conteudoDocumento) {

		if (monitor.getHorarioEntrada() != null) {
			Phrase phrase = seletorFonte.process(monitor.getHorarioEntrada());
			ColumnText.showTextAligned(conteudoDocumento, Element.ALIGN_RIGHT, phrase, HORARIO_ENTRADA_X,
							ATIVIDADE_Y - decrementoPosicaoRelativaY, 0);
		}

	}

	private void preencherHorarioSaidaAtividade(Monitoria monitor, int decrementoPosicaoRelativaY, PdfContentByte conteudoDocumento) {

		if (monitor.getHorarioSaida() != null) {
			Phrase phrase = seletorFonte.process(monitor.getHorarioSaida());
			ColumnText.showTextAligned(conteudoDocumento, Element.ALIGN_RIGHT, phrase, HORARIO_SAIDA_X,
							ATIVIDADE_Y - decrementoPosicaoRelativaY, 0);
		}

	}

	private void preencherDataAtividade(Atividade atividade, int decrementoPosicaoRelativaY, PdfContentByte conteudoDocumento) {

		if (atividade.getData() != null) {
			DateFormat formataData = DateFormat.getDateInstance();
			Phrase phrase = seletorFonte.process(formataData.format(atividade.getData()));
			ColumnText.showTextAligned(conteudoDocumento, Element.ALIGN_RIGHT, phrase, DATA_ATIVIDADE_X,
							ATIVIDADE_Y - decrementoPosicaoRelativaY, 0);
		}

	}

	private void preencherHorarioEntradaRelatorio(int posicao, Atividade atividade, PdfContentByte conteudoDocumento) {

		Phrase phrase = seletorFonte.process(atividade.getHorarioEntrada());
		ColumnText.showTextAligned(conteudoDocumento, Element.ALIGN_RIGHT, phrase, HORARIO_ENTRADA_X, ATIVIDADE_Y, 0);
	}

	private void preencherDescricaoSegundaSemana(Semana semana, PdfContentByte conteudoDocumento) throws DocumentException {

		if (semana.getDescricao() != null) {
			Phrase phrase = seletorFonte.process(semana.getDescricao());
			ColumnText columnText = new ColumnText(conteudoDocumento);
			columnText.setSimpleColumn(phrase, 73, 529, 250, 100, 10, Element.ALIGN_JUSTIFIED);
			columnText.go();
		}

	}

	private void preencherDescricaoQuartaSemana(Semana semana, PdfContentByte conteudoDocumento) throws DocumentException {

		if (semana.getDescricao() != null) {
			Phrase phrase = seletorFonte.process(semana.getDescricao());
			ColumnText columnText = new ColumnText(conteudoDocumento);
			columnText.setSimpleColumn(phrase, 73, 389, 250, 100, 10, Element.ALIGN_JUSTIFIED);
			columnText.go();
		}

	}

	private void preencherDescricaoQuintaSemana(Semana semana, PdfContentByte conteudoDocumento) throws DocumentException {

		if (semana.getDescricao() != null) {
			Phrase phrase = seletorFonte.process(semana.getDescricao());
			ColumnText columnText = new ColumnText(conteudoDocumento);
			columnText.setSimpleColumn(phrase, 73, 319, 250, 100, 10, Element.ALIGN_JUSTIFIED);
			columnText.go();
		}

	}

	private void preencherDescricaoTerceiraSemana(Semana semana, PdfContentByte conteudoDocumento) throws DocumentException {

		if (semana.getDescricao() != null) {
			Phrase phrase = seletorFonte.process(semana.getDescricao());
			ColumnText columnText = new ColumnText(conteudoDocumento);
			columnText.setSimpleColumn(phrase, 73, 459, 250, 100, 10, Element.ALIGN_JUSTIFIED);
			columnText.go();
		}

	}

	private void preencherDescricaoPrimeiraSemana(Semana semana, PdfContentByte conteudoDocumento) throws DocumentException {

		if (semana.getDescricao() != null) {
			Phrase phrase = seletorFonte.process(semana.getDescricao());
			ColumnText columnText = new ColumnText(conteudoDocumento);
			columnText.setSimpleColumn(phrase, 73, 605, 250, 260, 10, Element.ALIGN_JUSTIFIED);
			columnText.go();
		}

	}

	private void preencherAtividadesSemanais(Atividade atividade, Monitoria monitor, int decrementoPosicaoRelativaY,
					PdfContentByte conteudoDocumento) {

		preencherDataAtividade(atividade, decrementoPosicaoRelativaY, conteudoDocumento);
		preencherHorarioEntradaAtividade(monitor, decrementoPosicaoRelativaY, conteudoDocumento);
		preencherHorarioSaidaAtividade(monitor, decrementoPosicaoRelativaY, conteudoDocumento);
	}

	public byte[] gerarRelatorio(RelatorioFrequencia relatorio) {

		PdfStamper copia = null;
		OutputStream out = null;
		try {
			String path = URLDecoder.decode(getClass().getResource("selection.pdf").getPath(), "UTF-8");
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			PdfReader reader = new PdfReader(fis);
			out = new ByteArrayOutputStream();
			copia = new PdfStamper(reader, out);

		} catch (DocumentException | IOException e1) {

			e1.printStackTrace();
		}

		PdfContentByte conteudoDocumento = copia.getOverContent(1);

		preencherNomeMonitor(
						relatorio.getMonitor().getAluno().getNome().concat(" ").concat(relatorio.getMonitor().getAluno().getSobrenome()),
						conteudoDocumento);
		preencherNomeDisciplina(relatorio.getMonitor().getDisciplina().getDescricao(), conteudoDocumento);
		preencherNomeOrientador(relatorio.getMonitor().getDisciplina().getProfessor().getNome().concat(" ")
						.concat(relatorio.getMonitor().getDisciplina().getProfessor().getSobrenome()), conteudoDocumento);
		preencherMatricula(relatorio.getMonitor().getAluno().getMatricula(), conteudoDocumento);
		preencherNomeCurso(relatorio.getMonitor().getAluno().getCurso().getDescricao(), conteudoDocumento);
		preencherCampoMes(Util.obterNomeMes(relatorio.getMes()), conteudoDocumento);
		preencherCampoAno(String.valueOf(relatorio.getMonitor().getPeriodo().getAno()), conteudoDocumento);
		preencherCampoEdital(relatorio.getMonitor().getPeriodo().toString(), conteudoDocumento);

		int quantidadeAtividades = 0;
		int decrementoPosicaoRelativaY = 0;
		for (int posicaoSemana = 0; posicaoSemana < QUANTIDADE_SEMANAS; posicaoSemana++) {
			Semana semana = relatorio.getSemana(posicaoSemana);

			for (int i = 0; i < QUANTIDADE_ATIVIDADES; i++) {
				Atividade atividade = semana.getAtividade(i);
				if (atividade.getData() != null) {
					quantidadeAtividades++;
					preencherAtividadesSemanais(atividade, relatorio.getMonitor(), decrementoPosicaoRelativaY, conteudoDocumento);
				}
				decrementoPosicaoRelativaY += 14;
			}

		}
		long cargaHorariaMensal = relatorio.getMonitor().getCargaDiariaEmMinutos() * quantidadeAtividades;
		preencherCampoCargaHoraria(this.getCargaHorariaFormatada(cargaHorariaMensal), conteudoDocumento);
		try {
			preencherDescricaoPrimeiraSemana(relatorio.getSemana(0), conteudoDocumento);
			preencherDescricaoSegundaSemana(relatorio.getSemana(1), conteudoDocumento);
			preencherDescricaoTerceiraSemana(relatorio.getSemana(2), conteudoDocumento);
			preencherDescricaoQuartaSemana(relatorio.getSemana(3), conteudoDocumento);
			preencherDescricaoQuintaSemana(relatorio.getSemana(4), conteudoDocumento);
			copia.close();

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] content = ((ByteArrayOutputStream) out).toByteArray();
		return content;
	}

	private String getCargaHorariaFormatada(long cargaHoraria) {

		long minutos = cargaHoraria % 60;
		long horas = cargaHoraria - minutos;
		horas = horas / 60;
		StringBuilder builder = new StringBuilder();
		builder.append(horas);
		builder.append("H");
		builder.append(minutos);
		builder.append("M");
		return builder.toString();
	}

}
