package com.softwarecorporativo.monitoriaifpe.instituto.periodo.impl;

import com.softwarecorporativo.monitoriaifpe.instituto.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.negocio.impl.EntidadeNegocioImpl;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Semestre;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "PERIODO")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "PERIODO_ID"))})
public class PeriodoImpl extends EntidadeNegocioImpl implements Periodo {

    @Column(name = "PERIODO_ANO")
    private int ano;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('PRIMEIRO', 'SEGUNDO')", name = "SEMESTRE")
    private Semestre semestre;

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.periodo.Periodo#getAno()
     */
    @Override
    public int getAno() {

        return ano;
    }

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.periodo.Periodo#setAno(int)
     */
    @Override
    public void setAno(int ano) {

        this.ano = ano;
    }

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.periodo.Periodo#getSemestre()
     */
    @Override
    public Semestre getSemestre() {

        return semestre;
    }

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.periodo.Periodo#setSemestre(br.com.projetoperiodo.util.constantes.enumeracoes.Semestre)
     */
    @Override
    public void setSemestre(Semestre semestre) {

        this.semestre = semestre;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append(getAno());
        builder.append('.');
        builder.append(getSemestre().semestre);
        return builder.toString();
    }
}
