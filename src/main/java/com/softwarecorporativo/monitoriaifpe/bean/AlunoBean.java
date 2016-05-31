/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.servico.AlunoService;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Douglas Albuquerque
 */
@ManagedBean
@ViewScoped
public class AlunoBean extends GenericBean<Aluno> {
    
    @EJB
    private AlunoService alunoService;
    
    public AlunoBean() {
        super.setEntidadeNegocio(new Aluno());
    }
    
    public void cadastrarAluno() {
        
       alunoService.salvar(entidadeNegocio);
        
//        System.out.println("Cadastrando aluno");
//        return "login?faces-redirect=true";
    }

    @Override
    void inicializarEntidadeNegocio() {
        setEntidadeNegocio(alunoService.getEntidadeNegocio());
    }
    
}
