/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.servico.AlunoService;
import com.softwarecorporativo.monitoriaifpe.servico.CursoService;
import java.util.List;
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
    
    @EJB
    private CursoService cursoService;

    private Long cursoId;
    
    @Override
    void inicializarEntidadeNegocio() {
        setEntidadeNegocio(alunoService.getEntidadeNegocio());
    }
    
    @Override
    void inicializarServico() {
        setService(alunoService);
    }
    
    public List<Curso> listarCursos(){
        return cursoService.listarTodos();
    } 

    @Override
    public void gravar() {
        Curso curso = cursoService.buscarEntidade(cursoId);
        entidadeNegocio.setCurso(curso);
        super.gravar(); 
    }
    
    
     public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
    
}
