package com.softwarecorporativo.monitoriaifpe.relatorio.atividade;



import com.softwarecorporativo.monitoriaifpe.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.relatorio.semana.Semana;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_ATIVIDADE")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "ATIVIDADE_ID"))})
@Access(AccessType.FIELD)
public class Atividade extends EntidadeNegocio  {

    @Column(name = "HORARIO_SAIDA", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date horarioEntrada;
 
    @Column(name = "HORARIO_ENTRADA", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date horarioSaida;
    
    @Column(name = "ATIVIDADE_DATA", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date data;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "SEMANA_ID", referencedColumnName = "SEMANA_ID")
    private Semana semana;

   
    public Date getData() {
        return this.data;
    }

 
    public void setData(Date data) {
        this.data = data;
    }

  
    public Date getHorarioEntrada() {

        return horarioEntrada;
    }

  
    public void setHorarioEntrada(Date horarioEntrada) {

        this.horarioEntrada = horarioEntrada;
    }


    public Date getHorarioSaida() {

        return horarioSaida;
    }

  
    public void setHorarioSaida(Date horarioSaida) {

        this.horarioSaida = horarioSaida;
    }

  
    public Semana getSemana() {

        return semana;
    }

   
    public void setSemana(Semana semana) {

        this.semana = semana;
    }

}
