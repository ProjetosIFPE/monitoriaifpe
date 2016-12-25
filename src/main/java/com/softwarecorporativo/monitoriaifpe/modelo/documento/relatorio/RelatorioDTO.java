/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.modelo.documento.relatorio;

import com.google.common.collect.Lists;
import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edmilson Santana
 */
public class RelatorioDTO implements Serializable {

    private static final long serialVersionUID = 5418177404788125688L;

    private static final int QTD_ATIVIDADES_SEMANAS_RELATORIO = 5;

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

    private List<SemanaDTO> semanas;

    private RelatorioDTO(RelatorioDTOBuilder relatorioBuilder) {
        setAno(relatorioBuilder.ano);
        setNome(relatorioBuilder.nome);
        setDisciplina(relatorioBuilder.disciplina);
        setOrientador(relatorioBuilder.orientador);
        setCurso(relatorioBuilder.curso);
        setMatricula(relatorioBuilder.matricula);
        setEdital(relatorioBuilder.edital);
        setMes(relatorioBuilder.mes);
        setAssinaturaOrientador(relatorioBuilder.assinaturaOrientador);
        setSemanas(relatorioBuilder.semanas);
    }

    public InputStream getAssinaturaOrientador() {
        return assinaturaOrientador;
    }

    private void setAssinaturaOrientador(InputStream assinaturaOrientador) {
        this.assinaturaOrientador = assinaturaOrientador;
    }

    public List<SemanaDTO> getSemanas() {
        return semanas;
    }

    private void setSemanas(List<SemanaDTO> semanas) {
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

    private void setNome(String nome) {
        this.nome = nome;
    }

    public String getDisciplina() {
        return disciplina;
    }

    private void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getOrientador() {
        return orientador;
    }

    private void setOrientador(String orientador) {
        this.orientador = orientador;
    }

    public String getCurso() {
        return curso;
    }

    private void setCurso(String curso) {
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    private void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEdital() {
        return edital;
    }

    private void setEdital(String edital) {
        this.edital = edital;
    }

    public int getAno() {
        return ano;
    }

    private void setAno(int ano) {
        this.ano = ano;
    }

    public String getMes() {
        return mes;
    }

    private void setMes(String mes) {
        this.mes = mes;
    }

    public static class RelatorioDTOBuilder {

        private int ano;

        private String mes;

        public String nome;

        private String disciplina;

        private String orientador;

        private String curso;

        private String matricula;

        private String edital;

        private InputStream assinaturaOrientador;

        private final List<SemanaDTO> semanas = new ArrayList<>();

        public RelatorioDTOBuilder setEdital(String edital) {
            this.edital = edital;
            return this;
        }

        public RelatorioDTOBuilder setMes(String mes) {
            this.mes = mes;
            return this;
        }

        public RelatorioDTOBuilder setAno(int ano) {
            this.ano = ano;
            return this;
        }

        public RelatorioDTOBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public RelatorioDTOBuilder setDisciplina(String disciplina) {
            this.disciplina = disciplina;
            return this;
        }

        public RelatorioDTOBuilder setOrientador(String orientador) {
            this.orientador = orientador;
            return this;
        }

        public RelatorioDTOBuilder setCurso(String curso) {
            this.curso = curso;
            return this;
        }

        public RelatorioDTOBuilder setMatricula(String matricula) {
            this.matricula = matricula;
            return this;
        }

        public RelatorioDTOBuilder setAssinaturaOrientador(InputStream assinatura) {
            this.assinaturaOrientador = assinatura;
            return this;
        }

        public RelatorioDTOBuilder setAtividades(List<Atividade> atividades) {
            List<List<Atividade>> listaAtividadesSemanais = Lists.partition(atividades,
                    QTD_ATIVIDADES_SEMANAS_RELATORIO);
            for (List<Atividade> atividadesSemanais : listaAtividadesSemanais) {
                SemanaDTO novaSemana = new SemanaDTO.SemanaDTOBuilder()
                        .setAtividadesSemanais(atividadesSemanais).build();
                semanas.add(novaSemana);
            }
            return this;
        }

        public RelatorioDTO build() {
            return new RelatorioDTO(this);
        }
    }
}
