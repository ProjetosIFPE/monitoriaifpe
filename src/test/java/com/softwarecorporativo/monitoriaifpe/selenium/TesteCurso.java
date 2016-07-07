/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.selenium;

import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        //driver = new FirefoxDriver();
    }

    @After
    public void finalizar() {
        driver.close();
    }

    @Test
    public void testeEfetuarLogin() throws Exception {

        driver.get(urlBase);

        String login = "admin";
        String nomeUsuario = "Administrador";
        String senha = "fulu123";

        WebElement txtLogin = driver.findElement(By.id("login:login"));
        WebElement txtSenha = driver.findElement(By.name("login:senha"));

        txtLogin.sendKeys(login);
        txtSenha.sendKeys(senha);

        WebElement botao = driver.findElement(
                By.cssSelector("button[type='submit']"));

        botao.click();
        
        aguardarElemento(By.id("conteudo"));

        boolean usuarioNaTela = this.telaContemTexto(nomeUsuario);

        assertTrue(usuarioNaTela);
    }

    @Test
    public void testeCadastroCurso() {

        driver.get(urlBase + urlCurso);

        String descricaoCurso = "Análise de Sistemas";
        String codigoCampus = "RC";
        String codigoCurso = "Y6";

        WebElement txtDescricao = driver.findElement(
                By.id("formularioCurso:j_idt5:formCadastroCurso:descricao"));
        WebElement txtCodigoCampus = driver.findElement(
                By.id("formularioCurso:j_idt5:formCadastroCurso:codigoCampus"));
        WebElement txtCodigoCurso = driver.findElement(
                By.id("formularioCurso:j_idt5:formCadastroCurso:codigoCurso"));

        txtDescricao.sendKeys(descricaoCurso);
        txtCodigoCampus.sendKeys(codigoCampus);
        txtCodigoCurso.sendKeys(codigoCurso);

        WebElement botaoCadastro = driver.findElement(
                By.cssSelector("button[type='submit']"));

        botaoCadastro.click();

        aguardarElemento(By.cssSelector("p"));

        WebElement abaCursosCadastrados = driver.findElement(
                By.linkText("Cursos Cadastrados"));
        abaCursosCadastrados.click();

        boolean cursoCadastrado = telaContemTexto(descricaoCurso);

        assertTrue(cursoCadastrado);

        WebElement botaoEditar = driver.findElement(
                By.cssSelector(".ui-icon-pencil"));

        botaoEditar.click();

        txtDescricao = driver.findElement(
                By.id("formularioCurso:j_idt5:formTabelaCurso:tabelaCurso:0:descricao"));
        txtDescricao.clear();
        txtDescricao.sendKeys("Análise");

        WebElement botaoSalvar = driver.findElement(
                By.cssSelector("span.ui-icon.ui-icon-check"));
        botaoSalvar.click();

        aguardarElemento(By.cssSelector("p"));

        WebElement botaoRemover = driver.findElement(
                By.cssSelector(".ui-icon-trash"));

        aguardarElemento(By.cssSelector("p"));

        botaoRemover.click();

        abaCursosCadastrados = driver.findElement(
                By.linkText("Cursos Cadastrados"));
        abaCursosCadastrados.click();

        cursoCadastrado = telaContemTexto(descricaoCurso);
        assertFalse(cursoCadastrado);
    }
   

    public Boolean telaContemTexto(String texto) {
        return driver.getPageSource().contains(texto);
    }

    public WebElement aguardarElemento(By by) {
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public boolean elementoContemTexto(By by, String texto) {

        Boolean contemTexto = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.textToBe(by, texto));

        return contemTexto;
    }
    
     /*
    @Test
    public void testeMensagemDeErroCodCampus() {

        driver.get(urlBase + urlCurso);

        String descricaoCurso = "TADS";
        String codigoCampus = "Rc";

        WebElement txtDescricao = driver.findElement(
                By.id("formularioCurso:j_idt5:formCadastroCurso:descricao"));
        WebElement txtCodigoCampus = driver.findElement(
                By.id("formularioCurso:j_idt5:formCadastroCurso:codigoCampus"));

        txtDescricao.sendKeys(descricaoCurso);
        txtCodigoCampus.sendKeys(codigoCampus);
        txtCodigoCampus.sendKeys(Keys.TAB);

        By mensagemValidacao = By.id("formularioCurso:j_idt5:formCadastroCurso:messageCodigoCampus");

        assertTrue(this.elementoContemTexto(mensagemValidacao, "O código do campus deve possuir apenas duas letras maiúsculas"));
    }
*/

}
