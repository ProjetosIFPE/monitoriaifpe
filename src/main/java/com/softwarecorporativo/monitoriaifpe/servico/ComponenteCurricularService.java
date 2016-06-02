/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.ComponenteCurricular;
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
public class ComponenteCurricularService extends GenericService<ComponenteCurricular> {

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ComponenteCurricular getEntidadeNegocio() {
        return new ComponenteCurricular();
    }

    
    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Class<ComponenteCurricular> getClasseEntidade() {
        return ComponenteCurricular.class;
    }

}
