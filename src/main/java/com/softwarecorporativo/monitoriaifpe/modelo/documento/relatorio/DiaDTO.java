/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.modelo.documento.relatorio;

import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.modelo.util.DataUtil;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Edmilson Santana
 */
public class DiaDTO implements Serializable {

    private static final long serialVersionUID = -7454750560814965814L;
    
    private Date data;
    
    private String horario;
    
    private DiaDTO(DiaDTOBuilder diaDTOBuilder) {
        setData(diaDTOBuilder.data);
        setHorario(diaDTOBuilder.horario);
    }
    
    public Date getData() {
        return data;
    }
    
    private void setData(Date data) {
        this.data = data;
    }
    
    public String getHorario() {
        return horario;
    }
    
    private void setHorario(String horario) {
        this.horario = horario;
    }
    
    public static class DiaDTOBuilder {
        
        private Date data;
        
        private String horario;
        
        public DiaDTOBuilder setAtividadeDiaria(Atividade atividade) {
            
            String horarioInicial = DataUtil.formatarDataHoraMinuto(atividade.getDataInicio());
            String horarioFinal = DataUtil.formatarDataHoraMinuto(atividade.getDataFim());
            
            horario = horarioInicial + " - " + horarioFinal;
            data = atividade.getDataInicio();
            return this;
        }
        
        public DiaDTO build() {
            return new DiaDTO(this);
        }
    }
}
