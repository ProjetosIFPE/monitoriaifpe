/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.instituto.aluno;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Douglas Albuquerque
 */
public class ValidadadorDeMatricula implements ConstraintValidator<ValidaMatricula, String> {

    private List<String> listaCodigosDoCurso;

    @Override
    public void initialize(ValidaMatricula annotation) {
        this.listaCodigosDoCurso = new ArrayList<String>();
        this.listaCodigosDoCurso.add("Y6");
        this.listaCodigosDoCurso.add("T4");
        this.listaCodigosDoCurso.add("C2");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        String anoFornecido = value.substring(0, 4);
        int anoForn = Integer.parseInt(anoFornecido);
        String codigoDoCurso = value.substring(4, 6);
        String codigoCampus = value.substring(7, 9);

        return anoForn > 1910 && codigoCampus.equals("RC") && this.listaCodigosDoCurso.contains(codigoDoCurso);

    }

}
