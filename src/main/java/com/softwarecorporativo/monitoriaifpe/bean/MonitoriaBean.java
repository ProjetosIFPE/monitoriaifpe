/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.ComponenteCurricular;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.Turma;
import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.modelo.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Semestre;
import com.softwarecorporativo.monitoriaifpe.servico.DisciplinaService;
import com.softwarecorporativo.monitoriaifpe.servico.MonitoriaService;
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
public class MonitoriaBean extends GenericBean<Monitoria> {

    private static final long serialVersionUID = -4736071102515881964L;

    @ManagedProperty(value = "#{userSettings}")
    private UserSettings userSettings;

    private Long disciplinaId;

    @EJB
    private MonitoriaService monitoriaService;

    @EJB
    private DisciplinaService disciplinaService;

    private List<Monitoria> monitoriasDisciplina;

    @Override
    void inicializarEntidadeNegocio() {

        super.setEntidadeNegocio(monitoriaService.getEntidadeNegocio());
    }

    public void inicializarParametros() {
        Turma disciplina = disciplinaService.buscarEntidade(disciplinaId);
        popularMonitoriasPorDisciplina(disciplina);
    }

    @Override
    protected void inicializar() {
        super.inicializar();
    }

    @Override
    void inicializarServico() {
        setService(monitoriaService);
    }

    @Override
    public void cadastrar() {
        Aluno aluno = (Aluno) userSettings.getUsuario();
        entidadeNegocio.setAluno(aluno);
        super.cadastrar();
    }
    
    public List<Monitoria> getMonitoriasPorAluno() {
        //Aluno aluno = (Aluno) userSettings.getUsuario();
        //return monitoriaService.obterMonitoriasPorAluno(aluno);
        return Collections.EMPTY_LIST;
    }
    
    public List<Monitoria> getMonitorias() {
        //Aluno aluno = (Aluno) userSettings.getUsuario();
        //return monitoriaService.obterMonitoriasPorAluno(aluno);
        List<Monitoria> monitorias = new ArrayList<>();
        Monitoria monitoria = new Monitoria();
        monitoria.setChavePrimaria(1l);
        
        Turma disciplina = new Turma();
        disciplina.setChavePrimaria(1l);
        Periodo periodo = new Periodo();
        periodo.setChavePrimaria(1l);
        periodo.setAno(2015);
        periodo.setSemestre(Semestre.PRIMEIRO);
        disciplina.setPeriodo(periodo);
       
        ComponenteCurricular componente = new ComponenteCurricular();
        componente.setDescricao("Software Corporativo");
        componente.setChavePrimaria(1l);
        disciplina.setComponenteCurricular(componente);
        
        monitoria.setTurma(disciplina);
        
        monitorias.add(monitoria);
        
        return monitorias;
    }

    public List<Turma> getDisciplinasOfertadasParaMonitoria() throws NegocioException {
       // Aluno aluno = (Aluno) userSettings.getUsuario();
        return Collections.EMPTY_LIST;
    }

    protected void popularMonitoriasPorDisciplina(Turma disciplina) {
        monitoriasDisciplina = monitoriaService.obterMonitoriasPorDisciplina(disciplina);

    }

    public List<Monitoria> getMonitoriasDisciplina() {
        return monitoriasDisciplina;
    }

    public void setMonitoriasDisciplina(List<Monitoria> monitoriasDisciplina) {
        this.monitoriasDisciplina = monitoriasDisciplina;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }

}
