/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.aluno.validation;

import com.softwarecorporativo.monitoriaifpe.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.curso.Curso;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Douglas Albuquerque
 */
public class ValidadorDeMatricula implements ConstraintValidator<ValidaMatricula, Aluno> {

    @Override
    public boolean isValid(Aluno aluno, ConstraintValidatorContext context) {

        String matricula = aluno.getMatricula();
        Curso curso = aluno.getCurso();
        String codigoCursoAluno = curso.getCodigoCurso();
        String codigoCampusAluno = curso.getCodigoCampus();
        Boolean matriculaValida = Boolean.FALSE;
        if (!matricula.isEmpty()) {
            int anoMatricula = Integer.parseInt(matricula.substring(0, 4));
            String codigoCursoMatricula = matricula.substring(5, 7);
            String codigoCampusMatricula = matricula.substring(8, 10);
            if (anoMatricula > 1910 && codigoCursoMatricula.equals(codigoCursoAluno)
                    && codigoCampusMatricula.equals(codigoCampusAluno)) {
                matriculaValida = Boolean.TRUE;
            }
        }
        return matriculaValida;

    }

    @Override
    public void initialize(ValidaMatricula a) {

    }

}
