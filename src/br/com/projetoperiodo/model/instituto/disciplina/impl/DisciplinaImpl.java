
package br.com.projetoperiodo.model.instituto.disciplina.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.instituto.aluno.impl.AlunoImpl;
import br.com.projetoperiodo.model.instituto.curso.Curso;
import br.com.projetoperiodo.model.instituto.curso.impl.CursoImpl;
import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.model.instituto.professor.Professor;
import br.com.projetoperiodo.model.instituto.professor.impl.ProfessorImpl;
import br.com.projetoperiodo.model.negocio.entidade.impl.EntidadeNegocioImpl;

@Entity
@Table(name = "DISCIPLINA")
@AttributeOverrides({@AttributeOverride(name = "chavePrimaria", column = @Column(name = "DISCIPLINA_ID") )})
@SequenceGenerator(name="SQ_ENTIDADE", sequenceName="SQ_DISCIPLINA")
public class DisciplinaImpl extends EntidadeNegocioImpl implements Disciplina {

	@Column(name = "DISCIPLINA_DS")
	private String descricao;

	@ManyToMany(fetch = FetchType.LAZY, targetEntity = AlunoImpl.class)
	@JoinTable(name = "DISCIPLINA_ALUNO", joinColumns = @JoinColumn(name = "DISCIPLINA_ID") , inverseJoinColumns = @JoinColumn(name = "ALUNO_ID") )
	private List<Aluno> pagantes;

	@ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = CursoImpl.class)
	@JoinColumn(name = "CURSO_ID", referencedColumnName = "CURSO_ID")
	private Curso curso;

	@ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.MERGE, optional = true, targetEntity = ProfessorImpl.class)
	@JoinColumn(name = "PROFESSOR_ID", referencedColumnName = "PROFESSOR_ID")
	private Professor professor;

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.disciplina.impl.Disciplina#getDescricao()
	 */
	public String getDescricao() {

		return descricao;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.disciplina.impl.Disciplina#setDescricao(java.lang.String)
	 */
	public void setDescricao(String descricao) {

		this.descricao = descricao;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.disciplina.impl.Disciplina#getPagantes()
	 */
	public Aluno getPagantes(int index) {

		return pagantes.get(index);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.disciplina.impl.Disciplina#setPagantes(java.util.Collection)
	 */
	public void setPagantes(Aluno aluno) {

		this.pagantes.add(aluno);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.disciplina.impl.Disciplina#getCurso()
	 */
	public Curso getCurso() {

		return curso;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.disciplina.impl.Disciplina#setCurso(br.com.projetoperiodo.model.instituto.curso.Curso)
	 */
	public void setCurso(Curso curso) {

		this.curso = curso;
	}

	@Override
	public Professor getProfessor() {

		return this.professor;
	}

	@Override
	public void setProfessor(Professor professor) {

		this.professor = professor;

	}

}
