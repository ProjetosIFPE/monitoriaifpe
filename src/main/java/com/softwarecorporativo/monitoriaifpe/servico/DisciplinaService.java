/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.Turma;
import com.softwarecorporativo.monitoriaifpe.modelo.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import java.util.List;
import javax.ejb.EJB;
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
public class DisciplinaService extends GenericService<Turma> {

    @EJB
    private PeriodoService periodoService;

    @Override
    public Turma getEntidadeNegocio() {
        return new Turma();
    }

    @Override
    public Class<Turma> getClasseEntidade() {
        return Turma.class;
    }

    public Turma salvarDisciplinaComPeriodoAtual(Turma disciplina) throws NegocioException {
        Periodo periodo = periodoService.obterPeriodoAtual();
        disciplina.setPeriodo(periodo);
        return super.salvar(disciplina);

    }

    public List<Turma> obterDisciplinasDoCursoPorPeriodo(Curso curso, Periodo periodo) {

        StringBuilder jpql = new StringBuilder();
        jpql.append(" select disciplina from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as disciplina ");
        jpql.append(" join disciplina.componenteCurricular as cc ");
        jpql.append(" where disciplina.periodo = :paramPeriodo ");
        jpql.append(" and cc.curso = :paramCurso ");
        TypedQuery<Turma> query = super.entityManager
                .createQuery(jpql.toString(), getClasseEntidade());
        query.setParameter("paramPeriodo", periodo);
        query.setParameter("paramCurso", curso);

        return query.getResultList();
    }

    public List<Turma> obterDisciplinasPorCursoDoPeriodoAtual(Curso curso) throws NegocioException {
        Periodo periodo = periodoService.obterPeriodoAtual();
        return this.obterDisciplinasDoCursoPorPeriodo(curso, periodo);
    }

    public List<Turma> obterDisciplinasPorCursoDePeriodoNaoAtual(Curso curso) throws NegocioException {
        Periodo periodo = periodoService.obterPeriodoAtual();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select disciplina from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as disciplina ");
        jpql.append(" join disciplina.componenteCurricular as cc ");
        jpql.append(" where disciplina.periodo != :paramPeriodo ");
        jpql.append(" and cc.curso = :paramCurso ");
        TypedQuery<Turma> query = super.entityManager
                .createQuery(jpql.toString(), getClasseEntidade());
        query.setParameter("paramPeriodo", periodo);
        query.setParameter("paramCurso", curso);

        return query.getResultList();
    }

    public Turma salvarDisciplinaComPeriodoAntigo(Turma entidadeNegocio, String ano, String semestre) throws NegocioException {
        Periodo periodo = periodoService.criarPeriodoAnterior(ano, semestre);
        entidadeNegocio.setPeriodo(periodo);
        return super.salvar(entidadeNegocio);
    }

    /*TODO: Refatorar para a entidade de Professor*/
    public List<Turma> obterDisciplinasDoProfessor(Professor professor) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select d from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as d ");
        jpql.append(" where d.professor = :paramProfessor ");
        Query query = super.entityManager.createQuery(jpql.toString(), getClasseEntidade());
        query.setParameter("paramProfessor", professor);
        return query.getResultList();
    }

    @Override
    public Boolean verificarExistencia(Turma entidadeNegocio) {
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select disciplina from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as disciplina ");
        jpql.append(" where disciplina.componenteCurricular = ?1 ");
        jpql.append(" and disciplina.periodo = ?2 ");
        TypedQuery<Long> query = super.entityManager
                .createQuery(jpql.toString(), Long.class);
        query.setParameter(1, entidadeNegocio.getComponenteCurricular());
        query.setParameter(2, entidadeNegocio.getPeriodo());
        Long count = query.getSingleResult();
        return count > 0;
    }

}
