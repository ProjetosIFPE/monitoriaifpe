
package br.com.projetoperiodo.model.instituto.disciplina.controller.impl;

import java.util.HashMap;
import java.util.List;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.model.instituto.disciplina.controller.ControladorDisciplina;
import br.com.projetoperiodo.model.instituto.disciplina.impl.DisciplinaImpl;
import br.com.projetoperiodo.model.instituto.professor.Professor;
import br.com.projetoperiodo.model.negocio.controlador.ControladorNegocioImpl;
import br.com.projetoperiodo.model.negocio.entidade.EntidadeNegocio;
import br.com.projetoperiodo.util.exception.NegocioException;
import br.com.projetoperiodo.util.fachada.Persistencia;

public class ControladorDisciplinaImpl extends ControladorNegocioImpl implements ControladorDisciplina {

	public ControladorDisciplinaImpl() {

	}

	@Override
	public EntidadeNegocio criarEntidadeNegocio() {

		return new DisciplinaImpl();
	}

	@Override
	public List<Disciplina> listarDisciplinasCadastradas() {

		return Persistencia.getInstance().listarDisciplinas();
	}

	@Override
	public List<Disciplina> listarDisciplinasDeProfessor(Professor professor) {

		return  Persistencia.getInstance().buscarDisciplinasDeProfessor(professor);
	}

	@Override
	public List<Disciplina> listarDisciplinasDeAluno(Aluno aluno) {

		return Persistencia.getInstance().buscarDisciplinasDeAluno(aluno);
	}

	@Override
	public List<Disciplina> buscarDisciplinasSemProfessor() {

		return Persistencia.getInstance().buscarDisciplinasSemProfessor();
	}

	@Override
	public Disciplina buscarDisciplina(String descricao) throws NegocioException {

		HashMap<String, Object> filtro = new HashMap<>();
		filtro.put("descricao", descricao);

		return (Disciplina) Persistencia.getInstance().buscarDisciplina(filtro);
	}

	@Override
	public Disciplina buscarDisciplina(long chavePrimaria) {

		return (Disciplina) Persistencia.getInstance().buscarDisciplina(chavePrimaria);
	}

	@Override
	public String getNomeClasseEntidade() {

		return DisciplinaImpl.class.getSimpleName();
	}

	@Override
	public void atualizarDisciplina(Disciplina disciplina) {

		Persistencia.getInstance().atualizarDisciplina(disciplina);
	}
	
	@Override
	public void retirarVinculoDeProfessorDaDisciplina(long chaveDisciplina) {
		Persistencia.getInstance().removerProfessorDeDisciplina(chaveDisciplina);
	}

}
