/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Semestre;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Edmilson Santana
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PeriodoService extends GenericService<Periodo> {

    @Override
    public Periodo getEntidadeNegocio() {
        return new Periodo();
    }

    @Override
    public Class<Periodo> getClasseEntidade() {
        return Periodo.class;
    }

    private Periodo criarPeriodoAtual() {

        Periodo periodo = getEntidadeNegocio();

        Calendar data = Calendar.getInstance();

        int ano = data.get(Calendar.YEAR);
        periodo.setAno(ano);

        Calendar inicioSegundoSemestre = Calendar.getInstance();
        inicioSegundoSemestre.set(ano, 7, 1);

        if (data.after(inicioSegundoSemestre)) {
            periodo.setSemestre(Semestre.SEGUNDO);
        } else {
            periodo.setSemestre(Semestre.PRIMEIRO);
        }

        return periodo;
    }

    /**
     * Obtém um Periodo gerado a partir da data atual.
     *
     * @return  *
     */
    public Periodo obterPeriodoAtual() {
        Periodo periodo = this.criarPeriodoAtual();
        try {
            periodo = this.obterPeriodoCadastradoPorAnoEsemestre(periodo);
        } catch (NoResultException e) {
            super.salvar(periodo);
        }
        return periodo;

    }

    public Periodo obterPeriodoCadastradoPorAnoEsemestre(Periodo periodo) {

        StringBuilder jpql = new StringBuilder();
        jpql.append(" select p from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as p ");
        jpql.append(" where p.ano = :paramAno ");
        jpql.append(" and p.semestre = :paramSemestre ");
        TypedQuery<Periodo> query = entityManager.createQuery(jpql.toString(), getClasseEntidade());
        query.setParameter("paramAno", periodo.getAno());
        query.setParameter("paramSemestre", periodo.getSemestre());
        return query.getSingleResult();
    }
    
    public Periodo criarPeriodoAnterior(String ano, String semestre){
        Periodo periodo = getEntidadeNegocio();
        periodo.setAno(Integer.parseInt(ano));
        if(semestre.equals("1")){
            periodo.setSemestre(Semestre.PRIMEIRO);
        }else{
            periodo.setSemestre(Semestre.SEGUNDO);
        }
        
        return periodo;
    }

}
