/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.Turma;
import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Edmilson Santana
 */
@Stateless
public class MonitoriaService extends GenericService<Monitoria> {

    public List<Monitoria> obterMonitoriasPorDisciplina(Turma disciplina) {
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select monitoria from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as monitoria ");
        jpql.append(" where monitoria.disciplina = :paramDisciplina");
        Query query = super.entityManager
                .createQuery(jpql.toString(), getClasseEntidade());
        query.setParameter("paramDisciplina", disciplina);
        return query.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Monitoria> obterMonitoriasPorAluno(Aluno aluno) {
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select monitoria from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as monitoria ");
        jpql.append(" where monitoria.aluno.chavePrimaria = ?1");
        Query query = super.entityManager
                .createQuery(jpql.toString(), getClasseEntidade());
        query.setParameter(1, aluno.getChavePrimaria());
        return query.getResultList();
    }

    @Override
    public Boolean verificarExistencia(Monitoria entidadeNegocio) {
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select count(*) from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as monitoria ");
        jpql.append(" where monitoria.disciplina = ?1 ");
        jpql.append(" and monitoria.aluno = ?2 ");
        TypedQuery<Long> query = super.entityManager
                .createQuery(jpql.toString(), Long.class);
        query.setParameter(1, entidadeNegocio.getTurma());
        query.setParameter(2, entidadeNegocio.getAluno());
        Long count = query.getSingleResult();
        return count > 0;
    }

    @Override
    public Monitoria getEntidadeNegocio() {
        return new Monitoria();
    }

    @Override
    public Class<Monitoria> getClasseEntidade() {
        return Monitoria.class;
    }

}
