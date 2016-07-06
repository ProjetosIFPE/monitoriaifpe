/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.grupo.Grupo;
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
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class GrupoService extends GenericService<Grupo> {

    @Override
    public Grupo getEntidadeNegocio() {
        return new Grupo();
    }

    @Override
    public Class<Grupo> getClasseEntidade() {
        return Grupo.class;
    }

    public Grupo obterGrupo(String nomeGrupo) {
        TypedQuery<Grupo> query = super.entityManager
                .createNamedQuery(Grupo.GRUPO_POR_NOME, getClasseEntidade());
        query.setParameter(1, nomeGrupo);
        return query.getSingleResult();
    }

    @Override
    public Grupo verificarExistencia(Grupo entidadeNegocio) {
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select grupo ");
        jpql.append(" from ");
        jpql.append(this.getClasseEntidade().getSimpleName());
        jpql.append(" as grupo ");
        jpql.append(" where grupo.nome = ?1 ");

        TypedQuery<Grupo> query = entityManager.createQuery(jpql.toString(),
                getClasseEntidade());
        query.setParameter(1, entidadeNegocio.getNome());
        return query.getSingleResult();
    }

}
