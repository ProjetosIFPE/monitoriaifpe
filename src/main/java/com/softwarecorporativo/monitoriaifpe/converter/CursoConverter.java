package com.softwarecorporativo.monitoriaifpe.converter;
 
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("cursoConverter")
public class CursoConverter implements Converter {
 
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        
        return null;
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        
       return null;
    }   
}   