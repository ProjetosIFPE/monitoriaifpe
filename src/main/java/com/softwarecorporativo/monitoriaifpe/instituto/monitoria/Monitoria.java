package com.softwarecorporativo.monitoriaifpe.instituto.monitoria;

import com.softwarecorporativo.monitoriaifpe.instituto.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.instituto.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Modalidade;
import com.softwarecorporativo.monitoriaifpe.relatorio.frequencia.RelatorioFrequencia;
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

@Entity
@Table(name = "MONITORIA")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "MONITOR_ID"))})
@Access(AccessType.FIELD)
public class Monitoria extends EntidadeNegocio  {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Modalidade modalidade;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DISCIPLINA_ID", referencedColumnName = "DISCIPLINA_ID")
    private Disciplina disciplina;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ALUNO_ID", referencedColumnName = "ALUNO_ID")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PERIODO_ID", referencedColumnName = "PERIODO_ID")
    private Periodo periodo;

    //O relatório não é uma entidade. Modelagem artificial
    @OneToMany(mappedBy = "monitoria", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RelatorioFrequencia> relatoriosMensais;

    @Column(name = "HABILITADO")
    private boolean habilitado;

 
    public Modalidade getModalidade() {

        return modalidade;
    }

 
    public void setModalidade(Modalidade modalidade) {

        this.modalidade = modalidade;
    }

   
    public Disciplina getDisciplina() {

        return disciplina;
    }

 
    public void setDisciplina(Disciplina disciplina) {

        this.disciplina = disciplina;
    }

   
    public RelatorioFrequencia getRelatorioFrequencia(int index) {
        if ( this.relatoriosMensais == null ) {
            this.relatoriosMensais = new ArrayList<>();
        }
        return relatoriosMensais.get(index);
    }

  
    public void addRelatorio(RelatorioFrequencia relatorio) {
        if ( this.relatoriosMensais == null ) {
            this.relatoriosMensais = new ArrayList<>();
        }
        relatorio.setMonitoria(this);
        this.relatoriosMensais.add(relatorio);
    }

    
    public Periodo getPeriodo() {

        return periodo;
    }

    
    public void setPeriodo(Periodo periodo) {

        this.periodo = periodo;
    }

    
    public boolean isHabilitado() {

        return habilitado;
    }

    
    public void setHabilitado(boolean habilitado) {

        this.habilitado = habilitado;
    }

    
    public void setAluno(Aluno aluno) {

        this.aluno = aluno;

    }

    
    public Aluno getAluno() {

        return aluno;
    }

   
  

   
    

}