package com.softwarecorporativo.monitoriaifpe.modelo.aluno;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.validation.ValidaMatricula;
import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Aluno extends Usuario {

    private static final long serialVersionUID = -5010100705429213855L;

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

    public Curso getCurso() {

        return curso;
    }

    public void setCurso(Curso curso) {

        this.curso = curso;
    }

}
