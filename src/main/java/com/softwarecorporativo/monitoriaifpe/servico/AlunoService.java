/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 *
 * @author Edmilson Santana
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AlunoService extends GenericService<Aluno> {

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public Class<Aluno> getClasseEntidade() {
        return Aluno.class;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public Aluno getEntidadeNegocio() {
        return new Aluno();
    }

}
