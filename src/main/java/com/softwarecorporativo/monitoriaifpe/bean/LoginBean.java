/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import com.softwarecorporativo.monitoriaifpe.servico.LoginService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class LoginBean extends GenericBean<Usuario> {
    
    @EJB
    private LoginService loginService;
    
    public String efetuarLogon() {
        System.out.println("Entrando no sistema");
        super.adicionarMensagemView("Bem-vindo" + super.entidadeNegocio.getLogin());
        
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
