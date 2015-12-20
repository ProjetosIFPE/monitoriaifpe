package br.com.projetoperiodo.model.instituto.aluno.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.instituto.curso.Curso;
import br.com.projetoperiodo.model.instituto.curso.impl.CursoImpl;
import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.model.instituto.disciplina.impl.DisciplinaImpl;
import br.com.projetoperiodo.model.usuario.impl.UsuarioImpl;

@Entity
@Table(name = "ALUNO")
@PrimaryKeyJoinColumn( name = "ALUNO_ID" )
@Polymorphism(type=PolymorphismType.EXPLICIT)
public class AlunoImpl extends UsuarioImpl implements Aluno
{
	@Column( name = "ALUNO_MATRICULA", nullable = false)
	private String matricula;
	@ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity= CursoImpl.class)
	@JoinColumn(name = "CURSO_ID", referencedColumnName = "CURSO_ID")
	private Curso curso;
	
	@ManyToMany(targetEntity=DisciplinaImpl.class, fetch=FetchType.EAGER)
	@JoinTable(name="DISCIPLINA_ALUNO",
	           joinColumns = @JoinColumn( name = "ALUNO_ID"),
	           inverseJoinColumns = @JoinColumn(name = "DISCIPLINA_ID"))
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	@Transient
	private final String PAPEL_ALUNO = "ALUNO";
	
	public AlunoImpl() {
		PAPEL = PAPEL_ALUNO;
	}
	
	/* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.aluno.impl.Aluno#getMatricula()
	 */
	@Override
	public String getMatricula() {

		return matricula;
	}
	/* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.aluno.impl.Aluno#setMatricula(java.lang.String)
	 */
	@Override
	public void setMatricula(String matricula) {

		this.matricula = matricula;
	}

	/* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.aluno.impl.Aluno#getCurso()
	 */
	@Override
	public Curso getCurso() {

		return curso;
	}
	/* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.aluno.impl.Aluno#setCurso(br.com.projetoperiodo.model.instituto.curso.Curso)
	 */
	@Override
	public void setCurso(Curso curso) {

		this.curso = curso;
	}
	/* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.aluno.impl.Aluno#getDisciplinas(int)
	 */
	@Override
	public Disciplina getDisciplinas(int index) {

		return disciplinas.get(index);
	}
	/* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.aluno.impl.Aluno#setDisciplinas(br.com.projetoperiodo.model.instituto.disciplina.Disciplina)
	 */
	@Override
	public void setDisciplinas(Disciplina disciplina) {

		this.disciplinas.add(disciplina);
	}
	
	@Override
	public String getPapelUsuario() {
	
		return "ALUNO";
	}
	
	@Override
	@GeneratedValue(generator="SQ_USUARIO", strategy= GenerationType.AUTO)
	public long getChavePrimaria() {
	
		// TODO Auto-generated method stub
		return super.getChavePrimaria();
	}

	@Override
	public int quantidadeDisciplinasCursadas() {

		return disciplinas.size();
	}
}
