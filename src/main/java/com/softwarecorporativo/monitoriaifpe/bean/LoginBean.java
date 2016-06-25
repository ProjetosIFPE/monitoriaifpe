/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import com.softwarecorporativo.monitoriaifpe.servico.LoginService;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class LoginBean extends GenericBean<Usuario> {

    private static final long serialVersionUID = -93031811969557575L;

    @EJB
    private LoginService loginService;

    public String efetuarLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context
                .getExternalContext().getRequest();

        try {
            request.login(entidadeNegocio.getLogin(), entidadeNegocio.getSenha());
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.setAttribute("usuarioLogado", entidadeNegocio);
            inicializarEntidadeNegocio();
        } catch (ServletException ex) {
            ex.printStackTrace();
            inicializarEntidadeNegocio();
            super.adicionarMensagemView("Senha ou usuário inválidos!",
                    FacesMessage.SEVERITY_WARN);
            return "falha";
        }

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

    @Override
    void inicializarEntidadeNegocio() {
        setEntidadeNegocio(loginService.getEntidadeNegocio());
    }

    @Override
    void inicializarServico() {
        setService(loginService);
    }

}
