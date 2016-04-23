/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.instituto.monitoria.atividade;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.instituto.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.instituto.monitoria.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.util.Util;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Modalidade;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Situacao;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author EdmilsonS
 */
public class TesteAtividade extends MonitoriaTestCase {

    @Test
    public void testeInserirAtividade() {

        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());
        Atividade atividade = montarObjetoAtividade();
        super.entityManager.persist(atividade);
        super.entityManager.flush();
        super.entityManager.refresh(atividade);
        assertNotNull(atividade.getChavePrimaria());
    }

    @Test
    public void testeRemoverAtividade() {
        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());
        Atividade atividade = super.entityManager.find(Atividade.class, 1L);
        super.entityManager.remove(atividade);
        super.entityManager.flush();
        assertNull(super.entityManager.find(Atividade.class, 1L));
    }

    @Test
    public void testeAlterarAtividade() {
        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());
        Atividade atividade = super.entityManager.find(Atividade.class, 1L);
        atividade.setSituacao(Situacao.ESPERA);
        atividade.setDescricao("Descrição Alterada");
        super.entityManager.merge(atividade);
        super.entityManager.flush();
        super.entityManager.clear();
        atividade = super.entityManager.find(Atividade.class, 1L);
        assertEquals(Situacao.ESPERA, atividade.getSituacao());
    }

    @Test
    public void testeConsultarAtividadesAprovadas() {

        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());

        TypedQuery<Atividade> query = super.entityManager.createQuery(
                "SELECT a FROM Atividade a WHERE a.situacao = :situacao ORDER BY a.descricao",
                Atividade.class);
        query.setParameter("situacao", Situacao.APROVADO);

        List<Atividade> atividadesAprovadas = query.getResultList();
        assertEquals(20, atividadesAprovadas.size());
        for (Atividade atividade : atividadesAprovadas) {
            atividade.getSituacao().equals(Situacao.APROVADO);
        }
    }

    @Test
    public void testeConsultarAtividadesEmEspera() {

        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());

        TypedQuery<Atividade> query = super.entityManager.createQuery(
                "SELECT a FROM Atividade a WHERE a.situacao = :situacao ORDER BY a.descricao ASC",
                Atividade.class);
        query.setParameter("situacao", Situacao.ESPERA);

        List<Atividade> atividadesEmEspera = query.getResultList();
        assertEquals(26, atividadesEmEspera.size());
        for (Atividade atividade : atividadesEmEspera) {
            atividade.getSituacao().equals(Situacao.ESPERA);
        }
    }

    @Test
    public void testeConsultarAtividadesPorDescricao() {

        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());

        TypedQuery<Atividade> query = super.entityManager.createQuery(
                "SELECT a FROM Atividade a WHERE a.descricao LIKE :descricao ORDER BY a.descricao DESC",
                Atividade.class);
        query.setParameter("descricao", "Correção%");

        List<Atividade> atividades = query.getResultList();
        assertEquals(26, atividades.size());
        for (Atividade atividade : atividades) {
            assertTrue(atividade.getDescricao().startsWith("Correção"));
        }
    }

    @Test
    public void testeConsultarAtividadesDoMes() {

        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());

        TypedQuery<Atividade> query = super.entityManager.createQuery(
                "SELECT a FROM Atividade a WHERE a.data BETWEEN ?1 AND ?2 ORDER BY a.data",
                Atividade.class);

        query.setParameter(1, Util.getData(1, Calendar.JANUARY, 2016));
        query.setParameter(2, Util.getData(31, Calendar.JANUARY, 2016));

        List<Atividade> atividades = query.getResultList();
        assertEquals(23, atividades.size());
        Calendar calendar = Calendar.getInstance();
        for (Atividade atividade : atividades) {
            calendar.setTime(atividade.getData());
            assertEquals(Calendar.JANUARY, calendar.get(Calendar.MONTH));
        }
    }

    @Test
    public void testeConsultarAtividadesSemObservacoes() {

        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());

        TypedQuery<Atividade> query = super.entityManager.createQuery(
                "SELECT a FROM Atividade a WHERE a.observacoes IS NULL",
                Atividade.class);

        List<Atividade> atividades = query.getResultList();
        assertEquals(46, atividades.size());
        for (Atividade atividade : atividades) {
            assertNull(atividade.getObservacoes());
        }
    }

    @Test
    public void testeVerificarAtividadeNaMonitoria() {

        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());

        TypedQuery<Atividade> queryAtividade = super.entityManager.createQuery(
                "SELECT a FROM Atividade a INNER JOIN FETCH a.monitoria WHERE a.chavePrimaria = ?1",
                Atividade.class);
        queryAtividade.setParameter(1, 1L);

        Atividade atividade = queryAtividade.getSingleResult();

        TypedQuery<Monitoria> queryMonitoria = super.entityManager.createQuery(
                "SELECT m FROM Monitoria m WHERE :item MEMBER OF m.atividades",
                Monitoria.class);
        queryMonitoria.setParameter("item", atividade);

        Monitoria monitoria = queryMonitoria.getSingleResult();
        assertEquals(atividade.getMonitoria(), monitoria);
    }

    @Test
    public void testeConsultarAtividadesPassadas() {

        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());

        TypedQuery<Atividade> query = super.entityManager.createQuery(
                "SELECT a FROM Atividade a WHERE a.data < current_date() ORDER BY a.descricao",
                Atividade.class);

        Date dataAtual = Calendar.getInstance().getTime();
        List<Atividade> atividades = query.getResultList();
        assertEquals(46, atividades.size());
        for (Atividade atividade : atividades) {
            assertTrue(atividade.getData().before(dataAtual));
        }

    }

    @Test
    public void testeConsultarAtividadePorMonitoriaBolsista() {

        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());

        TypedQuery<Atividade> query = super.entityManager.createQuery(
                "SELECT a FROM Atividade a WHERE a.monitoria IN ( SELECT m FROM Monitoria m WHERE m.modalidade = 'BOLSISTA')",
                Atividade.class);

        List<Atividade> atividades = query.getResultList();
        assertEquals(23, atividades.size());
        for (Atividade atividade : atividades) {
            assertEquals(Modalidade.BOLSISTA, atividade.getMonitoria().getModalidade());
        }
    }

    @Test
    public void testeVerificarQuantidadeAtividades() {

        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());
        Long quantidadeEsperada = 46L;
        TypedQuery<Long> query = super.entityManager.createQuery(
                "SELECT COUNT(a) FROM Atividade a WHERE EXISTS ( SELECT m FROM Monitoria m)",
                Long.class);
        Long quantidade = query.getSingleResult();
        assertEquals(quantidadeEsperada, quantidade);
    }

    @Test
    public void testeConsultarAtividadeMaisAntiga() {

        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());
        Atividade atividadeEsperada = super.entityManager.find(Atividade.class, 1L);
        TypedQuery<Atividade> query = super.entityManager.createQuery(
                "SELECT a FROM Atividade a WHERE NOT(a.data > ALL ( SELECT aa.data FROM Atividade aa))",
                Atividade.class);

        Atividade atividade = query.getSingleResult();
        assertEquals(atividadeEsperada, atividade);
    }

    @Test
    public void testeConsultarAtividadesPorAluno() {
        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());
        Aluno alunoEsperado = super.entityManager.find(Aluno.class, 1L);
        TypedQuery<Atividade> query = super.entityManager.createQuery(
                "SELECT a FROM Atividade a INNER JOIN FETCH a.monitoria m INNER JOIN FETCH m.aluno al where CONCAT(al.nome, al.sobrenome) = :nomeAluno",
                Atividade.class);
        String nomeAluno = alunoEsperado.getNome().concat(alunoEsperado.getSobrenome());
        query.setParameter("nomeAluno", nomeAluno);
        List<Atividade> atividades = query.getResultList();
        assertEquals(23, atividades.size());
        for (Atividade atividade : atividades) {
            assertEquals(alunoEsperado, atividade.getMonitoria().getAluno());
        }
    }

    @Test
    public void testeAprovarAtividadesDeMonitoria() {
        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());
        Query query = super.entityManager.createQuery("UPDATE Atividade AS a SET a.situacao = ?1 WHERE a.situacao = ?2");
        query.setParameter(1, Situacao.APROVADO);
        query.setParameter(2, Situacao.ESPERA);
        query.executeUpdate();
        TypedQuery<Long> typedQuery = super.entityManager.createQuery("SELECT COUNT(a) FROM Atividade a where a.situacao = :situacao", Long.class);
        typedQuery.setParameter("situacao", Situacao.ESPERA);
        Long quantidadeEsperada = 0L;
        Long quantidade = typedQuery.getSingleResult();
        assertEquals(quantidadeEsperada, quantidade);
    }

    @Test
    public void testeRemoverAtividadesDeMonitoria() {
        LOGGER.log(Level.INFO, "Iniciando Teste - {0}", name.getMethodName());
        Monitoria monitoria = super.entityManager.find(Monitoria.class, 2L);
        Query query = super.entityManager.createQuery("DELETE Atividade AS a WHERE a.monitoria = ?1");
        query.setParameter(1, monitoria);
        query.executeUpdate();
        TypedQuery<Long> typedQuery = super.entityManager.createQuery("SELECT COUNT(a) FROM Atividade a where a.monitoria = :monitoria", Long.class);
        typedQuery.setParameter("monitoria", monitoria);
        Long quantidadeEsperada = 0L;
        Long quantidade = typedQuery.getSingleResult();
        assertEquals(quantidadeEsperada, quantidade);
    }

    private Atividade montarObjetoAtividade() {
        Atividade atividade = new Atividade();
        Date timestamp = Calendar.getInstance().getTime();
        atividade.setData(timestamp);
        atividade.setDescricao("Descrição da Atividade");
        atividade.setHorarioEntrada(timestamp);
        atividade.setObservacoes("Observação da Atividade");
        atividade.setSituacao(Situacao.APROVADO);
        atividade.setMonitoria(super.entityManager.find(Monitoria.class, 1L));
        atividade.setData(timestamp);
        return atividade;
    }

}
