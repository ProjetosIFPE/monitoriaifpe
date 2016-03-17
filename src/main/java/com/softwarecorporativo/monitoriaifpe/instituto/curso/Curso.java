package com.softwarecorporativo.monitoriaifpe.instituto.curso;

import com.softwarecorporativo.monitoriaifpe.instituto.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Grau;

public interface Curso extends EntidadeNegocio {

    Disciplina getDisciplinas(int index);

    void setDisciplinas(Disciplina disciplina);

    String getDescricao();

    void setDescricao(String descricao);

    Aluno getAlunos(int index);

    void setAlunos(Aluno aluno);

    Grau getModalidade();

    void setModalidade(Grau modalidade);

}
