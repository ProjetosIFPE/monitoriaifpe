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
    
    private static final long serialVersionUID = -4032758349094923282L;

    @Override
    public String getMessage() {
        return super.getMessage(); 
    }
    
    
    
}
