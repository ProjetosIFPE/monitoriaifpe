/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Constantes;
import com.softwarecorporativo.monitoriaifpe.servico.UsuarioService;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = -93031811969557575L;

    @EJB(lookup = "java:global/monitoriaifpe/AlunoService")
    private UsuarioService usuarioService;

    private final Usuario usuario = new Usuario();

    public String efetuarLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context
                .getExternalContext().getRequest();

        try {
            request.login(usuario.getEmail(), usuario.getSenha());
            HttpSession session = (HttpSession) context.getExternalContext().getSession(Boolean.TRUE);
            Usuario usuarioLogado = usuarioService.getUsuario(usuario.getEmail());
            session.setAttribute(Constantes.ATRIBUTO_USUARIO_LOGADO, usuarioLogado);
            usuarioLogado = (Usuario) session.getAttribute(Constantes.ATRIBUTO_USUARIO_LOGADO);
            System.out.println(usuarioLogado.getEmail());
        } catch (ServletException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage(null, new FacesMessage("Usuário não encontrado!"));
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

    public Usuario getUsuario() {
        return usuario;
    }

}
