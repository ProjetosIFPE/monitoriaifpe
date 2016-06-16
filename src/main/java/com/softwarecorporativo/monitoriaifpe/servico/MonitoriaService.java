/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Constantes;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

/**
 *
 * @author Edmilson Santana
 */
@Stateless
public class MonitoriaService extends GenericService<Monitoria> {


    @Override
    public Monitoria getEntidadeNegocio() {
       return new Monitoria();
    }

    @Override
    public Class<Monitoria> getClasseEntidade() {
       return Monitoria.class;
    }
    
    
    
    
    
  
    
}
