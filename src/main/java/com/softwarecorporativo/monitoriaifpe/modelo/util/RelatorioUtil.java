/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.modelo.util;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Edmilson Santana
 */
public class RelatorioUtil {

    /**
     * @param dados
     * @param parametros
     * @param arquivoJasper
     * @return
     * @throws net.sf.jasperreports.engine.JRException *
     */
    public static byte[] gerarRelatorioPDF(Collection<?> dados, HashMap<String, Object> parametros, String arquivoJasper) throws JRException {
        InputStream inputStream = RelatorioUtil.class.getClassLoader().getResourceAsStream("/reports/" + arquivoJasper);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
        byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, parametros, new JRBeanCollectionDataSource(dados));
        return bytes;
    }

}
