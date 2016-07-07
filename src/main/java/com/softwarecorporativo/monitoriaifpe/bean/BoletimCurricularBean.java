/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.boletim.BoletimCurricular;
import com.softwarecorporativo.monitoriaifpe.servico.BoletimCurricularService;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author douglas
 */
@ManagedBean
@ViewScoped
public class BoletimCurricularBean extends GenericBean<BoletimCurricular> {

    private static final long serialVersionUID = -3208305042223279157L;

    @ManagedProperty(value = "#{userSettings}")
    private UserSettings userSettings;

    @EJB
    private BoletimCurricularService boletimCurricularService;

    @Override
    void inicializarEntidadeNegocio() {

        setEntidadeNegocio(boletimCurricularService.getEntidadeNegocio());
    }

    @Override
    void inicializarServico() {
        setService(boletimCurricularService);
    }

    public List<BoletimCurricular> getBoletinsAluno() {
        Aluno aluno = (Aluno) userSettings.getUsuario();
        return aluno.getBoletins();
    }
    
    public void cadastrarBoletim() {

        Aluno aluno = (Aluno) userSettings.getUsuario();
        BoletimCurricular variavelAuxiliar = entidadeNegocio;
        entidadeNegocio.setAluno(aluno);
        super.cadastrar();
        aluno.addBoletimCurricular(variavelAuxiliar);
        

    }

    public String fluxoDeCadastro(FlowEvent event) {
        return event.getNewStep();
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }

}
