/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author EdmilsonS
 */
@Stateless
public class MonitoriaService extends GenericService<Monitoria> {

    @EJB
    private BoletimCurricularService boletimCurricularService;
    
    @Override
    public Monitoria getEntidadeNegocio() {
       return new Monitoria();
    }

    @Override
    public Class<Monitoria> getClasseEntidade() {
       return Monitoria.class;
    }
    
  
    
}
