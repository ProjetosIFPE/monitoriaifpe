/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.modelo.util;

import com.softwarecorporativo.monitoriaifpe.modelo.relatorio.frequencia.Dia;
import com.softwarecorporativo.monitoriaifpe.modelo.relatorio.frequencia.Relatorio;
import com.softwarecorporativo.monitoriaifpe.modelo.relatorio.frequencia.Semana;
import com.softwarecorporativo.monitoriaifpe.servico.AtividadeService;
import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Edmilson Santana
 */
public class RelatorioUtil {

    public static final String TEMPLATE_REPORT = "TEMPLATE_REPORT";

    /**
     * @param dados
     * @param parametros
     * @param arquivoJasper
     * @return
     * @throws net.sf.jasperreports.engine.JRException *
     */
    public static byte[] gerarRelatorioPDF(Collection<?> dados, Map<String, Object> parametros, String arquivoJasper) throws JRException {
        InputStream inputStream = RelatorioUtil.class.getClassLoader()
                .getResourceAsStream(File.separatorChar + "reports" + File.separatorChar + arquivoJasper);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
        byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, parametros, new JRBeanCollectionDataSource(dados));
        return bytes;
    }

    public static void compilarRelatorio(String src, String dest) throws JRException {
        JasperCompileManager.compileReportToFile(src, dest);
    }

    public static void main(String[] args) throws JRException, FileNotFoundException, IOException {

        Map<String, Object> parametros = new HashMap<>();
        String reportsPath = File.separatorChar + "reports" + File.separatorChar
                + "img" + File.separatorChar + AtividadeService.RELATORIO_JASPER_BACKGROUND_IMAGE;
        URL reportsAbsolutePath = AtividadeService.class.getClassLoader().getResource(reportsPath);
        parametros.put(RelatorioUtil.TEMPLATE_REPORT, reportsAbsolutePath);

        List<Relatorio> dados = new ArrayList<>();
        Relatorio relatorio = new Relatorio();
        relatorio.setCurso("Análise de Sistemas");
        relatorio.setNome("Edmilson Santana");

        List<Semana> semanaDTOs = new ArrayList<>();
        relatorio.setSemanas(semanaDTOs);

        Semana semanaDTO = new Semana();
        semanaDTO.setDescricao("Exercicios");
        semanaDTOs.add(semanaDTO);

        List<Dia> dias = new ArrayList<>();
        semanaDTO.setDias(dias);

        Dia diaDTO = new Dia();
        diaDTO.setData(new Date());
        diaDTO.setHorario("14:00 - 18:00");
        dias.add(diaDTO);

        dados.add(relatorio);

        Dimension dimension = new Dimension(660, 180);
        Rectangle rc = new Rectangle(dimension);

        BufferedImage buffer = ImageIO.read(new File("C:\\Users\\EdmilsonS\\assinatura.png"));

        int cropX = (buffer.getWidth() - rc.width) > 0 ? buffer.getWidth() - rc.width : 0;
        int cropY = (buffer.getHeight() - rc.height) > 0 ? buffer.getHeight() - rc.height : 0;
        BufferedImage subImage = buffer.getSubimage(cropX, cropY, rc.width, rc.height);
       
       
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(subImage, "png", os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        relatorio.setAssinaturaOrientador(is);

        byte[] bytes = gerarRelatorioPDF(dados, parametros, AtividadeService.RELATORIO_JASPER_ATIVIDADE);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\EdmilsonS\\relatorio.pdf"));
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
