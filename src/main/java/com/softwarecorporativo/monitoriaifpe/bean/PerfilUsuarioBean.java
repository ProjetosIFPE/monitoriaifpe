/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.servico.UsuarioService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.poi.util.IOUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
public class PerfilUsuarioBean extends Bean {

    @EJB(lookup = "java:global/monitoriaifpe/ProfessorService")
    private UsuarioService usuarioService;

    @ManagedProperty(value = "#{usuarioBean}")
    private UsuarioBean usuarioBean;

    private UploadedFile fileOnUpload;

    public void uploadAssinatura() {
        if (fileOnUpload != null) {
            Professor professor = (Professor) usuarioBean.getUsuario();
            try {
                professor.setAssinatura(IOUtils.toByteArray(fileOnUpload.getInputstream()));
                usuarioService.atualizar(professor);
            } catch (IOException | NegocioException ex) {
                adicionarMensagemView(ex.getMessage(), FacesMessage.SEVERITY_WARN);
            }
        }
    }

    public void atualizarPerfil() {
        uploadAssinatura();
    }

    public UploadedFile getFileOnUpload() {
        return fileOnUpload;
    }

    public void setFileOnUpload(UploadedFile fileOnUpload) {
        this.fileOnUpload = fileOnUpload;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    @Override
    void inicializarEntidades() {
    }

}
