package com.softwarecorporativo.monitoriaifpe.relatorio.semana;

import com.softwarecorporativo.monitoriaifpe.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.relatorio.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.relatorio.frequencia.RelatorioFrequencia;

public interface Semana extends EntidadeNegocio {

    String getDescricao();

    String getObservacoes();

    void setDescricao(String descricao);

    void setObservacoes(String observacoes);

    Atividade getAtividade(int index);

    void setAtividades(Atividade atividade);

    RelatorioFrequencia getRelatorio();

    void setRelatorio(RelatorioFrequencia relatorio);

}
