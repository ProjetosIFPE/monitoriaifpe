package com.softwarecorporativo.monitoriaifpe.curso;

import com.softwarecorporativo.monitoriaifpe.util.constantes.Grau;
import com.softwarecorporativo.monitoriaifpe.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.disciplina.Disciplina;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "TB_CURSO")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "CURSO_ID"))})
@Access(AccessType.FIELD)
public class Curso extends EntidadeNegocio  {

    @NotBlank
    @Size(min = 1, max = 100)
    @Pattern(regexp = "^[A-Z]{1}\\D+$", message = "{com.softwarecorporativo.monitoriaifpe.curso.descricao}")
    @Column(name = "CURSO_DS", nullable = false)
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "GRAU", nullable = false)
    private Grau grau;

    /* TODO: Verificar se é necessário colocar campo no equals */
    @NotBlank
    @Pattern(regexp = "^[A-Z][0-9]$", message = "{com.softwarecorporativo.monitoriaifpe.curso.codigoCurso}")
    @Column(name = "CODIGO_CURSO", nullable = false, unique = true)
    private String codigoCurso;
    
    @NotBlank
    @Pattern(regexp = "^[A-Z]{2}$", message = "{com.softwarecorporativo.monitoriaifpe.curso.codigoCampus}")
    @Column(name = "CODIGO_CAMPUS", nullable = false)
    private String codigoCampus;
    
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Disciplina> disciplinas;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
    
     public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getCodigoCampus() {
        return codigoCampus;
    }

    public void setCodigoCampus(String codigoCampus) {
        this.codigoCampus = codigoCampus;
    }

    
    

}
