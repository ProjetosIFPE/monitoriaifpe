package com.softwarecorporativo.monitoriaifpe.relatorio.frequencia;

import com.softwarecorporativo.monitoriaifpe.instituto.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.relatorio.semana.Semana;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Situacao;
import java.util.List;

public interface RelatorioFrequencia extends EntidadeNegocio {

    int getMes();

    void setMes(int mes);

    Monitoria getMonitoria();

    void setMonitoria(Monitoria monitoria);

    Semana getSemana(int index);
    
    List<Semana> getSemanas();
    
    void setSemanas(List<Semana> semanas);

    void setSemanas(Semana semana);

    Situacao getSituacao();

    void setSituacao(Situacao situacao);

    String getDescricaoMes();

}
