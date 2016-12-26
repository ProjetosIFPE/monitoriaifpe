package com.softwarecorporativo.monitoriaifpe.modelo.monitoria;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.Turma;
import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.modelo.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.SituacaoMonitoria;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_monitoria")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "id_monitoria"))})
@Access(AccessType.FIELD)
@NamedQueries(value = {
    @NamedQuery(name = Monitoria.MONITORIA_SOLICITADA,
            query = "select m from Monitoria as m where ( m.situacaoMonitoria = ?1 or m.situacaoMonitoria = ?2 ) and m.aluno = ?3"),
    @NamedQuery(name = Monitoria.MONITORIA_POR_ALUNO,
            query = "select m from Monitoria as m where m.situacaoMonitoria = ?1 and m.aluno = ?2"),
    @NamedQuery(name = Monitoria.COUNT_MONITORIA_CADASTRADA,
            query = "select count(m) from Monitoria as m where m.aluno = ?1 and m.turma = ?2 and m.chavePrimaria != ?3"),
    @NamedQuery(name = Monitoria.MONITORIA_POR_PROFESSOR,
            query = "select m from Monitoria as m join m.turma as t where m.situacaoMonitoria = ?1 and t.professor = ?2")})
public class Monitoria extends EntidadeNegocio {

    private static final long serialVersionUID = -4572493586452867519L;

    public static final String MONITORIA_POR_ALUNO = "monitoriaPorSituacao";

    public static final String MONITORIA_SOLICITADA = "monitoriaSolicitada";

    public static final String COUNT_MONITORIA_CADASTRADA = "countMonitoriaCadastrada";

    public static final String MONITORIA_POR_PROFESSOR = "monitoriaPorProfessor";

    @NotNull
    @Column(name = "situacao_monitoria", nullable = false)
    @Enumerated(EnumType.STRING)
    private SituacaoMonitoria situacaoMonitoria;

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
    
    public String getDescricaoCursoMonitoria() {
        return getAluno().getDescricaoCursoAluno();
    }
    
    public String getMatriculaMonitor() {
        return getAluno().getMatricula();
    }

    public String getDescricaoTurmaMonitoria() {
        return getTurma().getDescricaoTurma();
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

    public byte[] getAssinaturaOrientador() {
        return turma.getProfessor().getAssinatura();
    }

    public Periodo getPeriodoMonitoria() {
        return turma.getPeriodo();
    }

    public SituacaoMonitoria getSituacaoMonitoria() {
        return situacaoMonitoria;
    }

    public Boolean isAprovada() {
        return situacaoMonitoria.equals(SituacaoMonitoria.APROVADA);
    }

    public void aprovar() {
        this.situacaoMonitoria = SituacaoMonitoria.APROVADA;
    }

    public void reprovar() {
        this.situacaoMonitoria = SituacaoMonitoria.REPROVADA;
    }

    public void aguardarAprovacao() {
        this.situacaoMonitoria = SituacaoMonitoria.AGUARDANDO_APROVACAO;
    }

}
