/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.monitoria.validation;

import com.softwarecorporativo.monitoriaifpe.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.monitoria.Monitoria;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Edmilson Santana
 */
public class ValidadorMonitoria implements ConstraintValidator<ValidaMonitoria, Monitoria> {

    @Override
    public boolean isValid(Monitoria monitoria, ConstraintValidatorContext cvc) {

        Disciplina disciplina = monitoria.getDisciplina();
        Aluno aluno = monitoria.getAluno();
        Boolean valido = Boolean.FALSE;
        if (disciplina != null && aluno != null) {
            valido = aluno.validarMonitoriaDoAluno(disciplina);
        }
        return valido;
    }

    @Override
    public void initialize(ValidaMonitoria a) {

    }

}
