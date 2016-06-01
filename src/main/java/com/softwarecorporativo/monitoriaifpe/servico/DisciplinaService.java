/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.Disciplina;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 *
 * @author EdmilsonS
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DisciplinaService extends GenericService<Disciplina> {

    private static final long serialVersionUID = -98075646630672728L;

    @Override
    public Disciplina getEntidadeNegocio() {
       return new Disciplina();
    }

    @Override
    public Class<Disciplina> getClasseEntidade() {
       return Disciplina.class;
    }
    
  

    
}
