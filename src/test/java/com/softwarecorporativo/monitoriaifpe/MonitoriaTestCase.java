/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author EdmilsonS
 */
public class MonitoriaTestCase {

    private static final String PERSISTENCE_UNIT_NAME = "com.softwarecorporativo_monitoriaifpe_war_1.0-SNAPSHOTPU";
    protected Logger logger;
    private static EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    public MonitoriaTestCase() {
        logger = Logger.getLogger(getClass());
      
    }

    @BeforeClass
    public static void setUpClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    @AfterClass
    public static void tearDownClass() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }

    }

    @Before
    public void setUp() {
        try {
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            logger.log(Level.FATAL, "Não foi possível criar o EntityManager. Mensagem: " + e.getMessage());
            fail();
        }

    }

    @After
    public void tearDown() {
        if (entityManager != null) {
            entityManager.close();
        }

    }

}
