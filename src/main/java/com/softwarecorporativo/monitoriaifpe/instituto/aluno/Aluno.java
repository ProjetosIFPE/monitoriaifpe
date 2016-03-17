package com.softwarecorporativo.monitoriaifpe.instituto.aluno;

import com.softwarecorporativo.monitoriaifpe.instituto.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.usuario.Usuario;

public interface Aluno extends Usuario {

    String getMatricula();

    void setMatricula(String matricula);

    Curso getCurso();

    void setCurso(Curso curso);

    Disciplina getDisciplinas(int index);

    void setDisciplinas(Disciplina disciplina);

    int quantidadeDisciplinasCursadas();

}
