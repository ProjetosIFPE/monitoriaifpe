
package br.com.projetoperiodo.model.instituto.disciplina.controller;

import java.util.List;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.model.instituto.professor.Professor;
import br.com.projetoperiodo.model.negocio.controlador.ControladorNegocio;
import br.com.projetoperiodo.util.exception.NegocioException;

public interface ControladorDisciplina extends ControladorNegocio
{
	List<Disciplina> listarDisciplinasCadastradas();
	
	Disciplina buscarDisciplina(String descricao) throws NegocioException;

	List<Disciplina> listarDisciplinasDeAluno(Aluno aluno) throws NegocioException;

	List<Disciplina> listarDisciplinasDeProfessor(Professor professor);

	Disciplina buscarDisciplina(long chavePrimaria);

	List<Disciplina> buscarDisciplinasSemProfessor();

	void atualizarDisciplina(Disciplina disciplina);

	void retirarVinculoDeProfessorDaDisciplina(long chaveDisciplina);

	List<Disciplina> buscarDisciplinasComProfessor();

	List<Disciplina> listarDisciplinasComProfessorDisponiveisParaAluno(Aluno aluno);
	
}