/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class LoginBean {

    private static final long serialVersionUID = -93031811969557575L;

    private Usuario usuario = new Usuario();

    public String efetuarLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context
                .getExternalContext().getRequest();

        try {
            request.login(usuario.getLogin(), usuario.getSenha());
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.setAttribute("usuarioLogado", usuario);

        } catch (ServletException ex) {
            ex.printStackTrace();

            return "falha";
        }
        usuario = new Usuario();
        return "sucesso";
    }

    public String efetuarLogout() throws ServletException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        request.logout();
        return "sair";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
