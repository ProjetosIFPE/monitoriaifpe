/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Constantes;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@SessionScoped
public class UserSettings implements Serializable{

    private static final long serialVersionUID = 677831240483987806L;

    private String tema = "vader";

    private Usuario usuario;
    
    public Usuario getUsuario() {
        if(usuario == null){
            FacesContext context = FacesContext.getCurrentInstance();
            usuario = (Usuario) context.getExternalContext().getSessionMap().get(Constantes.ATRIBUTO_USUARIO_LOGADO);
       }
        return this.usuario;
    }


    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String[] getTemas() {
        return new String[]{"afterdark", "afternoon", "afterwork", "aristo",
            "black-tie", "blitzer", "bluesky", "bootstrap", "casablanca",
            "cupertino", "cruze", "dark-hive", "delta", "dot-luv",
            "eggplant", "excite-bike", "flick", "glass-x", "home",
            "hot-sneaks", "humanity", "le-frog", "midnight", "mint-choc",
            "overcast", "pepper-grinder", "redmond", "rocket", "sam",
            "smoothness", "south-street", "start", "sunny", "swanky-purse",
            "trontastic", "ui-darkness", "ui-lightness", "vader"};
    }
}
