/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.documento.Documento;
import com.softwarecorporativo.monitoriaifpe.servico.DocumentoService;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class DocumentoBean {

    @EJB
    private DocumentoService documentoService;

    private Boolean validouDocumento = Boolean.FALSE;

    private Documento documentoValido;

    private StreamedContent fileOnDownload;

    private UploadedFile fileOnUpload;

    public void validarDocumento() {
        if (fileOnUpload != null) {
            documentoValido = documentoService.buscarDocumento(fileOnUpload.getContents());
            fileOnDownload = prepararDownloadDocumentoValido();
            validouDocumento = Boolean.TRUE;
        }
    }

    private StreamedContent prepararDownloadDocumentoValido() {
        ByteArrayContent byteArrayContent = null;
        if (possuiDocumentoParaBaixar()) {
            byteArrayContent = new ByteArrayContent(documentoValido.getConteudo(),
                    "application/pdf", "documento" + documentoValido.getDataEmissao() + ".pdf");
        }
        return byteArrayContent;
    }

    public Boolean possuiDocumentoParaBaixar() {
        return !Objects.isNull(documentoValido);
    }

    public Documento getDocumentoValido() {
        return documentoValido;
    }

    public void setDocumentoValido(Documento documentoValido) {
        this.documentoValido = documentoValido;
    }

    public StreamedContent getFileOnDownload() {
        return fileOnDownload;
    }

    public void setFileOnDownload(StreamedContent fileOnDownload) {
        this.fileOnDownload = fileOnDownload;
    }

    public UploadedFile getFileOnUpload() {
        return fileOnUpload;
    }

    public void setFileOnUpload(UploadedFile fileOnUpload) {
        this.fileOnUpload = fileOnUpload;
    }

    public Boolean isValidouDocumento() {
        return validouDocumento;
    }

    public void setValidouDocumento(Boolean validouDocumento) {
        this.validouDocumento = validouDocumento;
    }

}
