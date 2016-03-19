/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author EdmilsonS
 */
public class MonitoriaTestCase {

    private static final String PERSISTENCE_UNIT_NAME = "com.softwarecorporativo_monitoriaifpe_war_1.0-SNAPSHOTPU";

    protected static EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    @BeforeClass
    public static void setUpClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    @AfterClass
    public static void tearDownClass() {

        entityManagerFactory.close();
    }

    @Before
    public void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @After
    public void tearDown() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
