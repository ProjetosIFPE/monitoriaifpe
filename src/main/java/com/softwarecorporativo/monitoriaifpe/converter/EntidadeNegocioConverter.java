/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.converter;

import antlr.StringUtils;
import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Edmilson Santana
 */
@FacesConverter(value = "entidadeNegocioConverter")
public class EntidadeNegocioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            return (EntidadeNegocio) component.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object entidade) {
        if (entidade != null && entidade instanceof EntidadeNegocio) {
            EntidadeNegocio entidadeNegocio = (EntidadeNegocio) entidade;
            String chave = entidadeNegocio.getChavePrimaria().toString();
            component.getAttributes().put(chave, entidade);
            return chave;
        }
        return null;
    }

}
