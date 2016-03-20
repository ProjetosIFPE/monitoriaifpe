package com.softwarecorporativo.monitoriaifpe.relatorio.frequencia;

import com.softwarecorporativo.monitoriaifpe.instituto.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.negocio.EntidadeNegocio;

import com.softwarecorporativo.monitoriaifpe.util.constantes.Situacao;
import com.softwarecorporativo.monitoriaifpe.relatorio.semana.Semana;
import com.softwarecorporativo.monitoriaifpe.util.Util;
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
@Table(name = "RELATORIO_FREQUENCIA")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "RELATORIO_ID"))})
@Access(AccessType.FIELD)
public class RelatorioFrequencia extends EntidadeNegocio {

    @Column(name = "RELATORIO_MES", nullable = false)
    private int mes;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MONITOR_ID", referencedColumnName = "MONITOR_ID")
    private Monitoria monitoria;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO_RELATORIO", nullable = false)
    private Situacao situacao;

    @OneToMany(mappedBy = "relatorio", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Semana> semanas;


  
    public int getMes() {

        return mes;
    }

  
    public String getDescricaoMes() {
        return Util.obterNomeMes(this.mes);
    }

    
    public void setMes(int mes) {

        this.mes = mes;
    }

   
    public Monitoria getMonitoria() {

        return monitoria;
    }

 
    public void setMonitoria(Monitoria monitoria) {

        this.monitoria = monitoria;
    }

   
    public Semana getSemana(int index) {
        if ( semanas == null ) {
            semanas = new ArrayList<>();
        }
        return semanas.get(index);
    }
    
 
    public List<Semana> getSemanas() {
        return semanas;
    }
    
    
    public void setSemanas(List<Semana> semanas) {
        this.semanas = semanas;
    }

  
    public void addSemana(Semana semana) {
        if ( semanas == null ) {
            semanas = new ArrayList<>();
        }
        semana.setRelatorio(this);
        this.semanas.add(semana);
    }

    
    public Situacao getSituacao() {
        return situacao;
    }

   
    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }
}
