/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.modelo.util.constantes;

/**
 *
 * @author Edmilson Santana
 */
public enum SituacaoMonitoria {
    APROVADA("Aprovada"), 
    AGUARDANDO_APROVACAO("Aguardando Aprovação"), 
    REPROVADA("Reprovada");

    private final String label;

    private SituacaoMonitoria(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
