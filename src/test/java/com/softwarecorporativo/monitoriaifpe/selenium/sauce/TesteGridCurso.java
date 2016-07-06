/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.selenium.sauce;

import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Grau;
import com.softwarecorporativo.monitoriaifpe.selenium.pages.CursoPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Edmilson Santana
 */
public class TesteGridCurso extends SauceLabsTest {

    private CursoPage cursoPage;

    public TesteGridCurso(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super(os, version, browser, deviceName, deviceOrientation);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        cursoPage = new CursoPage(driver);
    }

    @Test
    public void testeFluxoCadastroCurso() {

        cursoPage.visitar();

        Curso curso = montarObjetoCurso();

        cursoPage.cadastrarCurso(curso);

        boolean cursoCadastrado = cursoPage.isCursoCadastrado(curso);
        assertTrue(cursoCadastrado);

        curso.setDescricao("Tecnologia em Análise de Sistemas");

        cursoPage.alterarCurso(curso);

        cursoPage.removerCurso();

        cursoCadastrado = cursoPage.isCursoCadastrado(curso);
        assertTrue(!cursoCadastrado);

    }

    @Test
    public void testeCadastroCustoExistente() {
        cursoPage.visitar();

        Curso curso = montarObjetoCurso();

        cursoPage.cadastrarCurso(curso);

        boolean cursoCadastrado = cursoPage.isCursoCadastrado(curso);
        assertTrue(cursoCadastrado);

        assertEquals("Informação já cadastrada no sistema", cursoPage.cadastrarCurso(curso));
    }

    public Curso montarObjetoCurso() {
        Curso curso = new Curso();
        curso.setDescricao("Análise de Sistemas");
        curso.setCodigoCampus("RC");
        curso.setCodigoCurso("Y6");
        curso.setGrau(Grau.TECNICO);
        return curso;
    }
}
