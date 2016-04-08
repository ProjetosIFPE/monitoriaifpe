package com.softwarecorporativo.monitoriaifpe.instituto.aluno;

import com.softwarecorporativo.monitoriaifpe.instituto.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.usuario.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;



@Entity
@Table(name = "TB_ALUNO")
@PrimaryKeyJoinColumn(name = "ALUNO_ID")
@DiscriminatorValue(value = "A")
@Access(AccessType.FIELD)
public class Aluno extends Usuario  {

    @Column(name = "ALUNO_MATRICULA", nullable = false)
    private String matricula;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CURSO_ID", referencedColumnName = "CURSO_ID")
    private Curso curso;

    @ManyToMany(targetEntity = Disciplina.class, fetch = FetchType.LAZY)
    @JoinTable(name = "TB_DISCIPLINA_ALUNO",
            joinColumns = @JoinColumn(name = "ALUNO_ID"),
            inverseJoinColumns = @JoinColumn(name = "DISCIPLINA_ID"))
    private List<Disciplina> disciplinas;
    
   
    public String getMatricula() {

        return matricula;
    }

    public void setMatricula(String matricula) {

        this.matricula = matricula;
    }

   
    public Curso getCurso() {

        return curso;
    }

   
    public void setCurso(Curso curso) {

        this.curso = curso;
    }

  
    public Disciplina getDisciplina(int index) {
        if ( this.disciplinas == null ) {
            this.disciplinas = new ArrayList<>();
        }
        return disciplinas.get(index);
    }

    
    public void addDisciplina(Disciplina disciplina) {
        if ( this.disciplinas == null ) {
            this.disciplinas = new ArrayList<>();
        }
        this.disciplinas.add(disciplina);
    }

    
    public int quantidadeDisciplinasCursadas() {

        return disciplinas.size();
    }
}
