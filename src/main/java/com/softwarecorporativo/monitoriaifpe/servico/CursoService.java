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

    
    @Override
    public void remover(Curso entidadeNegocio) throws NegocioException {
        Long contadorUsoDoCurso = 0l;
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT COUNT(a,p) FROM ");
        jpql.append("Aluno a, ComponenteCurricular p ");
        jpql.append("WHERE a.curso or p.curso.chavePrimaria = ");
        jpql.append(entidadeNegocio.getChavePrimaria());
        String q1 = "SELECT COUNT(a) FROM " + "Aluno a WHERE a.curso.chavePrimaria = " + entidadeNegocio.getChavePrimaria();   
        String q2 = "SELECT COUNT(c) FROM " + "ComponenteCurricular c WHERE c.curso.chavePrimaria = " + entidadeNegocio.getChavePrimaria();   
        Query query = entityManager.createQuery(q1);
        contadorUsoDoCurso += (long) query.getSingleResult();
        query = entityManager.createQuery(q2);
        contadorUsoDoCurso += (long) query.getSingleResult();
        
        
        if (contadorUsoDoCurso > 0l) {
            throw new NegocioException(NegocioException.CURSO_ASSOCIADO_A_USUARIO);
        } else {
            this.entityManager.remove(entidadeNegocio);
        }
    }

}
