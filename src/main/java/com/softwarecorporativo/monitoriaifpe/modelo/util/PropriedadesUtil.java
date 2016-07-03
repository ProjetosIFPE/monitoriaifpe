/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.modelo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edmilson Santana
 */
public class PropriedadesUtil {

    private Properties propriedades;

    public PropriedadesUtil(String[] nomesArquivo) {
        propriedades = new Properties();
        InputStream is;

        for (String nome : nomesArquivo) {
            is = this.getClass().getClassLoader().getResourceAsStream(nome);

            if (is != null) {
                try {
                    propriedades.load(is);
                    is.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public String get(String chave) {
        return propriedades.getProperty(chave);
    }

    public synchronized void adicionar(String chave, String valor) {
        propriedades.put(chave, valor);
    }

    public static synchronized Properties carregarPropriedades(String propsFileName, Properties propriedades) {

        try {
            InputStream inputStream = PropriedadesUtil.class
                    .getClassLoader().getResourceAsStream("/properties/" + propsFileName);
            propriedades.load(inputStream);
            inputStream.close();
            return propriedades;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public static synchronized void salvarPropriedades(String propsFileName, Properties properties) {
        FileOutputStream outputStream;
        try {

            URL propsFilePath = PropriedadesUtil.class
                    .getClassLoader().getResource("/properties/" + propsFileName);
            outputStream = new FileOutputStream(new File(propsFilePath.toURI()));
            properties.store(outputStream, null);
            outputStream.close();
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
