/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.periodo.Periodo;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.NoResultException;
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

    /**
     * Obtém um Periodo gerado a partir da data atual.
     *
     * @throws NegocioException
     * @return *
     */
    public Periodo obterPeriodo() throws NegocioException {
        Periodo periodo = getEntidadeNegocio();
        try {
            periodo = this.obterPeriodoCadastradoPorAnoEsemestre(periodo);
        } catch (NoResultException e) {
            super.salvar(periodo);
        }
        return periodo;
    }

    public Periodo obterPeriodoCadastradoPorAnoEsemestre(Periodo periodo) {

        Object[] parametros = new Object[2];
        parametros[0] = periodo.getAno();
        parametros[1] = periodo.getSemestre();
        return super.get(Periodo.PERIODO_POR_ANO_SEMESTRE, parametros);
    }

    public Boolean existePeriodoCadastradoPorAnoEsemestre(Periodo periodo) {

        Object[] parametros = new Object[3];
        parametros[0] = periodo.getAno();
        parametros[1] = periodo.getSemestre();
        parametros[2] = periodo.getChavePrimaria();
        return super.count(Periodo.PERIODO_CADASTRADO, parametros) > 0;
    }

    @Override
    public Boolean verificarExistencia(Periodo entidadeNegocio) {
        return existePeriodoCadastradoPorAnoEsemestre(entidadeNegocio);
    }

}
