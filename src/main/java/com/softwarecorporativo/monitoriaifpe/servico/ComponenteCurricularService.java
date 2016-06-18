/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.ComponenteCurricular;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;

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
    
    public List<ComponenteCurricular> obterComponentesPorCurso(Curso curso) {
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select componente from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as componente ");
        jpql.append(" where componente.curso = :paramCurso ");
        Query query = super.entityManager.createQuery(jpql.toString(), getClasseEntidade());
        query.setParameter("paramCurso", curso);
        return query.getResultList();
    }

}
