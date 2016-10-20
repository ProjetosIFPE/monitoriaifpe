/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.atividade;

import com.softwarecorporativo.monitoriaifpe.funcionais.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
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

/**
 *
 * @author EdmilsonS
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
        assertEquals(26, atividades.size());
        for (Atividade atividade : atividades) {
            assertTrue(atividade.getDescricao().startsWith("Correção"));
        }
    }

    /*
    @Test
    public void testeConsultarAtividadesDoMes() {
        TypedQuery<Atividade> query = super.entityManager.createQuery(
                "SELECT a FROM Atividade a WHERE a.data BETWEEN ?1 AND ?2 ORDER BY a.data",
                Atividade.class);

        query.setParameter(1, Util.getDate(1, Calendar.JANUARY, 2016));
        query.setParameter(2, Util.getDate(31, Calendar.JANUARY, 2016));

        List<Atividade> atividades = query.getResultList();
        assertEquals(23, atividades.size());
        Calendar calendar = Calendar.getInstance();
        for (Atividade atividade : atividades) {
            calendar.setTime(atividade.getData());
            assertEquals(Calendar.JANUARY, calendar.get(Calendar.MONTH));
        }
    }
     */
    @Test
    public void testeConsultarAtividadesSemObservacoes() {

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

    /*
    @Test
    public void testeConsultarAtividadesPassadas() {
        TypedQuery<Atividade> query = super.entityManager.createQuery(
                "SELECT a FROM Atividade a WHERE a.data < current_date() ORDER BY a.descricao",
                Atividade.class);

        Date dataAtual = Calendar.getInstance().getTime();
        List<Atividade> atividades = query.getResultList();
        assertEquals(46, atividades.size());
        for (Atividade atividade : atividades) {
            assertTrue(atividade.getData().before(dataAtual));
        }

    } */

    @Test
    public void testeVerificarQuantidadeAtividades() {
        Long quantidadeEsperada = 46L;
        TypedQuery<Long> query = super.entityManager.createQuery(
                "SELECT COUNT(a) FROM Atividade a WHERE EXISTS ( SELECT m FROM Monitoria m)",
                Long.class);
        Long quantidade = query.getSingleResult();
        assertEquals(quantidadeEsperada, quantidade);
    }

    @Test
    public void testeConsultarAtividadeMaisAntiga() {
        Atividade atividadeEsperada = super.entityManager.find(Atividade.class, 1L);
        TypedQuery<Atividade> query = super.entityManager.createQuery(
                "SELECT a FROM Atividade a WHERE NOT(a.data > ALL ( SELECT aa.data FROM Atividade aa))",
                Atividade.class);

        Atividade atividade = query.getSingleResult();
        assertEquals(atividadeEsperada, atividade);
    }

    @Test
    public void testeRemoverAtividadesDeMonitoria() {
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

    /*
    @Test
    public void testeCriarAtividadeComHorarioEntradaAposSaida() {
        String mensagemEsperada = "O horário de saída deve ser posterior ao horário de entrada";
        Atividade atividade = montarObjetoAtividade();
        atividade.setHorarioEntrada(Util.getTime(14, 30, 0));
        atividade.setHorarioSaida(Util.getTime(14, 0, 0));
        Set<ConstraintViolation<Atividade>> constraintViolations = validator.validate(atividade);
        assertEquals(1, constraintViolations.size());
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(mensagemEsperada, mensagemObtida);
    } 

    @Test
    public void testeCriarAtividadeComHorarioEntradaIgualSaida() {
        String mensagemEsperada = "O horário de saída deve ser posterior ao horário de entrada";
        Atividade atividade = montarObjetoAtividade();
        atividade.setHorarioEntrada(Util.getTime(14, 0, 0));
        atividade.setHorarioSaida(Util.getTime(14, 0, 0));
        Set<ConstraintViolation<Atividade>> constraintViolations = validator.validate(atividade);
        assertEquals(1, constraintViolations.size());
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(mensagemEsperada, mensagemObtida);
    }*/

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

    /*
    @Test
    public void testeCriarAtividadeComAtributosInvalidos() {
        Atividade atividade = montarObjetoAtividade();
        atividade.setData(null);
        atividade.setDescricao(null);
        atividade.setMonitoria(null);
        atividade.setHorarioEntrada(null);
        atividade.setHorarioSaida(null);
        atividade.setSituacao(null);
        Set<ConstraintViolation<Atividade>> constraintViolations = validator.validate(atividade);
        assertEquals(7, constraintViolations.size());

    }

    @Test
    public void testeCriarAtividadeForaDoSemestreEmPeriodo() {    
        Atividade atividade = montarObjetoAtividade();
        atividade.setData(Util.getDate(14, 11, 2016));
    } */

    @Test
    public void testeCriarAtividadeValida() {
        Atividade atividade = montarObjetoAtividade();
        Set<ConstraintViolation<Atividade>> constraintViolations = validator.validate(atividade);
        assertEquals(0, constraintViolations.size());
    }

    private Atividade montarObjetoAtividade() {
        Atividade atividade = new Atividade();
        Date date = Calendar.getInstance().getTime();
        /*atividade.setData(date);
        atividade.setDescricao("Descrição da Atividade");
        atividade.setHorarioEntrada(Util.getTime(14, 0, 0));
        atividade.setHorarioSaida(Util.getTime(15, 0, 0));*/
        atividade.setObservacoes("Observação da Atividade");
        atividade.setMonitoria(super.entityManager.find(Monitoria.class, 1L));
        return atividade;
    }

}
