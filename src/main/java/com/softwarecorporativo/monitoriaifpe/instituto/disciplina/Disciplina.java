package com.softwarecorporativo.monitoriaifpe.instituto.disciplina;

import com.softwarecorporativo.monitoriaifpe.instituto.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.instituto.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.negocio.EntidadeNegocio;

public interface Disciplina extends EntidadeNegocio {

    String getDescricao();

    void setDescricao(String descricao);

    Aluno getPagantes(int index);

    void setPagantes(Aluno aluno);

    Curso getCurso();

    void setCurso(Curso curso);

    Professor getProfessor();

    void setProfessor(Professor professor);

}
