/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.exception;

import javax.ejb.ApplicationException;

/**
 *
 * @author Edmilson Santana
 */
@ApplicationException(rollback = true)
public class NegocioException extends Exception {

    private String chave; 
    private static final long serialVersionUID = -4032758349094923282L;
    public static final String OBJETO_EXISTENTE = "excecao.NegocioException.objetoExistente";  
    public static final String CURSO_ASSOCIADO_A_USUARIO = "exception.NegocioException.cursoService.remover";
    public static final String DISCIPLINA_ASSOCIADA = "exception.NegocioException.disciplinaService.remover";
    public static final String LOGIN_NAO_ENCONTRADO = "exception.NegocioException.loginService.verificarCadastroDeUsuario";
    public static final String DISCIPLINA_JA_CADASTRADA = "exception.NegocioException.disciplinaService.salvarDisciplinaComPeriodoAtual";
       
    
    
    public NegocioException(String chave) {
        this.chave = chave;
    }  

    public String getChave() {
        return chave;
    }
    
    @Override
    public String getMessage() {
        MensagemExcecao mensagemExcecao = new MensagemExcecao(this);
        return mensagemExcecao.getMensagem();
    }

}
