package com.softwarecorporativo.monitoriaifpe.instituto.periodo;

import com.softwarecorporativo.monitoriaifpe.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Semestre;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_PERIODO")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "PERIODO_ID"))})
@Access(AccessType.FIELD)
public class Periodo extends EntidadeNegocio {

    @Min(1970)
    @Column(name = "PERIODO_ANO", nullable = false)
    private Integer ano;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "SEMESTRE", nullable = false)
    private Semestre semestre;

  
    public Integer getAno() {

        return ano;
    }

  
    public void setAno(Integer ano) {

        this.ano = ano;
    }

    
    public Semestre getSemestre() {

        return semestre;
    }

  
    public void setSemestre(Semestre semestre) {

        this.semestre = semestre;
    }

    
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append(getAno());
        builder.append('.');
        builder.append(getSemestre());
        return builder.toString();
    }
}
