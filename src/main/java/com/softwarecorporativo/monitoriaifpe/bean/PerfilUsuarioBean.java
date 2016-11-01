/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.modelo.util.Util;
import com.softwarecorporativo.monitoriaifpe.servico.AlunoService;
import com.softwarecorporativo.monitoriaifpe.servico.CursoService;
import com.softwarecorporativo.monitoriaifpe.servico.ProfessorService;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import org.primefaces.event.CaptureEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class PerfilUsuarioBean extends Bean {

    private String fileNameOnCapture;

    private UploadedFile fileOnUpload;

    public void oncapture(CaptureEvent captureEvent) {
        fileNameOnCapture = Util.getRandomString();
        byte[] data = captureEvent.getData();

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String newFileName = externalContext.getRealPath("") + File.separator + "resources"
                + File.separator + "img" + File.separator + fileNameOnCapture + ".jpeg";

        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        } catch (IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }
    }

    public void uploadAssinatura() {
        if (fileOnUpload != null) {
            adicionarMensagemView("Succesful" + fileOnUpload.getFileName() + " is uploaded.");
        }
    }
    
    public void atualizarPerfil() {
        uploadAssinatura();
    }

    public String getFileNameOnCapture() {
        return fileNameOnCapture;
    }

    public UploadedFile getFileOnUpload() {
        return fileOnUpload;
    }

    public void setFileOnUpload(UploadedFile fileOnUpload) {
        this.fileOnUpload = fileOnUpload;
    }

    @Override
    void inicializarEntidades() {
    }

}
