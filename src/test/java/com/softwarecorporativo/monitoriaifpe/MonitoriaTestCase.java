/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe;

import com.softwarecorporativo.monitoriaifpe.util.DbUnitUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;

/**
 *
 * @author EdmilsonS
 */
public class MonitoriaTestCase {

    private static final String PERSISTENCE_UNIT_NAME = "com.softwarecorporativo_monitoriaifpe_war_1.0-SNAPSHOTPU";
    private static EntityManagerFactory entityManagerFactory;
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    protected EntityManager entityManager;
    protected EntityTransaction entityTransaction;
    protected Validator validator;
    protected static final Logger LOGGER = Logger.getGlobal();
    @Rule
    public final TestName name;
    
    public MonitoriaTestCase() {
        this.name = new TestName();
        this.validator = factory.getValidator();
    }

    @BeforeClass
    public static void setUpClass() {
        LOGGER.setLevel(Level.INFO);
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
            prepararCenario();
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            fail("Não foi possível iniciar a transação. Mensagem " + e.getMessage());
        }

    }

    protected void prepararCenario() {
        DbUnitUtil.inserirDados();
    }

    @After
    public void tearDown() {
        try {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.commit();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            entityTransaction.rollback();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

    }

}
