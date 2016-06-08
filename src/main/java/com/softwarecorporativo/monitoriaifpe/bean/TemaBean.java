/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@SessionScoped
public class TemaBean {

    private String tema = "bootstrap";

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
