/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.curso;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Grau;
import java.util.Set;
import java.util.logging.Level;
import javax.validation.ConstraintViolation;
import org.apache.commons.lang.RandomStringUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author EdmilsonS
 */
public class TesteCurso extends MonitoriaTestCase {

    @Test
    public void testeInserirCurso() {
        Curso curso = this.montarObjetoCurso();
        super.entityManager.persist(curso);
        super.entityManager.flush();
        super.entityManager.refresh(curso);
        assertNotNull(curso.getChavePrimaria());
    }

    @Test
    public void testAlterarCurso() {
        Curso cursoObtido = super.entityManager.find(Curso.class, 1L);
        cursoObtido.setModalidade(Grau.TECNICO);
        super.entityManager.merge(cursoObtido);
        super.entityManager.flush();
        super.entityManager.clear();
        Curso cursoAlterado = super.entityManager.find(Curso.class, 1L);
        assertEquals(cursoObtido.getModalidade(), cursoAlterado.getModalidade());
    }

    @Test
    public void testeRemoverPeriodo() {
        Curso curso = super.entityManager.find(Curso.class, 3L);
        super.entityManager.remove(curso);
        super.entityManager.flush();
        assertNull(super.entityManager.find(Curso.class, 3L));
    }

    @Test
    public void testeCriarCursoComDescricaoTamanhoExcedente() {
        String mensagemEsperada = "tamanho deve estar entre 0 e 100";
        Curso curso = montarObjetoCurso();
        String descricao = RandomStringUtils.random(101);
        curso.setDescricao(descricao);
        Set<ConstraintViolation<Curso>> constraintViolations = validator.validate(curso);
        assertEquals(1, constraintViolations.size());
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(mensagemEsperada, mensagemObtida);
    }
    
    @Test
    public void testeCriarCursoComCodigoDoCursoInvalido() {
        String mensagemEsperada = "O código do curso deve começar com uma letra maiúscula seguida de um inteiro entre 0 e 9";
        Curso curso = montarObjetoCurso();
        curso.setCodigoCurso("2R");
        Set<ConstraintViolation<Curso>> constraintViolations = validator.validate(curso);
        assertEquals(1, constraintViolations.size());
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(mensagemEsperada, mensagemObtida);
    }
    
    @Test
    public void testeCriarCursoComCodigoDoCampusInvalido() {
        String mensagemEsperada = "O código do campus deve possuir apenas duas letras maiúsculas";
        Curso curso = montarObjetoCurso();
        curso.setCodigoCampus("Rc");
        Set<ConstraintViolation<Curso>> constraintViolations = validator.validate(curso);
        assertEquals(1, constraintViolations.size());
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(mensagemEsperada, mensagemObtida);
    }

    @Test
    public void testeCriarCursoComDescricaoVazia() {
        String mensagemEsperada = "Não pode estar em branco";
        Curso curso = montarObjetoCurso();
        curso.setDescricao("");
        Set<ConstraintViolation<Curso>> constraintViolations = validator.validate(curso);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }

    public Curso montarObjetoCurso() {
        Curso curso = new Curso();
        curso.setModalidade(Grau.SUPERIOR);
        curso.setDescricao("Ciência da Computação");
        curso.setCodigoCampus("RC");
        curso.setCodigoCurso("Y7");
        return curso;
    }
}
