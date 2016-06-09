/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import com.softwarecorporativo.monitoriaifpe.servico.LoginService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class LoginBean extends GenericBean<Usuario> {

    private static final long serialVersionUID = -93031811969557575L;

    @EJB
    private LoginService loginService;

    public String efetuarLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(Boolean.TRUE);
        try {
            Usuario usuario = loginService.buscarUsuarioPorLogin(entidadeNegocio.getLogin());
            if (usuario.getSenha().equals(entidadeNegocio.getSenha())) {
                context.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
            }
        } catch (NegocioException e) {
            super.adicionarMensagemView("Usuário não encontrado");
            return "login?faces-redirect=true";
        }

        return "admin/admin?faces-redirect=true";
    }

    public String efetuarLogout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("usuarioLogado");
        return "login?faces-redirect=true";
    }

    @Override
    void inicializarEntidadeNegocio() {
        setEntidadeNegocio(loginService.getEntidadeNegocio());
    }

    @Override
    void inicializarServico() {
        setService(loginService);
    }

}
