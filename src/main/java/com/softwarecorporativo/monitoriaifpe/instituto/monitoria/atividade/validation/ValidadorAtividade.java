/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.instituto.monitoria.atividade.validation;

import com.softwarecorporativo.monitoriaifpe.instituto.monitoria.atividade.Atividade;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author EdmilsonS
 */
public class ValidadorAtividade implements ConstraintValidator<ValidaHorarioAtividade, Atividade> {

    @Override
    public void initialize(ValidaHorarioAtividade validaAtividade) {

    }

    @Override
    public boolean isValid(Atividade atividade, ConstraintValidatorContext cvc) {
        if (atividade != null && atividade.getHorarioEntrada() != null && atividade.getHorarioSaida() != null) {
            return atividade.getHorarioEntrada().before(atividade.getHorarioSaida());
        }
        return Boolean.FALSE;
    }

}
