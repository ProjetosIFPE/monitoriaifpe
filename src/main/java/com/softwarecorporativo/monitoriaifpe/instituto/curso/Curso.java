package com.softwarecorporativo.monitoriaifpe.instituto.curso;

import com.softwarecorporativo.monitoriaifpe.util.constantes.Grau;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.negocio.EntidadeNegocio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CURSO")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "CURSO_ID"))})
@Access(AccessType.FIELD)
public class Curso extends EntidadeNegocio  {

    @Column(name = "CURSO_DS", nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Grau grau;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Disciplina> disciplinas;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Aluno> alunos;

   
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
        disciplina.setCurso(this);
        this.disciplinas.add(disciplina);
    }

   
    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

   
    public Aluno getAluno(int index) {
        if (this.alunos == null) {
            this.alunos = new ArrayList<>();
        }
        return alunos.get(index);
    }

    
    public void addAluno(Aluno aluno) {
        if ( this.alunos == null ) {
            this.alunos = new ArrayList<>();
        }
        aluno.setCurso(this);
        this.alunos.add(aluno);
    }

 
    public Grau getModalidade() {

        return grau;
    }

    
    public void setModalidade(Grau modalidade) {

        this.grau = modalidade;
    }

}
