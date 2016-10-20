/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.servico.AlunoService;
import com.softwarecorporativo.monitoriaifpe.servico.CursoService;
import com.softwarecorporativo.monitoriaifpe.servico.ProfessorService;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@RequestScoped
public class CadastroUsuarioBean extends Bean {

    @EJB
    private AlunoService alunoService;

    @EJB
    private ProfessorService professorService;
    
    @EJB
    private CursoService cursoService;

    private Aluno aluno = new Aluno();

    private Professor professor = new Professor();
    
    
    public void cadastrarAluno() {
        super.cadastrar(alunoService, aluno);
    }

    public void cadastrarProfessor() {
        super.cadastrar(professorService, professor);
    }

    @Override
    void inicializarEntidades() {
        aluno = alunoService.getEntidadeNegocio();
        professor = professorService.getEntidadeNegocio();
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    
    public List<Curso> getCursos() {
        return cursoService.listarTodos();
    }
    
    public Boolean possuiCursos() {
        return cursoService.possuiCursos();
    }
    
    

}
