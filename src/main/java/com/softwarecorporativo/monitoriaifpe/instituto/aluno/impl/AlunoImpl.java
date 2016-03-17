package com.softwarecorporativo.monitoriaifpe.instituto.aluno.impl;

import com.softwarecorporativo.monitoriaifpe.instituto.curso.Curso;

import com.softwarecorporativo.monitoriaifpe.instituto.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.impl.CursoImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.impl.DisciplinaImpl;
import com.softwarecorporativo.monitoriaifpe.usuario.impl.UsuarioImpl;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "ALUNO")
@PrimaryKeyJoinColumn(name = "ALUNO_ID")
public class AlunoImpl extends UsuarioImpl implements Aluno {

    @Column(name = "ALUNO_MATRICULA", nullable = false)
    private String matricula;
    @ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = CursoImpl.class)
    @JoinColumn(name = "CURSO_ID", referencedColumnName = "CURSO_ID")
    private Curso curso;

    @ManyToMany(targetEntity = DisciplinaImpl.class, fetch = FetchType.EAGER)
    @JoinTable(name = "DISCIPLINA_ALUNO",
            joinColumns = @JoinColumn(name = "ALUNO_ID"),
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
    @GeneratedValue(generator = "SQ_USUARIO", strategy = GenerationType.AUTO)
    public long getChavePrimaria() {

        // TODO Auto-generated method stub
        return super.getChavePrimaria();
    }

    @Override
    public int quantidadeDisciplinasCursadas() {

        return disciplinas.size();
    }
}
