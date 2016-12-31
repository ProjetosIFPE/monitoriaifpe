/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.atividade;

import com.softwarecorporativo.monitoriaifpe.funcionais.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.modelo.util.DataUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import org.apache.commons.lang.RandomStringUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.Ignore;

/**
 *
 * @author Edmilson Santana
 */
public class TesteAtividade extends MonitoriaTestCase {

    @Test
    public void testeInserirAtividade() {
        Atividade atividade = montarObjetoAtividade();
        super.entityManager.persist(atividade);
        super.entityManager.flush();
        super.entityManager.refresh(atividade);
        assertNotNull(atividade.getChavePrimaria());
    }

    @Test
    public void testeRemoverAtividade() {
        Atividade atividade = super.entityManager.find(Atividade.class, 1L);
        super.entityManager.remove(atividade);
        super.entityManager.flush();
        assertNull(super.entityManager.find(Atividade.class, 1L));
    }

    @Test
    public void testeConsultarAtividadesPorDescricao() {
        TypedQuery<Atividade> query = super.entityManager.createQuery(
                "SELECT a FROM Atividade a WHERE a.descricao LIKE :descricao ORDER BY a.descricao DESC",
                Atividade.class);
        query.setParameter("descricao", "Correção%");

        List<Atividade> atividades = query.getResultList();
        assertEquals(25, atividades.size());
        for (Atividade atividade : atividades) {
            assertTrue(atividade.getDescricao().startsWith("Correção"));
        }
    }

    @Test
    public void testeConsultarAtividadesSemObservacoes() {

        TypedQuery<Atividade> query = super.entityManager.createQuery(
                "SELECT a FROM Atividade a WHERE a.observacoes IS NULL",
                Atividade.class);

        List<Atividade> atividades = query.getResultList();
        assertEquals(45, atividades.size());
        for (Atividade atividade : atividades) {
            assertNull(atividade.getObservacoes());
        }
    }

    @Test
    public void testeVerificarAtividadeNaMonitoria() {
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
    public void testeVerificarQuantidadeAtividades() {
        Long quantidadeEsperada = 45L;
        TypedQuery<Long> query = super.entityManager.createQuery(
                "SELECT COUNT(a) FROM Atividade a WHERE EXISTS ( SELECT m FROM Monitoria m)",
                Long.class);
        Long quantidade = query.getSingleResult();
        assertEquals(quantidadeEsperada, quantidade);
    }

    @Test
    public void testeRemoverAtividadesDeMonitoria() {
        Monitoria monitoria = super.entityManager.find(Monitoria.class, 2L);
        Query query = super.entityManager.createQuery("DELETE FROM Atividade AS a WHERE a.monitoria = ?1");
        query.setParameter(1, monitoria);
        query.executeUpdate();
        TypedQuery<Long> typedQuery = super.entityManager.createQuery("SELECT COUNT(a) FROM Atividade a where a.monitoria = :monitoria", Long.class);
        typedQuery.setParameter("monitoria", monitoria);
        Long quantidadeEsperada = 0L;
        Long quantidade = typedQuery.getSingleResult();
        assertEquals(quantidadeEsperada, quantidade);
    }

    @Test
    public void testeCriarAtividadeComDescricaoTamanhoExcedente() {
        String mensagemEsperada = "tamanho deve estar entre 0 e 140";
        Atividade atividade = montarObjetoAtividade();
        String descricao = RandomStringUtils.random(145);
        atividade.setDescricao(descricao);
        Set<ConstraintViolation<Atividade>> constraintViolations = validator.validate(atividade);
        assertEquals(1, constraintViolations.size());
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(mensagemEsperada, mensagemObtida);
    }

    @Test
    public void testeCriarAtividadeComDescricaoVazia() {
        String mensagemEsperada = "Não pode estar em branco";
        Atividade atividade = montarObjetoAtividade();
        atividade.setDescricao("");
        Set<ConstraintViolation<Atividade>> constraintViolations = validator.validate(atividade);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }

    @Test
    public void testeCriarAtividadeComObservacaoTamanhoExcedente() {
        String mensagemEsperada = "tamanho deve estar entre 0 e 140";
        Atividade atividade = montarObjetoAtividade();
        String descricao = RandomStringUtils.random(145);
        atividade.setDescricao(descricao);
        Set<ConstraintViolation<Atividade>> constraintViolations = validator.validate(atividade);
        assertEquals(1, constraintViolations.size());
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(mensagemEsperada, mensagemObtida);
    }

    @Test
    public void testeCriarAtividadeValida() {
        Atividade atividade = montarObjetoAtividade();
        Set<ConstraintViolation<Atividade>> constraintViolations = validator.validate(atividade);
        assertEquals(0, constraintViolations.size());
    }

    private Atividade montarObjetoAtividade() {
        Atividade atividade = new Atividade();
        atividade.setDescricao("Descrição da Atividade");
        atividade.setDataInicio(DataUtil.getTime(14, 0, 0));
        atividade.setDataFim(DataUtil.getTime(15, 0, 0));
        atividade.setObservacoes("Observação da Atividade");
        atividade.setMonitoria(super.entityManager.find(Monitoria.class, 1L));
        return atividade;
    }

}
