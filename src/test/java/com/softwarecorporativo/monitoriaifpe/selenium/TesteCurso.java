/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.selenium;

import com.softwarecorporativo.monitoriaifpe.selenium.pages.CursoPage;
import com.softwarecorporativo.monitoriaifpe.selenium.sauce.SauceLabsTest;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Edmilson Santana
 */
public class TesteCurso {

    final String urlBase = "https://localhost:8181/monitoriaifpe";

    final String urlCurso = "/publico/curso.xhtml";
    private WebDriver driver;

    @Before
    public void inicializar() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\EdmilsonS\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();

    }

    @Test
    public void testeEfetuarLogin() throws Exception {

        driver.get(urlBase);

        String login = "admin";
        String nomeUsuario = "Administrador";
        String senha = "admin";

        WebElement txtLogin = driver.findElement(By.id("login:login"));
        WebElement txtSenha = driver.findElement(By.name("login:senha"));

        txtLogin.sendKeys(login);
        txtSenha.sendKeys(senha);

        WebElement botao = driver.findElement(
                By.cssSelector("button[type='submit']"));

        botao.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        ExpectedCondition condicaoEsperada = ExpectedConditions
                .visibilityOfElementLocated(By.id("conteudo"));
        wait.until(condicaoEsperada);

        boolean usuarioNaTela = driver.getPageSource().contains(nomeUsuario);

        assertTrue(usuarioNaTela);
    }

    @Test
    public void testeCadastroCurso() {

        driver.get(urlBase + urlCurso);

        String descricaoCurso = "Análise de Sistemas";
        String codigoCampus = "RC";
        String codigoCurso = "Y6";
        String grauCurso = "SUPERIOR";

        WebElement txtDescricao = driver.findElement(
                By.id("formularioCurso:j_idt38:formCadastroCurso:descricao"));
        WebElement txtCodigoCampus = driver.findElement(
                By.id("formularioCurso:j_idt38:formCadastroCurso:codigoCampus"));
        WebElement txtCodigoCurso = driver.findElement(
                By.id("formularioCurso:j_idt38:formCadastroCurso:codigoCurso"));

        txtDescricao.sendKeys(descricaoCurso);
        txtCodigoCampus.sendKeys(codigoCampus);
        txtCodigoCurso.sendKeys(codigoCurso);

        WebElement listaGrausCursos = driver.findElement(
                By.id("formularioCurso:j_idt38:formCadastroCurso:grauCurso_input"));
        Select grausCurso = new Select(listaGrausCursos);
        grausCurso.selectByVisibleText(grauCurso);

        WebElement botaoCadastro = driver.findElement(
                By.cssSelector("button[type='submit']"));

        botaoCadastro.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        ExpectedCondition condicaoEsperada
                = ExpectedConditions.presenceOfElementLocated(By.cssSelector("p"));
        wait.until(condicaoEsperada);

        WebElement abaCursosCadastrados = driver.findElement(
                By.linkText("Cursos Cadastrados"));
        abaCursosCadastrados.click();

        boolean cursoCadastrado = driver.getPageSource()
                .contains(descricaoCurso);

        assertTrue(cursoCadastrado);

        WebElement botaoEditar = driver.findElement(
                By.cssSelector(".ui-icon-pencil"));

        botaoEditar.click();

        txtDescricao = driver.findElement(
                By.id("formularioCurso:j_idt38:formTabelaCurso:tabelaCurso:0:descricao"));
        txtDescricao.clear();
        txtDescricao.sendKeys("Análise");

        WebElement botaoSalvar = driver.findElement(
                By.cssSelector("span.ui-icon.ui-icon-check"));
        botaoSalvar.click();

        wait = new WebDriverWait(driver, 10);
        condicaoEsperada
                = ExpectedConditions.presenceOfElementLocated(By.cssSelector("p"));
        wait.until(condicaoEsperada);

        WebElement botaoRemover = driver.findElement(
                By.cssSelector(".ui-icon-trash"));

        botaoRemover.click();

        wait = new WebDriverWait(driver, 10);
        condicaoEsperada
                = ExpectedConditions.presenceOfElementLocated(
                        By.xpath("(//button[@id='formularioCurso:j_idt38:formTabelaCurso:tabelaCurso:0:j_idt71'])[2]"));
        wait.until(condicaoEsperada);

        WebElement confirmarRemover = driver.findElement(
                By.xpath("(//button[@id='formularioCurso:j_idt38:formTabelaCurso:tabelaCurso:0:j_idt71'])[2]"));

        confirmarRemover.click();

        abaCursosCadastrados = driver.findElement(
                By.linkText("Cursos Cadastrados"));
        abaCursosCadastrados.click();

        cursoCadastrado = driver.getPageSource()
                .contains(descricaoCurso);
        assertFalse(cursoCadastrado);
    }

}
