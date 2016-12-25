package com.softwarecorporativo.monitoriaifpe.modelo.aluno;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.validation.ValidaMatricula;
import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tb_aluno")
@PrimaryKeyJoinColumn(name = "id_aluno")
@DiscriminatorValue(value = "A")
@Access(AccessType.FIELD)
@ValidaMatricula
@NamedQueries(value = {
    @NamedQuery(name = Aluno.ALUNO_POR_MATRICULA, query = "select a from Aluno as a where a.matricula = ?1"),
    @NamedQuery(name = Aluno.COUNT_ALUNO_POR_MATRICULA, query = "select count(a) from Aluno as a where a.matricula = ?1")})
public class Aluno extends Usuario {

    private static final long serialVersionUID = -5010100705429213855L;

    public static final String ALUNO_POR_MATRICULA = "alunoPorMatricula";
    public static final String COUNT_ALUNO_POR_MATRICULA = "countAlunoPorMatricula";
    
    @NotBlank
    @Column(name = "txt_matricula", nullable = false)
    private String matricula;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    private Curso curso;

    public String getMatricula() {

        return matricula;
    }

    public void setMatricula(String matricula) {

        this.matricula = matricula;
    }
    
    public String getDescricaoCursoAluno() {
        return getCurso().getDescricao();
    }

    public Curso getCurso() {

        return curso;
    }

    public void setCurso(Curso curso) {

        this.curso = curso;
    }

}
