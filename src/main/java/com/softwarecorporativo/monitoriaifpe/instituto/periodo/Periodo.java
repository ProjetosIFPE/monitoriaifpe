package com.softwarecorporativo.monitoriaifpe.instituto.periodo;


import com.softwarecorporativo.monitoriaifpe.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.util.constantes.enumeracoes.Semestre;

public interface Periodo extends EntidadeNegocio {

    int getAno();

    void setAno(int ano);

    Semestre getSemestre();

    void setSemestre(Semestre semestre);

}
