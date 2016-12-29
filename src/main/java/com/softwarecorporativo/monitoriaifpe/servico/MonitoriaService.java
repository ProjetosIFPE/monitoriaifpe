/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.SituacaoMonitoria;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Edmilson Santana
 */
@Stateless
public class MonitoriaService extends GenericService<Monitoria> {

    @Override
    public Monitoria getEntidadeNegocio() {
        return new Monitoria();
    }

    @Override
    public Class<Monitoria> getClasseEntidade() {
        return Monitoria.class;
    }

    @Override
    public Monitoria salvar(Monitoria monitoria) throws NegocioException {
        monitoria.aguardarAprovacao();
        return super.salvar(monitoria);
    }

    public void aprovar(Monitoria monitoria) throws NegocioException {
        monitoria.aprovar();
        atualizar(monitoria);
    }

    public void reprovar(Monitoria monitoria) throws NegocioException {
        monitoria.reprovar();
        atualizar(monitoria);
    }

    public List<Monitoria> consultarMonitoriasAprovadas(Professor professor) {
        return this.consultarMonitorias(SituacaoMonitoria.APROVADA, professor);
    }

    public List<Monitoria> consultarMonitoriasAguardandoAprovacao(Professor professor) {
        return this.consultarMonitorias(SituacaoMonitoria.AGUARDANDO_APROVACAO, professor);
    }

    public List<Monitoria> consultarMonitoriasAprovadas(Aluno aluno) {
        return this.consultarMonitorias(SituacaoMonitoria.APROVADA, aluno);
    }

    public List<Monitoria> consultarMonitorias(SituacaoMonitoria situacao, Aluno aluno) {
        Object[] parametros = new Object[2];
        parametros[0] = situacao;
        parametros[1] = aluno;
        return getResultList(Monitoria.MONITORIA_POR_ALUNO, parametros);
    }

    public List<Monitoria> consultarMonitorias(SituacaoMonitoria situacao, Professor professor) {
        Object[] parametros = new Object[2];
        parametros[0] = situacao;
        parametros[1] = professor;
        return getResultList(Monitoria.MONITORIA_POR_PROFESSOR, parametros);
    }

    public List<Monitoria> consultarMonitoriasSolicitadas(Aluno aluno) {
        Object[] parametros = new Object[3];
        parametros[0] = SituacaoMonitoria.AGUARDANDO_APROVACAO;
        parametros[1] = SituacaoMonitoria.REPROVADA;
        parametros[2] = aluno;
        return getResultList(Monitoria.MONITORIA_SOLICITADA, parametros);
    }

    @Override
    public Boolean verificarExistencia(Monitoria monitoria) {
        Object[] parametros = new Object[2];
        parametros[0] = monitoria.getAluno();
        parametros[1] = monitoria.getTurma();
        return super.count(Monitoria.COUNT_MONITORIA_CADASTRADA, parametros) > 0;
    }

}
