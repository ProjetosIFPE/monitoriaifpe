/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.boletim;

import com.softwarecorporativo.monitoriaifpe.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.util.constantes.SituacaoDisciplina;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Edmilson
 */
@Entity
@Table(name = "TB_BOLETIM_CURRICULAR")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "BOLETIM_CURRICULAR_ID"))})
@Access(AccessType.FIELD)
public class BoletimCurricular extends EntidadeNegocio {

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "DISCIPLINA_ID", referencedColumnName = "DISCIPLINA_ID")
    private Disciplina disciplina;

    @DecimalMax(value = "10.0")
    @DecimalMin(value = "0.0")
    @Column(name = "NOTA_BOLETIM")
    private Double nota;

    @DecimalMax(value = "100.0")
    @DecimalMin(value = "0.0")
    @Column(name = "FREQUENCIA_BOLETIM")
    private Double frequencia;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ALUNO_ID", referencedColumnName = "ALUNO_ID")
    private Aluno aluno;

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Boolean isAlunoAprovadoEmDisciplina() {
        Boolean isAprovado = Boolean.FALSE;
        if (this.nota >= 7.0 && this.frequencia >= 75.00) {
            isAprovado = Boolean.TRUE;
        }
        return isAprovado;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Double getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Double frequencia) {
        this.frequencia = frequencia;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    /**
     * Recebe a disciplina de uma monitoria e verifica se o boletim curricular está apto 
     * a exercer a atividade de monitoria para a disciplina recebida.
     * @param disciplinaMonitoria
     * @return 
     */
    public Boolean validarBoletimParaExercerMonitoria(Disciplina disciplinaMonitoria) {
        Boolean isValido = Boolean.TRUE;
        if (this.getDisciplina().verificarIgualdadeComponenteCurricular(disciplinaMonitoria)
                && this.isAlunoAprovadoEmDisciplina()) {
            isValido = Boolean.TRUE;
        } else if (this.getDisciplina().verificarIgualdadeComponenteCurricular(disciplinaMonitoria)
                && !this.isAlunoAprovadoEmDisciplina()) {
            isValido = Boolean.FALSE;
        }
        return isValido;
    }

}
