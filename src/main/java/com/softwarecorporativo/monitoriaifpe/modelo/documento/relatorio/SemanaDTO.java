/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.modelo.documento.relatorio;

import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Edmilson Santana
 */
public class SemanaDTO implements Serializable {

    private static final long serialVersionUID = 1187906149482262433L;

    private String descricao;

    private String observacao;

    private List<DiaDTO> dias;

    private SemanaDTO(SemanaDTOBuilder semanaDTOBuilder) {
        setDescricao(semanaDTOBuilder.descricao);
        setObservacao(semanaDTOBuilder.observacao);
        setDias(semanaDTOBuilder.dias);
    }

    public List<DiaDTO> getDias() {
        return dias;
    }

    private void setDias(List<DiaDTO> dias) {
        this.dias = dias;
    }

    public String getDescricao() {
        return descricao;
    }

    private void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    private void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public static class SemanaDTOBuilder {

        private String descricao;

        private String observacao;

        private final List<DiaDTO> dias = new ArrayList<>();

        public SemanaDTOBuilder setAtividadesSemanais(List<Atividade> atividades) {
            StringBuilder descricaoSemana = new StringBuilder();
            StringBuilder observacaoSemana = new StringBuilder();
            for (Atividade atividade : atividades) {
                if (!StringUtils.isEmpty(atividade.getDescricao())) {
                    descricaoSemana.append(atividade.getDescricao());
                    descricaoSemana.append("; ");
                }
                if (!StringUtils.isEmpty(atividade.getObservacoes())) {
                    observacaoSemana.append(atividade.getObservacoes());
                    observacaoSemana.append("; ");
                }
                DiaDTO novoDia = new DiaDTO.DiaDTOBuilder()
                        .setAtividadeDiaria(atividade).build();
                dias.add(novoDia);
            }
            descricao = descricaoSemana.toString();
            observacao = observacaoSemana.toString();
            return this;
        }

        public SemanaDTO build() {
            return new SemanaDTO(this);
        }
    }

}
