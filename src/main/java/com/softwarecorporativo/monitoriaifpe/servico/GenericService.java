/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author Edmilson Santana
 * @param <T>
 */
public abstract class GenericService<T extends EntidadeNegocio> {

    @PersistenceContext(unitName = "monitoriaifpe-unit-dev", type = PersistenceContextType.TRANSACTION)
    EntityManager entityManager;

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public T buscarEntidade(Long chavePrimaria) {
        return entityManager.find(getClasseEntidade(), chavePrimaria);
    }

    public T salvar(T entidadeNegocio) throws NegocioException {
        this.validarCadastro(entidadeNegocio);
        this.entityManager.persist(entidadeNegocio);
        return entidadeNegocio;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> listarTodos() {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select entidadeNegocio from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as entidadeNegocio ");
        Query query = entityManager.createQuery(jpql.toString(), getClasseEntidade());
        return query.getResultList();
    }

    public void remover(T entidadeNegocio) throws NegocioException {

        entidadeNegocio = this.entityManager.find(this.getClasseEntidade(), entidadeNegocio.getChavePrimaria());
        this.entityManager.remove(entidadeNegocio);
    }

    public void atualizar(T entidadeNegocio) throws NegocioException {
        this.entityManager.merge(entidadeNegocio);
    }

    public void validarCadastro(T entidadeNegocio) throws NegocioException {
        T entidade;
        try {
            entidade = verificarExistencia(entidadeNegocio);
            if (entidade != null) {
                throw new NegocioException(NegocioException.OBJETO_EXISTENTE);
            }
        } catch (NonUniqueResultException e) {
            throw new NegocioException(NegocioException.OBJETO_EXISTENTE);
        } catch (NoResultException e) {
        
        }
    }

    public abstract T verificarExistencia(T entidadeNegocio);

    public abstract T getEntidadeNegocio();

    public abstract Class<T> getClasseEntidade();

}
