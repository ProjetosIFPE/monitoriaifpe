/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.relatorio;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.impl.AlunoImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.instituto.monitoria.impl.MonitoriaImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.instituto.periodo.impl.PeriodoImpl;
import com.softwarecorporativo.monitoriaifpe.relatorio.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.relatorio.atividade.impl.AtividadeImpl;
import com.softwarecorporativo.monitoriaifpe.relatorio.frequencia.RelatorioFrequencia;
import com.softwarecorporativo.monitoriaifpe.relatorio.frequencia.impl.RelatorioFrequenciaImpl;
import com.softwarecorporativo.monitoriaifpe.relatorio.semana.Semana;
import com.softwarecorporativo.monitoriaifpe.relatorio.semana.impl.SemanaImpl;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Modalidade;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Situacao;
import java.util.Calendar;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author EdmilsonS
 */
public class TesteRelatorioFrequencia extends MonitoriaTestCase {

    @Override
    public void setUp() {
        super.setUp(); 
        this.prepararCenario();
    }

    
    @Test
    public void testeInserirRelatorioFrequencia() {

        int index = 0;

        RelatorioFrequencia relatorio = montarObjetoRelatorioFrequencia();

        super.entityManager.persist(relatorio);
        entityManager.flush();
        entityManager.detach(relatorio);

        assertTrue(relatorio.getChavePrimaria() > 0);

        RelatorioFrequencia relatorioObtido = super.entityManager
                .find(relatorio.getClass(), relatorio.getChavePrimaria());

        assertEquals(relatorio, relatorioObtido);
        assertEquals(relatorio.getSemana(index), relatorioObtido.getSemana(index));
        assertEquals(relatorio.getSemana(index).getAtividade(index), relatorioObtido.getSemana(index).getAtividade(index));
        assertEquals(relatorio.getMonitoria(), relatorioObtido.getMonitoria());
    }

    @Test
    public void testeRemoverRelatorioFrequencia() {

        RelatorioFrequencia relatorio = montarObjetoRelatorioFrequencia();

        super.entityManager.persist(relatorio);
        entityManager.flush();
        entityManager.detach(relatorio);

        assertTrue(relatorio.getChavePrimaria() > 0);

        RelatorioFrequencia relatorioObtido = super.entityManager
                .find(relatorio.getClass(), relatorio.getChavePrimaria());

        assertNotNull(relatorioObtido);
    }

    @Test
    public void testeAtualizarRelatorioFrequencia() {

        RelatorioFrequencia relatorio = montarObjetoRelatorioFrequencia();

        super.entityManager.persist(relatorio);

        assertTrue(relatorio.getChavePrimaria() > 0);

        relatorio.setSituacao(Situacao.ESPERA);

        int quantidadeSemanasPreAlteracao = relatorio.getSemanas().size();

        relatorio.getSemanas().clear();

        entityManager.flush();
        entityManager.detach(relatorio);

        RelatorioFrequencia relatorioObtido = super.entityManager
                .find(relatorio.getClass(), relatorio.getChavePrimaria());

        int quantidadeSemanasPosAlteracao = relatorioObtido.getSemanas().size();

        assertEquals(quantidadeSemanasPreAlteracao - 1, quantidadeSemanasPosAlteracao);
        assertEquals(Situacao.ESPERA, relatorioObtido.getSituacao());

    }

    private void prepararCenario() {

        super.entityManager.persist(montarObjetoMonitoria());
    }

    private RelatorioFrequencia montarObjetoRelatorioFrequencia() {
        RelatorioFrequencia relatorio = new RelatorioFrequenciaImpl();
        relatorio.setMes(01);
        relatorio.setSituacao(Situacao.APROVADO);
        relatorio.setSemanas(montarObjetoSemana());
        Monitoria monitoria = super.entityManager.find(MonitoriaImpl.class, 1L);
        relatorio.setMonitoria(monitoria);
        return relatorio;
    }

    private Semana montarObjetoSemana() {
        Semana semana = new SemanaImpl();
        semana.setDescricao("Semana de Teste");
        semana.setObservacoes("Observações de Teste");
        semana.setAtividades(montarObjetoAtividade());
        return semana;
    }

    private Atividade montarObjetoAtividade() {
        Atividade atividade = new AtividadeImpl();
        atividade.setData(Calendar.getInstance().getTime());
        atividade.setHorarioEntrada("14:00");
        atividade.setHorarioSaida("18:00");
        return atividade;
    }

    private Monitoria montarObjetoMonitoria() {
        Monitoria monitoria = new MonitoriaImpl();
        Aluno aluno = super.entityManager.find(AlunoImpl.class, 1L);
        monitoria.setAluno(aluno);
        monitoria.setDisciplina(aluno.getDisciplinas(0));
        monitoria.setModalidade(Modalidade.BOLSISTA);
        Periodo periodo = super.entityManager.find(PeriodoImpl.class, 1L);
        monitoria.setPeriodo(periodo);
        return monitoria;
    }

}
