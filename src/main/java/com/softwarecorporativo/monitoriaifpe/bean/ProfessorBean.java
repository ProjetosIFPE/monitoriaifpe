/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.servico.ProfessorService;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ProfessorBean extends ViewScopedBean<Professor> {

    private static final long serialVersionUID = 5263486689492837240L;

    @EJB
    private ProfessorService professorService;

    @Override
    void inicializarEntidades() {
        setEntidadeNegocio(professorService.getEntidadeNegocio());
    }

    @Override
    void inicializarServico() {
        setService(professorService);
    }

}
