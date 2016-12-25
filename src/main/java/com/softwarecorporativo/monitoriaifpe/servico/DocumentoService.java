/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.documento.Documento;
import com.softwarecorporativo.monitoriaifpe.modelo.util.DigestDefault;
import java.io.File;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Edmilson Santana
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DocumentoService {

    public static final String TEMPLATE_REPORT = "TEMPLATE_REPORT";

    @PersistenceContext(unitName = "monitoriaifpe-unit-dev", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    public Documento gerarDocumentoPDF(Collection<?> dados, Map<String, Object> parametros, String arquivoJasper) {
        InputStream inputStream = DocumentoService.class.getClassLoader()
                .getResourceAsStream(File.separatorChar + "reports" + File.separatorChar + arquivoJasper);
        try {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
            byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, parametros, new JRBeanCollectionDataSource(dados));
            return novoDocumento(bytes);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public Documento buscarDocumento(byte[] conteudoDocumento) {
        String hashDocumento = gerarHashDocumento(conteudoDocumento);
        Documento documento = this.buscarPorHash(hashDocumento);
        return documento;
    }

    private Documento novoDocumento(byte[] conteudo) {
        Documento documento = new Documento();
        documento.setHashConteudo(gerarHashDocumento(conteudo));
        documento.setConteudo(conteudo);
        documento.setDataEmissao(Calendar.getInstance().getTime());
        this.salvarDocumento(documento);
        return documento;
    }

    private String gerarHashDocumento(byte[] pdfData) {
        return DigestDefault.hashAsHexString(pdfData, DigestDefault.DEFAULT_ALGORITHM);
    }

    private void salvarDocumento(Documento documento) {
        entityManager.persist(documento);
    }

    private Documento buscarPorHash(String hash) {
        return entityManager.find(Documento.class, hash);
    }
}
