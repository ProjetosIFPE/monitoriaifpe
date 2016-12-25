/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.curso.integracao;

import com.softwarecorporativo.monitoriaifpe.servico.CursoService;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.embeddable.EJBContainer;
import javax.faces.application.FacesMessage;
import javax.naming.NamingException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Edmilson Santana
 */
public class TesteCursoBean {

    private static EJBContainer container;
    private static CursoService cursoService;
    private FacesMessage facesMessages;
    private static final String MODULE_NAME = "embedded";
    private static final String TARGET_DIR = "target/" + MODULE_NAME;

    @BeforeClass
    public static void setUp() {
        cursoService = (CursoService) getBean(CursoService.class);
    }

    @AfterClass
    public static void tearDown() {
        getContainer().close();
    }

    @Test
    public void teste() {

    }

    public static EJBContainer getContainer() {
        if (container == null) {
            container = prepareContainer();
        }
        return container;
    }

    public static EJBContainer prepareContainer() {
        Map<String, Object> properties = new HashMap<>();

        try {
            File target = prepareModuleDirectory();
            properties.put(EJBContainer.MODULES, target);
            properties.put("org.glassfish.ejb.embedded.glassfish.installation.root",
                    "target/classes/glassfish");
            return EJBContainer.createEJBContainer(properties);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static File prepareModuleDirectory() throws IOException {
        File result = new File(TARGET_DIR);
        FileUtils.copyDirectory(new File("target/classes"), result);
    //    FileUtils.copyFile(new File("target/test-classes/META-INF/persistence.xml"),
      //          new File(TARGET_DIR + "/META-INF/persistence.xml"));
        return result;
    }

    public static <T> T getBean(Class<T> beanClass) {
        String resource = "java:global/" + MODULE_NAME + "/" + beanClass.getSimpleName();
        T bean = null;
        try {
            bean = (T) getContainer().getContext().lookup(resource);
        } catch (NamingException ex) {
            Logger.getLogger(TesteCursoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }
}
