/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.util.PropriedadesUtil;
import java.util.Properties;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.annotation.Resource;
import javax.ejb.Stateless;

/**
 *
 * @author Edmilson Santana
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SecurityAccessService {

    private static final String PROPS_FILE_NAME = "authentication-data.properties";

    @Resource(lookup = "java:app/custom/authentication-data")
    private Properties authenticationData;

    public void salvarPropriedadesAcesso(String sal, String usuario) {
        authenticationData.setProperty(usuario, sal);
        PropriedadesUtil.salvarPropriedades(PROPS_FILE_NAME, authenticationData);
    }

}
