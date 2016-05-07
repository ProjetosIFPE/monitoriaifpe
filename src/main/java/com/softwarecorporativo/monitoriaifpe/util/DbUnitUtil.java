/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.util;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

/**
 *
 * @author
 */
public class DbUnitUtil {

    private static final String XML_FILE = "\\src\\main\\resources\\META-INF\\dataset.xml";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/monitoriaifpe";
    private static final String CONNECTION_USER = "root";
    private static final String CONNECTION_PASSWORD = "";

    public static void inserirDados() {

        IDatabaseConnection databaseConnection = null;
        try {

            databaseConnection = getDatabaseConnection();
            FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            builder.setColumnSensing(true);
            String workspace = System.getProperty("user.dir");
            IDataSet dataSet = builder.build(new File(workspace + XML_FILE));
            DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, dataSet);
        } catch (SQLException | DatabaseUnitException | MalformedURLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            try {
                if (databaseConnection != null) {
                    databaseConnection.close();
                    Connection connection = databaseConnection.getConnection();
                    if (connection != null) {
                        connection.close();
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
    }

    private static DatabaseConnection getDatabaseConnection() throws SQLException, DatabaseUnitException {
        Connection connection = DriverManager.getConnection(
                CONNECTION_URL, CONNECTION_USER, CONNECTION_PASSWORD);
        return new DatabaseConnection(connection);
    }

  
}
