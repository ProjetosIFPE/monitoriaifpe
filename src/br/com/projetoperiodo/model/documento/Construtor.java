package br.com.projetoperiodo.model.documento;

import br.com.projetoperiodo.model.instituto.curso.Curso;
import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.model.instituto.monitor.Monitoria;
import br.com.projetoperiodo.model.instituto.professor.Professor;
import br.com.projetoperiodo.model.relatorio.frequencia.RelatorioFrequencia;

public abstract class Construtor {
	protected byte[] documento;
	
	public byte[] construirDocumento(RelatorioFrequencia relatorio) {
		this.documento  = criarDocumento(relatorio);
		adicionarAno(relatorio.getMonitor().getPeriodo().getAno());
		adicionarMes(relatorio.getMes());
		adicionarAtividades(relatorio);
		adicionarSemanas(relatorio);
		adicionarProfessor(relatorio.getMonitor().getDisciplina().getProfessor());
		adicionarMonitor(relatorio.getMonitor());
	//	adicionarCargaHoraria();
		adicionarEdital(relatorio.getMonitor().getPeriodo().toString());
		adicionarCurso(relatorio.getMonitor().getAluno().getCurso());
		adicionarDisciplina(relatorio.getMonitor().getDisciplina());
		return documento;
	}
	protected abstract byte[] criarDocumento(RelatorioFrequencia relatorio);
	protected abstract void adicionarMonitor(Monitoria monitor);
	protected abstract void adicionarMes(int mes);
	protected abstract void adicionarAno(int ano);
	protected abstract void adicionarEdital(String edital);
	protected abstract void adicionarCurso(Curso curso);
	protected abstract void adicionarSemanas(RelatorioFrequencia relatorio);
	protected abstract void adicionarAtividades(RelatorioFrequencia relatorio);
	protected abstract void adicionarProfessor(Professor professor);
	protected abstract void adicionarCargaHoraria(String cargaHoraria);
	protected abstract void adicionarDisciplina(Disciplina disciplina);
	
}
