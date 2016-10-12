package com.softwarecorporativo.monitoriaifpe.usuario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import com.softwarecorporativo.monitoriaifpe.funcionais.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import java.util.Set;
import javax.validation.ConstraintViolation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Edmilson Santana
 */
public class TesteUsuario extends MonitoriaTestCase {

    @Test
    public void testeCriarUsuarioComNomeInvalido() {
        Usuario usuario = this.montarObjetoUsuario();
        String mensagemEsperada = "Deve possuir uma única letra maiúscula, seguida por letras minúsculas";
        usuario.setNome("nome");
        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }
    
    @Test
    public void testeCriarUsuarioComNomeValido() {
        Usuario usuario = this.montarObjetoUsuario();
        usuario.setNome("Usuario Valido Teste");
        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    public void testeCriarUsuarioComNomeTamanhoMaxInvalido() {
        Usuario usuario = this.montarObjetoUsuario();
        String mensagemEsperada = "tamanho deve estar entre 1 e 30";
        String nome = "Edmilsonmanoelguilhermedesantana";
        usuario.setNome(nome);
        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }


    @Test
    public void testeCriarUsuarioComSenhaInvalida() {
        Usuario usuario = this.montarObjetoUsuario();
        String mensagemEsperada = "A senha permite apenas letras, números, caractere sublinhado, hífen e caracteres especiais; deve ter tamanho mínimo 6 e máximo 18";
        usuario.setSenha("senha\\1");
        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }

    @Test
    public void testeCriarUsuarioComSenhaTamanhoMaxInvalido() {
        Usuario usuario = this.montarObjetoUsuario();
        String mensagemEsperada = "tamanho deve estar entre 6 e 18";
        String senha = "Senha1232z2s-4_sasvs9";
        usuario.setSenha(senha);
        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }

    @Test
    public void testeCriarUsuarioComSenhaTamanhoMinInvalido() {
        Usuario usuario = this.montarObjetoUsuario();
        String mensagemEsperada = "tamanho deve estar entre 6 e 18";
        String senha = "s1_";
        usuario.setSenha(senha);
        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }

    @Test
    public void testeCriarUsuarioComEmailInvalido() {
        Usuario usuario = this.montarObjetoUsuario();
        String mensagemEsperada = "Não é um endereço de e-mail";
        usuario.setEmail("usuario@");
        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }

    @Test
    public void testeCriarUsuarioComEmailTamanhoMaxInvalido() {
        Usuario usuario = this.montarObjetoUsuario();
        String mensagemEsperada = "tamanho deve estar entre 1 e 30";
        usuario.setEmail("usuarioooooooooooooooooooooooooooooooooo@hotmail.com");
        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }

    public Usuario montarObjetoUsuario() {
        Professor professor = new Professor();
        professor.setNome("Usuario");
        professor.setSenha("usuario123");
        professor.setEmail("usuario@hotmail.com");
        professor.setCpf("137.229.621-29");
        professor.setSiape("1234567");
        professor.setCurso(entityManager.find(Curso.class, 1l));
        return professor;
    }
}
