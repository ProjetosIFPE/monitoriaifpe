/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.modelo.turma;

import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.modelo.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Edmilson
 */
@Entity
@Table(name = "tb_turma")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "id_turma"))})
@Access(AccessType.FIELD)
@NamedQueries(value = {
    @NamedQuery(name = Turma.TURMAS_POR_PROFESSOR,
            query = "select t from Turma as t where t.professor = ?1"),
    @NamedQuery(name = Turma.COUNT_TURMA_CADASTRADA,
            query = "select count(t) from Turma as t where t.periodo = ?1 and t.componenteCurricular = ?2"),
    @NamedQuery(name = Turma.TURMAS_OFERTADAS_POR_CURSO,
            query = "select t from Turma as t join t.componenteCurricular as c "
            + " where t.ofertada = ?1 and c.curso = ?2")})
public class Turma extends EntidadeNegocio {

    private static final long serialVersionUID = -7788698676039962643L;

    public static final String TURMAS_OFERTADAS_POR_CURSO = "turmasOfertadas";
    public static final String TURMAS_POR_PROFESSOR = "turmasPorProfessor";
    public static final String COUNT_TURMA_CADASTRADA = "countTurmaCadastrada";

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_componente_curricular", referencedColumnName = "id_componente_curricular")
    private ComponenteCurricular componenteCurricular;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_professor", referencedColumnName = "id_professor")
    private Professor professor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_periodo", referencedColumnName = "id_periodo")
    private Periodo periodo;

    @NotNull
    @Column(name = "turma_ofertada", nullable = false)
    private Boolean ofertada;

    @OneToMany(mappedBy = "turma", fetch = FetchType.LAZY)
    private List<Monitoria> monitorias;

    public String getDescricaoTurma() {
        return getComponenteCurricular().getDescricao();
    }

    public int obterAnoTurma() {

        return getPeriodo().getAno();
    }

    public Professor getProfessor() {

        return this.professor;
    }

    public void setProfessor(Professor professor) {

        this.professor = professor;

    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;

    }

    public ComponenteCurricular getComponenteCurricular() {
        return this.componenteCurricular;
    }

    public void setComponenteCurricular(ComponenteCurricular componenteCurricular) {
        this.componenteCurricular = componenteCurricular;
    }

    /**
     * Recebe um Componente Curricular para verificar se a disciplina possui o
     * mesmo
     *
     * @param disciplina
     * @return
     *
     */
    public Boolean verificarIgualdadeComponenteCurricular(Turma disciplina) {
        return this.getComponenteCurricular().equals(disciplina.getComponenteCurricular());
    }

    public Boolean isOfertada() {
        return ofertada;
    }

    public Monitoria getMonitoria(int index) {
        if (this.monitorias == null) {
            this.monitorias = new ArrayList<>();
        }
        return monitorias.get(index);
    }

    public void addMonitoria(Monitoria monitoria) {
        if (this.monitorias == null) {
            this.monitorias = new ArrayList<>();
        }
        monitoria.setTurma(this);
        this.monitorias.add(monitoria);
    }

    @Override
    public boolean isInativo() {
        return monitorias.isEmpty();
    }

    public void ofertar() {
        this.ofertada = Boolean.TRUE;
    }

    public void removerOferta() {
        this.ofertada = Boolean.FALSE;
    }

}
