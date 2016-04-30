/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.usuario;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.professor.Professor;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.apache.commons.lang.RandomStringUtils;
import static org.junit.Assert.assertEquals;
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
    public void testeCriarUsuarioComNomeTamanhoMaxInvalido() {
        Usuario usuario = this.montarObjetoUsuario();
        String mensagemEsperada = "tamanho deve estar entre 1 e 30";
        String nome = "E".concat(RandomStringUtils.random(30, "e"));
        usuario.setNome(nome);
        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }

    @Test
    public void testeCriarUsuarioComSobrenomeInvalido() {
        Usuario usuario = this.montarObjetoUsuario();
        String mensagemEsperada = "Deve possuir uma única letra maiúscula, seguida por letras minúsculas";
        usuario.setNome("sobre nome");
        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }
    
    @Test
    public void testeCriarUsuarioComSobrenomeTamanhoMaxInvalido() {
        Usuario usuario = this.montarObjetoUsuario();
        String mensagemEsperada = "tamanho deve estar entre 1 e 30";
        String sobrenome = "E".concat(RandomStringUtils.random(30, "e"));
        usuario.setNome(sobrenome);
        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }

    @Test
    public void testeCriarUsuarioComLoginInvalido() {
        Usuario usuario = this.montarObjetoUsuario();
        String mensagemEsperada = "O login permite apenas letras, números, caracteres sublinhado e hífen com tamanho mínimo 3 e máximo 16";
        usuario.setLogin("lo gin");
        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }
    
    @Test
    public void testeCriarUsuarioComLoginTamanhoMaxInvalido() {
        Usuario usuario = this.montarObjetoUsuario();
        String mensagemEsperada = "tamanho deve estar entre 3 e 16";
        String login = RandomStringUtils.random(17, "e");
        usuario.setLogin(login);
        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }
    
     @Test
    public void testeCriarUsuarioComLoginTamanhoMinInvalido() {
        Usuario usuario = this.montarObjetoUsuario();
        String mensagemEsperada = "tamanho deve estar entre 3 e 16";
        String login = RandomStringUtils.random(2, "e");
        usuario.setLogin(login);
        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }

    @Test
    public void testeCriarUsuarioComSenhaInvalida() {
        Usuario usuario = this.montarObjetoUsuario();
        String mensagemEsperada = "A senha permite apenas letras minúsculas, números, caractere sublinhado e hífen com tamanho mínimo 6 e máximo 18";
        usuario.setSenha("Senha_1");
        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
        String mensagemObtida = constraintViolations.iterator().next().getMessage();
        assertEquals(1, constraintViolations.size());
        assertEquals(mensagemEsperada, mensagemObtida);
    }
    
    @Test
    public void testeCriarUsuarioComSenhaTamanhoMaxInvalido() {
        Usuario usuario = this.montarObjetoUsuario();
        String mensagemEsperada = "tamanho deve estar entre 6 e 18";
        String senha = RandomStringUtils.random(19, "e");
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
        String senha = RandomStringUtils.random(5, "e");
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
        professor.setSobrenome("Teste");
        professor.setLogin("usuarioTeste");
        professor.setSenha("usuario123");
        professor.setEmail("usuario@hotmail.com");
        return professor;
    }
}
