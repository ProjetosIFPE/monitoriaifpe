/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Grau;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Modalidade;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author EdmilsonS
 */
@ManagedBean
@ApplicationScoped
public class UtilBean {
    
    public Modalidade[] getModalidades() {

        return Modalidade.values();
    }
    
    public Grau[] getGraus() {
        return Grau.values();
    }
}
