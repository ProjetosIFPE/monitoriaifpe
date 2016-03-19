package com.softwarecorporativo.monitoriaifpe.instituto.disciplina.impl;

import com.softwarecorporativo.monitoriaifpe.instituto.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.impl.CursoImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.instituto.professor.impl.ProfessorImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.impl.AlunoImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.negocio.impl.EntidadeNegocioImpl;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
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
import javax.persistence.Table;

@Entity
@Table(name = "DISCIPLINA")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "DISCIPLINA_ID"))})
@Access(AccessType.FIELD)
public class DisciplinaImpl extends EntidadeNegocioImpl implements Disciplina {

    @Column(name = "DISCIPLINA_DS", nullable = false)
    private String descricao;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = AlunoImpl.class)
    @JoinTable(name = "DISCIPLINA_ALUNO", joinColumns = @JoinColumn(name = "DISCIPLINA_ID"), inverseJoinColumns = @JoinColumn(name = "ALUNO_ID"))
    private List<Aluno> pagantes;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = CursoImpl.class)
    @JoinColumn(name = "CURSO_ID", referencedColumnName = "CURSO_ID")
    private Curso curso;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, optional = true, targetEntity = ProfessorImpl.class)
    @JoinColumn(name = "PROFESSOR_ID", referencedColumnName = "PROFESSOR_ID")
    private Professor professor;

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.disciplina.impl.Disciplina#getDescricao()
     */
    @Override
    public String getDescricao() {

        return descricao;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.disciplina.impl.Disciplina#setDescricao(java.lang.String)
     */
    @Override
    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.disciplina.impl.Disciplina#getPagantes()
     */
    @Override
    public Aluno getPagantes(int index) {
        if ( this.pagantes == null ) {
            this.pagantes = new ArrayList<>();
        }
        return pagantes.get(index);
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.disciplina.impl.Disciplina#setPagantes(java.util.Collection)
     */
    @Override
    public void setPagantes(Aluno aluno) {
        if ( this.pagantes == null ) {
            this.pagantes = new ArrayList<>();
        }
        aluno.setDisciplinas(this);
        this.pagantes.add(aluno);
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.disciplina.impl.Disciplina#getCurso()
     */
    @Override
    public Curso getCurso() {

        return curso;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.disciplina.impl.Disciplina#setCurso(br.com.projetoperiodo.model.instituto.curso.Curso)
     */
    @Override
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
