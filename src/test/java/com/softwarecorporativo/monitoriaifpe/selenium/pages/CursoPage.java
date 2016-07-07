/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.selenium.pages;

import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Edmilson Santana
 */
public class CursoPage {

    @FindBy(id = "formularioCurso:j_idt5:formCadastroCurso:descricao")
    private WebElement formDescricaoCurso;

    @FindBy(id = "formularioCurso:j_idt5:formTabelaCurso:tabelaCurso:0:descricao")
    private WebElement tabelaDescricaoCurso;

    @FindBy(id = "formularioCurso:j_idt5:formCadastroCurso:codigoCampus")
    private WebElement formCodigoCampus;

    @FindBy(id = "formularioCurso:j_idt5:formCadastroCurso:codigoCurso")
    private WebElement formCodigoCurso;

    @FindBy(id = "formularioCurso:j_idt5:formCadastroCurso:codigoCurso")
    private WebElement formGrauCurso;

    @FindBy(css = "button[type='submit']")
    private WebElement botaoCadastro;

    @FindBy(css = ".ui-icon-pencil")
    private WebElement botaoEditar;

    @FindBy(css = ".ui-icon-check")
    private WebElement botaoAlterar;

    @FindBy(css = ".ui-icon-trash")
    private WebElement botaoRemover;

    private final WebDriver driver;

    private final String urlBase = "https://localhost:8181/monitoriaifpe/publico/curso.xhtml";

    public CursoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void visitar() {
        driver.get(urlBase);
    }

    public String cadastrarCurso(Curso curso) {

        setFormDescricaoCurso(curso.getDescricao());
        setFormCodigoCampus(curso.getCodigoCampus());
        setFormCodigoCurso(curso.getCodigoCurso());

        botaoCadastro.click();
        return obterTextoMensagemTela();
    }

    public boolean isCursoCadastrado(Curso curso) {

        return driver.getPageSource().contains(curso.getDescricao());
    }

    public String alterarCurso(Curso curso) {
        By seletorAba = By.linkText("Cursos Cadastrados");
        WebElement abaCursosCadastrados = aguardarElemento(seletorAba);
        abaCursosCadastrados.click();
        botaoEditar.click();
        setTabelaDescricaoCurso(curso.getDescricao());
        botaoAlterar.click();
        return obterTextoMensagemTela();
    }

    public String removerCurso() {
        botaoRemover.click();
        return obterTextoMensagemTela();
    }

    public void setFormDescricaoCurso(String descricaoCurso) {
        this.formDescricaoCurso.clear();
        this.formDescricaoCurso.sendKeys(descricaoCurso);
    }

    public void setTabelaDescricaoCurso(String descricaoCurso) {
        this.tabelaDescricaoCurso.clear();
        this.tabelaDescricaoCurso.sendKeys(descricaoCurso);
    }

    public void setFormCodigoCampus(String codigoCampus) {
        this.formCodigoCampus.clear();
        this.formCodigoCampus.sendKeys(codigoCampus);
    }

    public void setFormCodigoCurso(String codigoCurso) {
        this.formCodigoCurso.clear();
        this.formCodigoCurso.sendKeys(codigoCurso);
    }

    public String obterTextoMensagemTela() {
        WebElement mensagem = this.aguardarElemento(By.cssSelector(".ui-growl-title"));
        System.out.println(mensagem.getText());
        return mensagem.getText();
    }
    

    public WebElement aguardarElemento(By by) {
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public boolean elementoPresenteEmTela(WebElement elemento) {
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeSelected(elemento));
    }

}
