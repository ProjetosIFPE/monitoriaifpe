package com.softwarecorporativo.monitoriaifpe.modelo.professor;

import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_PROFESSOR")
@PrimaryKeyJoinColumn(name = "PROFESSOR_ID")
@DiscriminatorValue(value = "P")
@Access(AccessType.FIELD)
public class Professor extends Usuario {

    private static final long serialVersionUID = -8880339274163624313L;

    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    private List<Disciplina> disciplinas;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CURSO_ID", referencedColumnName = "CURSO_ID")
    private Curso curso;

    public Disciplina getDisciplina(int index) {
        if (this.disciplinas == null) {
            this.disciplinas = new ArrayList<>();
        }
        return disciplinas.get(index);
    }

    public void addDisciplina(Disciplina disciplina) {
        if (this.disciplinas == null) {
            this.disciplinas = new ArrayList<>();
        }
        disciplina.setProfessor(this);
        this.disciplinas.add(disciplina);
    }

    public int getQuantidadeDisciplinasDoProfessor() {
        return disciplinas.size();
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    
}
