/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author Edmilson Santana
 * @param <T>
 */
public abstract class GenericService<T extends EntidadeNegocio> {

    @PersistenceContext(unitName = "monitoriaifpe-unit-dev", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    public T buscarEntidade(Long chavePrimaria) {
        return entityManager.find(getClasseEntidade(), chavePrimaria);
    }

    abstract T getEntidadeNegocio();

    abstract Class<T> getClasseEntidade();
}
