/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.modelo.util;

import com.softwarecorporativo.monitoriaifpe.modelo.relatorio.frequencia.DiaDTO;
import com.softwarecorporativo.monitoriaifpe.modelo.relatorio.frequencia.RelatorioDTO;
import com.softwarecorporativo.monitoriaifpe.modelo.relatorio.frequencia.SemanaDTO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

/**
 *
 * @author Edmilson Santana
 */
public class RelatorioUtil {
    
    private static String RELATORIO_FREQUENCIA = "";
    
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, JRException, IOException {
        
        RelatorioUtil relatorio = new RelatorioUtil();
        Collection<RelatorioDTO> dados = new ArrayList<>();
        RelatorioDTO relatorioDTO = new RelatorioDTO();
        relatorioDTO.setAno(2015);
        relatorioDTO.setNome("Edmilson");
        relatorioDTO.setEdital("2015.2");
        relatorioDTO.setMatricula("20141Y6-RC0323");
        relatorioDTO.setMes("Agosto");
        relatorioDTO.setOrientador("Marcos Costa");
        relatorioDTO.setDisciplina("Software Corporativo");
        relatorioDTO.setCurso("Análise de Sistemas");
        List<SemanaDTO> semanas = new ArrayList<>();
        List<DiaDTO> dias = new ArrayList<>();
        DiaDTO diaDTO = new DiaDTO();
        diaDTO.setData(new Date());
        diaDTO.setHorario("14:00/18:00");
        dias.add(diaDTO);
        SemanaDTO semanaDTO = new SemanaDTO();
        semanaDTO.setDescricao("Descricao1");
        semanaDTO.setObservacao("Observação grande demais");
        semanaDTO.setDias(dias);
        semanas.add(semanaDTO);
        semanaDTO = new SemanaDTO();
        semanaDTO.setDescricao("Descricao2");
        semanaDTO.setDias(dias);
        semanas.add(semanaDTO);
        relatorioDTO.setSemanas(semanas);
        dados.add(relatorioDTO);
        relatorio.exportarRelatorioPDF(dados);
        
    }
    
    
    public void exportarRelatorioPDF(Collection<?> dados) throws JRException, IOException {
        HashMap<String, Object> parametros = new HashMap<>();
        JasperPrint print = JasperFillManager.fillReport("C:\\Users\\EdmilsonS\\Desktop\\relatorioFrequencia.jasper", parametros, new JRBeanCollectionDataSource(dados));
        
        OutputStreamExporterOutput fileOutputStream = new SimpleOutputStreamExporterOutput(new File("report.pdf"));
        
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(fileOutputStream);
        
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(conf);
        exporter.exportReport();
        
    }
}
