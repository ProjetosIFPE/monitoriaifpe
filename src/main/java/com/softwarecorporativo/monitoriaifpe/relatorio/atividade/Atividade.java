package com.softwarecorporativo.monitoriaifpe.relatorio.atividade;


import com.softwarecorporativo.monitoriaifpe.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.relatorio.semana.Semana;
import java.util.Date;

public interface Atividade extends EntidadeNegocio {

    Date getData();

    void setData(Date data);

    String getHorarioEntrada();

    void setHorarioEntrada(String horarioEntrada);

    String getHorarioSaida();

    void setHorarioSaida(String horarioSaida);

    Semana getSemana();

    void setSemana(Semana semana);

}
