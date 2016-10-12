/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.ComponenteCurricular;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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

    @Override
    public Boolean verificarExistencia(ComponenteCurricular entidadeNegocio) {
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select componente from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as componente ");
        jpql.append(" where componente.curso = ?1 and ");
        jpql.append(" ( componente.descricao = ?2 ");
        jpql.append(" or componente.codigoComponenteCurricular = ?3 ) ");
        TypedQuery<Long> query = super.entityManager
                .createQuery(jpql.toString(), Long.class);
        query.setParameter(1, entidadeNegocio.getCurso());
        query.setParameter(2, entidadeNegocio.getDescricao());
        query.setParameter(3, entidadeNegocio.getCodigoComponenteCurricular());
        Long count = query.getSingleResult();
        return count > 0;
    }

}
