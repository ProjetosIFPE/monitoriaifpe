package com.softwarecorporativo.monitoriaifpe.instituto.monitoria;

import com.softwarecorporativo.monitoriaifpe.instituto.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.instituto.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.relatorio.frequencia.RelatorioFrequencia;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Modalidade;

public interface Monitoria extends EntidadeNegocio {

    Modalidade getModalidade();

    void setModalidade(Modalidade modalidade);

    Disciplina getDisciplina();

    void setDisciplina(Disciplina disciplina);

    RelatorioFrequencia getRelatoriosMensais(int index);

    void setRelatoriosMensais(RelatorioFrequencia relatorio);

    Periodo getPeriodo();

    void setPeriodo(Periodo periodo);

    boolean isHabilitado();

    void setHabilitado(boolean habilitado);

    void setAluno(Aluno aluno);

    Aluno getAluno();

    String getHorarioEntrada();

    String getHorarioSaida();

    void setHorarioEntrada(String horario);

    void setHorarioSaida(String horario);

    long getCargaDiariaEmMinutos();

}
