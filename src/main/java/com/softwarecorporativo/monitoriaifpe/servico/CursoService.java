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
import javax.persistence.TypedQuery;

/**
 *
 * @author Edmilson Santana
 */

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CursoService extends GenericService<Curso> {

    

   
    @Override
    public void remover(Curso entidadeNegocio) throws NegocioException{

        if (!entidadeNegocio.isInativo()) {
            throw new NegocioException(NegocioException.CURSO_ASSOCIADO_A_USUARIO);
        } else {
            super.remover(entidadeNegocio);
        }
    }

    @Override
    public Boolean verificarExistencia(Curso entidadeNegocio) {
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select curso ");
        jpql.append(" from ");
        jpql.append(this.getClasseEntidade().getSimpleName());
        jpql.append(" as curso ");
        jpql.append(" where curso.descricao = ?1 or curso.codigoCurso = ?2  ");

        TypedQuery<Long> query = entityManager.createQuery(jpql.toString(),
                Long.class);
        query.setParameter(1, entidadeNegocio.getDescricao());
        query.setParameter(2, entidadeNegocio.getCodigoCurso());
        Long count = query.getSingleResult();
        return count > 0;
    }
    
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

}
