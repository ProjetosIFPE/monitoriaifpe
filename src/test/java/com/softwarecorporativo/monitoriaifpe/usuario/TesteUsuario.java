/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.usuario;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.usuario.impl.UsuarioImpl;
import com.softwarecorporativo.monitoriaifpe.util.Util;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Constantes;
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
        Usuario usuario = new UsuarioImpl();
        String senha = "admin";
        usuario.setNome("Edmilson");
        usuario.setSobrenome("Santana");
        usuario.setLogin("EdmilsonSantana");
        usuario.setEmail("edmilsonsantana2@hotmail.com");
        usuario.setSenha(Util.criptografarSenha(senha, senha, Constantes.CONSTANTE_CRIPTOGRAFIA));
        super.entityManager.persist(usuario);
        assertTrue(usuario.getChavePrimaria() > 0);
    }
    
   
}
