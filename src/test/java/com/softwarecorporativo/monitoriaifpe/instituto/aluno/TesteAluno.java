/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.instituto.aluno;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.usuario.Usuario;
import com.softwarecorporativo.monitoriaifpe.util.Util;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Constantes;
import java.util.logging.Level;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author douglas
 */
public class TesteAluno extends MonitoriaTestCase {

    @Test
    public void testePersistAluno() {

        LOGGER.log(Level.INFO, "Teste de Persistência Aluno - {0}", name.getMethodName());

        Aluno aluno = montarObjetoAluno();
        super.entityManager.persist(aluno);
        super.entityManager.flush();
        super.entityManager.refresh(aluno.getCurso());
        super.entityManager.refresh(aluno);
        assertNotNull(aluno.getChavePrimaria());

    }

    @Test
    public void testeUpdateAluno() {
        LOGGER.log(Level.INFO, "Teste de Atualização Email de um Aluno - {1}", name.getMethodName());
        Aluno alunoBuscado = super.entityManager.find(Aluno.class, 1L);
        String emailAntigo = alunoBuscado.getEmail();
        alunoBuscado.setEmail("edmilson@hotmail.com");
        super.entityManager.merge(alunoBuscado);
        super.entityManager.flush();
        super.entityManager.clear();
        alunoBuscado = super.entityManager.find(Aluno.class, 1L);
        assertEquals("edmilson@hotmail.com", alunoBuscado.getEmail());

    }

    @Test
    public void testeDeleteAluno() {
        LOGGER.log(Level.INFO, "Teste de Remoção de um Aluno - {2}", name.getMethodName());
        Usuario alunoBuscado = super.entityManager.find(Usuario.class, 3L);
        super.entityManager.remove(alunoBuscado);
        super.entityManager.flush();
        assertNull(super.entityManager.find(Aluno.class, 3L));
    }

    @Test
    public void testeJPQLSelecionarAlunosPeloAnoDaMatricula() {
        LOGGER.log(Level.INFO, "Teste de JPQL LIKE Ano de Aluno - {1}", name.getMethodName());
        TypedQuery<Long> query = super.entityManager.createQuery(
                "SELECT COUNT(a.chavePrimaria) from Aluno a WHERE a.matricula LIKE :matricula", Long.class);
        query.setParameter("matricula", "2014%");
        Long resultado = query.getSingleResult();
        assertEquals(new Long(3), resultado);
    }

    @Test
    public void testeJPQLSelecionarAlunosPeloCurso() {
        LOGGER.log(Level.INFO, "Teste de JPQL Aluno por curso - {1}", name.getMethodName());
        TypedQuery<Long> query = super.entityManager.createQuery(
                "SELECT COUNT(a.chavePrimaria) from Aluno a WHERE a.curso = :curso", Long.class);
        Curso curso = super.entityManager.find(Curso.class, 1L);
        query.setParameter("curso", curso);
        Long resultado = query.getSingleResult();
        assertEquals(new Long(5), resultado);
    }

    @Test
    public void testeJPQLSelecionarAlunosPelaInicialDoNome() {
        LOGGER.log(Level.INFO, "Teste de JPQL Inicial de Auno - {1}", name.getMethodName());
        TypedQuery<Long> query = super.entityManager.createQuery(
                "SELECT COUNT(a.chavePrimaria) from Aluno a WHERE a.nome LIKE :nome", Long.class);
        query.setParameter("nome", "M%");
        Long resultado = query.getSingleResult();
        assertEquals(new Long(1), resultado);
    }

    @Test
    public void testeJPQLQuantidadeDeAlunos() {
        LOGGER.log(Level.INFO, "Teste de JPQL Aluno por grau do curso - {1}", name.getMethodName());
        TypedQuery<Long> query = super.entityManager.createQuery(
                "SELECT COUNT(a.chavePrimaria) from Aluno a WHERE a.curso = (SELECT c.chavePrimaria FROM Curso c WHERE c.grau = 'TECNICO')", Long.class);
        Long resultado = query.getSingleResult();
        assertEquals(new Long(2), resultado);
    }

    private Aluno montarObjetoAluno() {
        Aluno alunoCriado = new Aluno();
        alunoCriado.setNome("Fulano");
        alunoCriado.setSobrenome("Silva");
        alunoCriado.setEmail("fulano@gmail.com");
        alunoCriado.setLogin("fulano21");
        alunoCriado.setMatricula("1911Y6-RC2222");
        String password = Util.criptografarSenha("123", "123", Constantes.CONSTANTE_CRIPTOGRAFIA);
        alunoCriado.setSenha(password);
        Curso curso = super.entityManager.find(Curso.class, 1L);
        alunoCriado.setCurso(curso);
        Disciplina disciplina = super.entityManager.find(Disciplina.class, 1L);
        alunoCriado.addDisciplina(disciplina);

        return alunoCriado;
    }

}
