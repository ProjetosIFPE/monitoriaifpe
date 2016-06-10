package com.softwarecorporativo.monitoriaifpe.modelo.aluno;

import com.softwarecorporativo.monitoriaifpe.modelo.boletim.BoletimCurricular;
import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.modelo.aluno.validation.ValidaMatricula;
import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "TB_ALUNO")
@PrimaryKeyJoinColumn(name = "ALUNO_ID")
@DiscriminatorValue(value = "A")
@Access(AccessType.FIELD)
@ValidaMatricula
public class Aluno extends Usuario {

    private static final long serialVersionUID = -5010100705429213855L;

    @NotBlank
    @Column(name = "ALUNO_MATRICULA", nullable = false)
    private String matricula;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CURSO_ID", referencedColumnName = "CURSO_ID")
    private Curso curso;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aluno", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<BoletimCurricular> boletins;

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

    /**
     * *Recebe uma disciplina de monitoria para verificar se o aluno está apto
     * a exercer a atividade de monitoria na disciplina desta
     *
     * @param disciplinaMonitoria
     * @return
     */
    public Boolean validarMonitoriaDoAluno(Disciplina disciplinaMonitoria) {
        if (this.boletins != null) {
            for (BoletimCurricular boletimCurricular : this.boletins) {
                if (boletimCurricular.verificarAprovacaoEmComponenteCurricular(disciplinaMonitoria)) {
                    return Boolean.TRUE;
                } else if (boletimCurricular.verificarReprovacaoEmComponenteCurricular(disciplinaMonitoria)) {
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.FALSE;
    }

    public void addBoletimCurricular(BoletimCurricular boletimCurricular) {
        if (this.boletins == null) {
            this.boletins = new ArrayList<>();
        }
        boletimCurricular.setAluno(this);
        this.boletins.add(boletimCurricular);
    }

    public int quantidadeDisciplinasCursadas() {

        return boletins.size();
    }
}
