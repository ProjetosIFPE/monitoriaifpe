package com.softwarecorporativo.monitoriaifpe.instituto.monitor.impl;

import com.softwarecorporativo.monitoriaifpe.instituto.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.impl.AlunoImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.impl.DisciplinaImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.monitor.Monitoria;
import com.softwarecorporativo.monitoriaifpe.instituto.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.instituto.periodo.impl.PeriodoImpl;
import com.softwarecorporativo.monitoriaifpe.negocio.impl.EntidadeNegocioImpl;
import com.softwarecorporativo.monitoriaifpe.relatorio.frequencia.impl.RelatorioFrequenciaImpl;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Modalidade;
import com.softwarecorporativo.monitoriaifpe.relatorio.frequencia.RelatorioFrequencia;
import java.util.ArrayList;
import java.util.List;
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

@Entity
@Table(name = "MONITORIA")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "MONITOR_ID"))})
public class MonitorImpl extends EntidadeNegocioImpl implements Monitoria {

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('BOLSISTA', 'VOLUNTARIO')")
    private Modalidade modalidade;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = DisciplinaImpl.class)
    @JoinColumn(name = "DISCIPLINA_ID", referencedColumnName = "DISCIPLINA_ID")
    private Disciplina disciplina;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = AlunoImpl.class)
    @JoinColumn(name = "ALUNO_ID", referencedColumnName = "ALUNO_ID")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = PeriodoImpl.class)
    @JoinColumn(name = "PERIODO_ID", referencedColumnName = "PERIODO_ID")
    private Periodo periodo;

    @Column(name = "HORARIO_SAIDA", nullable = true)
    private String horario_entrada;
    @Column(name = "HORARIO_ENTRADA", nullable = true)
    private String horario_saida;

    @OneToMany(mappedBy = "monitor", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, targetEntity = RelatorioFrequenciaImpl.class)
    private List<RelatorioFrequencia> relatoriosMensais;

    @Column(name = "HABILITADO")
    private boolean habilitado;

    public MonitorImpl() {
        relatoriosMensais = new ArrayList<RelatorioFrequencia>();
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.monitor.impl.Monitor#getModalidade()
     */
    public Modalidade getModalidade() {

        return modalidade;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.monitor.impl.Monitor#setModalidade(br.com.projetoperiodo.util.constantes.enumeracoes.Modalidade)
     */
    public void setModalidade(Modalidade modalidade) {

        this.modalidade = modalidade;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.monitor.impl.Monitor#getDisciplina()
     */
    public Disciplina getDisciplina() {

        return disciplina;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.monitor.impl.Monitor#setDisciplina(br.com.projetoperiodo.model.instituto.disciplina.Disciplina)
     */
    public void setDisciplina(Disciplina disciplina) {

        this.disciplina = disciplina;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.monitor.impl.Monitor#getRelatoriosMensais()
     */
    @Override
    public RelatorioFrequencia getRelatoriosMensais(int index) {

        return relatoriosMensais.get(index);
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.monitor.impl.Monitor#setRelatoriosMensais(java.util.Collection)
     */
    @Override
    public void setRelatoriosMensais(RelatorioFrequencia relatorio) {

        this.relatoriosMensais.add(relatorio);
    }

    @Override
    public Periodo getPeriodo() {

        return periodo;
    }

    @Override
    public void setPeriodo(Periodo periodo) {

        this.periodo = periodo;
    }

    @Override
    public boolean isHabilitado() {

        return habilitado;
    }

    @Override
    public void setHabilitado(boolean habilitado) {

        this.habilitado = habilitado;
    }

    @Override
    public void setAluno(Aluno aluno) {

        this.aluno = aluno;

    }

    @Override
    public Aluno getAluno() {

        return aluno;
    }

    @Override
    public String getHorarioEntrada() {

        return this.horario_entrada;
    }

    @Override
    public String getHorarioSaida() {

        return this.horario_saida;
    }

    @Override
    public void setHorarioEntrada(String horario) {
        this.horario_entrada = horario;

    }

    @Override
    public void setHorarioSaida(String horario) {
        this.horario_saida = horario;

    }

    @Override
    public long getCargaDiariaEmMinutos() {
        String[] entrada = this.getHorarioEntrada().split(":");
        String[] saida = this.getHorarioSaida().split(":");
        long horasEntrada = Long.valueOf(entrada[0]);
        long minutosEntrada = Long.valueOf(entrada[1]) + horasEntrada * 60;
        long horasSaida = Long.valueOf(saida[0]);
        long minutosSaida = Long.valueOf(saida[1]) + horasSaida * 60;

        return (minutosSaida - minutosEntrada);
    }

}
