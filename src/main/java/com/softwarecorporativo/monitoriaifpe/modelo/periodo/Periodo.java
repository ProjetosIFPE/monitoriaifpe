package com.softwarecorporativo.monitoriaifpe.modelo.periodo;

import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Semestre;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_periodo")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "id_periodo"))})
@Access(AccessType.FIELD)
@NamedQueries(value = {
    @NamedQuery(name = Periodo.PERIODO_POR_ANO_SEMESTRE,
            query = "select p from Periodo as p where p.ano = ?1 and p.semestre = ?2"),
    @NamedQuery(name = Periodo.PERIODO_CADASTRADO,
            query = "select count(p) from Periodo as p where p.ano = ?1 and p.semestre = ?2 and p.chavePrimaria != ?3")})
public class Periodo extends EntidadeNegocio {

    public static final String PERIODO_CADASTRADO = "periodoCadastrado";
    public static final String PERIODO_POR_ANO_SEMESTRE = "periodoPorAnoSemestre";

    private static final long serialVersionUID = 21290824252510458L;

    @Min(value = 1970, message = "{com.softwarecorporativo.monitoriaifpe.periodo.ano}")
    @Column(name = "ano", nullable = false)
    private Integer ano;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('PRIMEIRO', 'SEGUNDO')", name = "semestre", nullable = false)
    private Semestre semestre;

    private static final int MES_INICIO_SEGUNDO_SEMESTRE = 7;
    private static final int DIA_INICIO_SEGUNDO_SEMESTRE = 1;

    public Periodo() {
        calcularPeriodo();
    }

    public Periodo(Date data) {
        calcularPeriodo(data);
    }

    private void calcularPeriodo() {
        Calendar calendario = Calendar.getInstance();
        calcularPeriodo(calendario.getTime());
    }

    private void calcularPeriodo(Date data) {
        if (data != null) {
            this.setAnoPorData(data);
            this.setSemestrePorData(data);
        }
    }

    private void setAnoPorData(Date data) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        this.ano = calendario.get(Calendar.YEAR);
    }

    private void setSemestrePorData(Date data) {
        Date inicioSegundoSemestre = this.getDataInicioSegundoSemestre();

        if (inicioSegundoSemestre.before(data)
                || inicioSegundoSemestre.equals(data)) {
            this.setSemestre(Semestre.SEGUNDO);
        } else {
            this.setSemestre(Semestre.PRIMEIRO);
        }
    }

    private Date getDataInicioSegundoSemestre() {
        Calendar inicioSegundoSemestre = Calendar.getInstance();
        inicioSegundoSemestre.set(this.ano, MES_INICIO_SEGUNDO_SEMESTRE,
                DIA_INICIO_SEGUNDO_SEMESTRE);
        return inicioSegundoSemestre.getTime();
    }

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

    public Date getDataInicioPeriodo() {
        return null;
    }

    public Date getDataFimPeriodo() {
        return null;
    }

    public boolean dataEstaEmPeriodo(Date data) {

        return true;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append(getAno());
        builder.append('.');
        builder.append(getSemestre().ordinal() + 1);
        return builder.toString();
    }
}
