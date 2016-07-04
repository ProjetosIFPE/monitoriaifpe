/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author EdmilsonS
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CursoService extends GenericService<Curso> {

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Curso getEntidadeNegocio() {
        return new Curso();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Class<Curso> getClasseEntidade() {
        return Curso.class;
    }

    
    
    public void removerCurso(Curso entidadeNegocio) throws NegocioException {
      
        if (entidadeNegocio.isInativo() == Boolean.FALSE) {
            throw new NegocioException(NegocioException.CURSO_ASSOCIADO_A_USUARIO);
        } else {
            super.remover(entidadeNegocio);
        }
    }

    

}
