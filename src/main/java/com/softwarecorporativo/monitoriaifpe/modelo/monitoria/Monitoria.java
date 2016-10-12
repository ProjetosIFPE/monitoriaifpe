package com.softwarecorporativo.monitoriaifpe.modelo.monitoria;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.Turma;
import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.modelo.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Modalidade;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_monitoria")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "id_monitoria"))})
@Access(AccessType.FIELD)
public class Monitoria extends EntidadeNegocio {

    private static final long serialVersionUID = -4572493586452867519L;

    @NotNull
    @Column(name = "monitoria_aprovada", nullable = false)
    private Boolean aprovada;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_turma", referencedColumnName = "id_turma")
    private Turma turma;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_aluno", referencedColumnName = "id_aluno")
    private Aluno aluno;

    @OneToMany(mappedBy = "monitoria", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atividade> atividades;

    public int getAnoMonitoria() {
        return turma.obterAnoTurma();
    }

    public String getEditalMonitoria() {
        return turma.getPeriodo().toString();
    }


    public Turma getTurma() {

        return turma;
    }

    public void setTurma(Turma turma) {

        this.turma = turma;
    }

    public Atividade getAtividade(int index) {
        if (this.atividades == null) {
            this.atividades = new ArrayList<>();
        }
        return atividades.get(index);
    }

    public void addAtividade(Atividade atividade) {
        if (this.atividades == null) {
            this.atividades = new ArrayList<>();
        }
        atividade.setMonitoria(this);
        this.atividades.add(atividade);
    }

    public void setAluno(Aluno aluno) {

        this.aluno = aluno;

    }

    public Aluno getAluno() {

        return aluno;
    }

    public String getNomeMonitor() {
        return aluno.getNomeCompleto();
    }

    public String getNomeOrientador() {
        return turma.getProfessor().getNomeCompleto();
    }

    public Periodo getPeriodoMonitoria() {
        return turma.getPeriodo();
    }

    public Boolean isAprovada() {
        return aprovada;
    }

    public void setAprovada(Boolean aprovada) {
        this.aprovada = aprovada;
    }

}
