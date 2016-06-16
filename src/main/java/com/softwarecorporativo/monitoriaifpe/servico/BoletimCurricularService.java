/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.boletim.BoletimCurricular;
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
public class BoletimCurricularService extends GenericService<BoletimCurricular>{

    @Override
    public BoletimCurricular getEntidadeNegocio() {
        return new BoletimCurricular();
    }

    @Override
    public Class<BoletimCurricular> getClasseEntidade() {
        return BoletimCurricular.class;
    }
    
    public List<BoletimCurricular> obterBoletinsCurricularesDoAluno(Aluno aluno) {
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select boletim from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as boletim ");
        jpql.append(" where aluno = :paramAluno ");
        Query query = super.entityManager.createQuery(jpql.toString(), getClasseEntidade());
        return query.getResultList();
    }
    
}
