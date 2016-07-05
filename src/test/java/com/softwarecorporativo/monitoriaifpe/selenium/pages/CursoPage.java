/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.selenium.pages;

import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Edmilson Santana
 */
public class CursoPage {

    @FindBy(id = "formularioCurso:j_idt38:formCadastroCurso:descricao")
    private WebElement formDescricaoCurso;

    @FindBy(id = "formularioCurso:j_idt38:formTabelaCurso:tabelaCurso:0:descricao")
    private WebElement tabelaDescricaoCurso;

    @FindBy(id = "formularioCurso:j_idt38:formCadastroCurso:codigoCampus")
    private WebElement formCodigoCampus;

    @FindBy(id = "formularioCurso:j_idt38:formCadastroCurso:codigoCurso")
    private WebElement formCodigoCurso;

    @FindBy(xpath = "//div[@id='formularioCurso:j_idt38:formCadastroCurso:grauCurso']/div[3]/span")
    private WebElement formGrauCurso;

    @FindBy(css = "button[type='submit']")
    private WebElement botaoCadastro;

    @FindBy(css = ".ui-icon-pencil")
    private WebElement botaoEditar;

    @FindBy(css = ".ui-icon-check")
    private WebElement botaoAlterar;

    @FindBy(css = ".ui-icon-trash")
    private WebElement botaoRemover;

    @FindBy(xpath = "(//button[@id='formularioCurso:j_idt38:formTabelaCurso:tabelaCurso:0:j_idt71'])[2]")
    private WebElement botaoConfirmaRemover;

    @FindBy(linkText = "Cursos Cadastrados")
    private WebElement abaCursosCadastrados;

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
        /* */
       // setFormGrauCurso(curso.getGrau().name());

        botaoCadastro.click();

        return obterTextoMensagemTela();
    }

    public boolean isCursoCadastrado(Curso curso) {
        abaCursosCadastrados.click();
        return driver.getPageSource().contains(curso.getDescricao());
    }

    public String alterarCurso(Curso curso) {
        botaoEditar.click();
        setTabelaDescricaoCurso(curso.getDescricao());
        botaoAlterar.click();
        return obterTextoMensagemTela();
    }

    public void removerCurso() {
        botaoRemover.click();
        botaoConfirmaRemover.click();

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

    public void setFormGrauCurso(String grauCurso) {
        formGrauCurso.click();
        formGrauCurso.findElement(By.linkText(grauCurso)).click();
    }

    public String obterTextoMensagemTela() {

        WebElement mensagemTela = this.aguardarElemento(By.cssSelector("p"));
        return mensagemTela.getText();
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
