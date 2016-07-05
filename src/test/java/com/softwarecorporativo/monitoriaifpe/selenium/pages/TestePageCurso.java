/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.selenium.pages;

import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Grau;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Edmilson Santana
 */
public class TestePageCurso {

    private WebDriver driver;
    private CursoPage cursoPage;

    @Before
    public void inicializar() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\EdmilsonS\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        cursoPage = new CursoPage(driver);
    }

    @Test
    public void testeFluxoCadastroCurso() {

        cursoPage.visitar();

        Curso curso = montarObjetoCurso();

        assertEquals("Cadastro realizado com sucesso!", cursoPage.cadastrarCurso(curso));

        boolean cursoCadastrado = cursoPage.isCursoCadastrado(curso);
        assertTrue(cursoCadastrado);

        curso.setDescricao("Tecnologia em Análise de Sistemas");

        cursoPage.alterarCurso(curso);
        /*TODO: Verificar problema da mensagem do alterar*/
        cursoCadastrado = cursoPage.isCursoCadastrado(curso);
        assertTrue(cursoCadastrado);

        cursoPage.removerCurso();

        cursoCadastrado = cursoPage.isCursoCadastrado(curso);
        assertFalse(cursoCadastrado);

    }

    @After
    public void finalizar() {
        //driver.close();
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
