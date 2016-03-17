package com.softwarecorporativo.monitoriaifpe.relatorio.frequencia;

import com.softwarecorporativo.monitoriaifpe.instituto.monitor.Monitoria;
import com.softwarecorporativo.monitoriaifpe.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.relatorio.semana.Semana;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Situacao;

public interface RelatorioFrequencia extends EntidadeNegocio {

    int getMes();

    void setMes(int mes);

    Monitoria getMonitor();

    void setMonitor(Monitoria monitor);

    Semana getSemana(int index);

    void setSemanas(Semana semana);

    Situacao getSituacao();

    void setSituacao(Situacao situacao);

    String getDescricaoMes();

}
