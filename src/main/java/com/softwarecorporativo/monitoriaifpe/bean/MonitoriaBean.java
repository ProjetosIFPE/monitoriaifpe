/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.ComponenteCurricular;
import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.modelo.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Modalidade;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Semestre;
import com.softwarecorporativo.monitoriaifpe.servico.MonitoriaService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author EdmilsonS
 */
@ManagedBean
@ViewScoped
public class MonitoriaBean extends GenericBean<Monitoria> {

    private static final long serialVersionUID = -4736071102515881964L;

    @EJB
    private MonitoriaService monitoriaService;

    private final List<Monitoria> monitorias = new ArrayList<>();

    @Override
    void inicializarEntidadeNegocio() {
        super.setEntidadeNegocio(monitoriaService.getEntidadeNegocio());
    }

    public Modalidade[] getModalidades() {

        return Modalidade.values();
    }

    public List<Monitoria> getMonitorias() {

        if (monitorias.isEmpty()) {
            Monitoria monitoria = new Monitoria();
            Aluno aluno = new Aluno();
            aluno.setNome("Edmilson Santana");
            monitoria.setAluno(aluno);
            ComponenteCurricular componenteCurricular = new ComponenteCurricular();
            componenteCurricular.setDescricao("Software Corporativo");
            Disciplina disciplina = new Disciplina();
            disciplina.setComponenteCurricular(componenteCurricular);
            monitoria.setDisciplina(disciplina);
            Periodo periodo = new Periodo();
            periodo.setAno(2015);
            periodo.setSemestre(Semestre.PRIMEIRO);
            monitoria.setPeriodo(periodo);
            monitoria.setModalidade(Modalidade.BOLSISTA);
            monitorias.add(monitoria);
            monitoria = new Monitoria();
            aluno = new Aluno();
            aluno.setNome("Edmilson Santana");
            monitoria.setAluno(aluno);
            componenteCurricular = new ComponenteCurricular();
            componenteCurricular.setDescricao("Arquitetura de Software");
            disciplina = new Disciplina();
            disciplina.setComponenteCurricular(componenteCurricular);
            monitoria.setDisciplina(disciplina);
            periodo = new Periodo();
            periodo.setAno(2015);
            periodo.setSemestre(Semestre.SEGUNDO);
            monitoria.setPeriodo(periodo);
            monitoria.setModalidade(Modalidade.VOLUNTARIO);
            monitorias.add(monitoria);
            System.out.println("Consultei");
        }
        return monitorias;
    }

}
