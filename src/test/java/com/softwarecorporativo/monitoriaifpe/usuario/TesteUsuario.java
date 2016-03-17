/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.usuario;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.impl.AlunoImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.impl.CursoImpl;
import com.softwarecorporativo.monitoriaifpe.usuario.impl.UsuarioImpl;
import com.softwarecorporativo.monitoriaifpe.util.Util;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Constantes;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Grau;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author EdmilsonS
 */
public class TesteUsuario extends MonitoriaTestCase {
    
    
    @Test
    public void testeCadastrarUsuario() {
        Aluno aluno = new AlunoImpl();
        Curso curso = new CursoImpl();
        curso.setDescricao("Análise de Sistemas");
        curso.setModalidade(Grau.SUPERIOR);
        super.entityManager.persist(curso);
        String senha = "admin";
        aluno.setNome("Edmilson");
        aluno.setSobrenome("Santana");
        aluno.setLogin("EdmilsonSantana");
        aluno.setEmail("edmilsonsantana2@hotmail.com");
        aluno.setSenha(Util.criptografarSenha(senha, senha, Constantes.CONSTANTE_CRIPTOGRAFIA));
        aluno.setMatricula("20141Y6-RC0323");
        aluno.setCurso(curso);
        super.entityManager.persist(aluno);
        assertTrue(aluno.getChavePrimaria() > 0);
    }
    
   
}
