
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

	private static final String DISCIPLINAS_INDISPONIVEIS = "Você não possui mais disciplinas ofertadas, "
					+ "candidatas a monitoria. Adicione mais disciplinas.";
	private static final String DISCIPLINA_INVALIDA = "A Disciplina informada não existe.";
	
	public ControladorDisciplinaImpl() {
		super();
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
	public List<Disciplina> listarDisciplinasDeAluno(Aluno aluno) throws NegocioException {
		List<Disciplina> listaDisciplinas = Persistencia.getInstance().buscarDisciplinasDeAluno(aluno);
		if ( listaDisciplinas.isEmpty() ) {
			throw new NegocioException(DISCIPLINAS_INDISPONIVEIS);
		}
		return listaDisciplinas;
	}
	@Override
	public List<Disciplina> listarDisciplinasComProfessorDisponiveisParaAluno(Aluno aluno) {
		List<Disciplina> disciplinasComProfessor = Persistencia.getInstance().buscarDisciplinasComProfessor();
		for ( int i = 0; i < aluno.quantidadeDisciplinasCursadas(); i++  ) {
			Disciplina disciplinaCursada = aluno.getDisciplinas(i);
			for ( Disciplina disciplinaComProfessor: disciplinasComProfessor ) {
				if ( disciplinaComProfessor.getDescricao().equals(disciplinaCursada.getDescricao()) ) {
					disciplinasComProfessor.remove(disciplinaComProfessor);
					break;
				}
			}
		}
		return disciplinasComProfessor;
	}

	@Override
	public List<Disciplina> buscarDisciplinasSemProfessor() {

		return Persistencia.getInstance().buscarDisciplinasSemProfessor();
	}
	
	@Override
	public List<Disciplina> buscarDisciplinasComProfessor() {
		return Persistencia.getInstance().buscarDisciplinasComProfessor();
	}

	@Override
	public Disciplina buscarDisciplina(String descricao) throws NegocioException{

		HashMap<String, Object> filtro = new HashMap<>();
		filtro.put("descricao", descricao);
		Disciplina disciplina;
		try {
			disciplina = (Disciplina) Persistencia.getInstance().buscarDisciplina(filtro);
			return disciplina;
		} catch (NegocioException e) {
			throw new NegocioException(DISCIPLINA_INVALIDA);
		}
		
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
