/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.modelo.relatorio.frequencia;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Edmilson Santana
 */
public class Relatorio implements Serializable {

    private static final long serialVersionUID = 5418177404788125688L;

    private String nome;

    private String disciplina;

    private String orientador;

    private String curso;

    private String matricula;

    private String edital;

    private int ano;

    private String mes;

    private int cargaHoraria;

    private InputStream assinaturaOrientador;

    private List<Semana> semanas;

    
    public InputStream getAssinaturaOrientador() {
        return assinaturaOrientador;
    }

    public void setAssinaturaOrientador(InputStream assinaturaOrientador) {
        this.assinaturaOrientador = assinaturaOrientador;
    }

   
    public List<Semana> getSemanas() {
        return semanas;
    }

    public void setSemanas(List<Semana> semanas) {
        this.semanas = semanas;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getOrientador() {
        return orientador;
    }

    public void setOrientador(String orientador) {
        this.orientador = orientador;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEdital() {
        return edital;
    }

    public void setEdital(String edital) {
        this.edital = edital;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
