/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.Turma;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Edmilson Santana
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TurmaService extends GenericService<Turma> {

    @Inject
    public PeriodoService periodoService;

    @Override
    public Boolean verificarExistencia(Turma turma) {
        Object[] parametros = new Object[2];
        parametros[0] = turma.getPeriodo();
        parametros[1] = turma.getComponenteCurricular();
        return super.count(Turma.COUNT_TURMA_CADASTRADA, parametros) > 0;
    }

    public List<Turma> consultarTurmasOfertadas(Professor professor) {
        Object[] parametros = new Object[2];
        parametros[0] = Boolean.TRUE;
        parametros[1] = professor;
        return super.getResultList(Turma.TURMAS_OFERTADAS_POR_PROFESSOR, parametros);
    }

    public List<Turma> consultarTurmasOfertadas(Curso curso) {
        Object[] parametros = new Object[2];
        parametros[0] = Boolean.TRUE;
        parametros[1] = curso;
        return super.getResultList(Turma.TURMAS_OFERTADAS_POR_CURSO, parametros);
    }

    public void removerOferta(Turma turma) throws NegocioException {

        turma.removerOferta();
        super.atualizar(turma);
    }

    @Override
    public Turma salvar(Turma turma) throws NegocioException {
        Periodo periodo = periodoService.obterPeriodoAtual();
        turma.setPeriodo(periodo);
        turma.ofertar();
        return super.salvar(turma);
    }

    @Override
    public Turma getEntidadeNegocio() {
        return new Turma();
    }

    @Override
    public Class<Turma> getClasseEntidade() {
        return Turma.class;
    }

}
