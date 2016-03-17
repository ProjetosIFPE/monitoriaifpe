package com.softwarecorporativo.monitoriaifpe.instituto.professor;

import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;

import com.softwarecorporativo.monitoriaifpe.usuario.Usuario;

public interface Professor extends Usuario {

    Disciplina getDisciplina(int index);

    void setDisciplina(Disciplina disciplina);

    int getQuantidadeDisciplinasDoProfessor();

}
