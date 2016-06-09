/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.modelo.periodo.Periodo;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 *
 * @author Edmilson Santana
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DisciplinaService extends GenericService<Disciplina> {

    @EJB
    private PeriodoService periodoService;

    @Override
    public Disciplina getEntidadeNegocio() {
        return new Disciplina();
    }

    @Override
    public Class<Disciplina> getClasseEntidade() {
        return Disciplina.class;
    }

    public Disciplina salvarDisciplinaComPeriodoAtual(Disciplina entidadeNegocio) {
        Periodo periodo = periodoService.obterPeriodoAtual();
        entidadeNegocio.setPeriodo(periodo);
        return super.salvar(entidadeNegocio);
    }
    
    public Disciplina salvarDisciplinaComPeriodoAntigo(Disciplina entidadeNegocio,String ano, String semestre){
        Periodo periodo = periodoService.criarPeriodoAnterior(ano, semestre);
        entidadeNegocio.setPeriodo(periodo);
        return super.salvar(entidadeNegocio);
    }
    
    
}
