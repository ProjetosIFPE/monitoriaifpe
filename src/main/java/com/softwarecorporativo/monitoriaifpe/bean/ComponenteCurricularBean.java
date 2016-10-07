/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.ComponenteCurricular;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.servico.ComponenteCurricularService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class ComponenteCurricularBean extends GenericBean<ComponenteCurricular> {

    private static final long serialVersionUID = -3700833037294884488L;

    @ManagedProperty(value = "#{userSettings}")
    private UserSettings userSettings;

    @EJB
    private ComponenteCurricularService componenteCurricularService;

    @Override
    void inicializarEntidadeNegocio() {
        setEntidadeNegocio(componenteCurricularService.getEntidadeNegocio());

    }

    @Override
    void inicializarServico() {
        setService(componenteCurricularService);
    }

    public List<ComponenteCurricular> getComponentesPorCursoDoProfessor() {
       // Professor professor = (Professor) userSettings.getUsuario();
       /* ComponenteCurricular componenteCurricular = new ComponenteCurricular();
        componenteCurricular.setCodigoComponenteCurricular("A2");
        componenteCurricular.setDescricao("Software Corporativo");
        componenteCurricular.setChavePrimaria(1l);
        List<ComponenteCurricular> componentes = new ArrayList<>();
        componentes.add(componenteCurricular);*/
        return Collections.EMPTY_LIST;
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }

}
