/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.modelo.atividade.validation;

import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang.time.DateUtils;

/**
 *
 * @author EdmilsonS
 */
public class ValidadorAtividade implements ConstraintValidator<ValidaAtividade, Atividade> {

    @Override
    public void initialize(ValidaAtividade validaAtividade) {

    }

    @Override
    public boolean isValid(Atividade atividade, ConstraintValidatorContext cvc) {
        Date dataInicio = atividade.getDataInicio();
        Date dataFim = atividade.getDataFim();
        if (dataInicio != null && dataFim != null) {
            return (DateUtils.isSameDay(dataInicio, dataInicio) && dataInicio.before(dataFim));
        }
        return Boolean.FALSE;
    }

}
