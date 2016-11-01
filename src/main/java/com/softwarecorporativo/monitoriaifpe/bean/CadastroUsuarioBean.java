/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import com.softwarecorporativo.monitoriaifpe.modelo.util.Util;
import com.softwarecorporativo.monitoriaifpe.servico.AlunoService;
import com.softwarecorporativo.monitoriaifpe.servico.CursoService;
import com.softwarecorporativo.monitoriaifpe.servico.ProfessorService;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import org.primefaces.event.CaptureEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class CadastroUsuarioBean extends Bean {

    @EJB
    private AlunoService alunoService;

    @EJB
    private ProfessorService professorService;

    @EJB
    private CursoService cursoService;

    private Aluno aluno = new Aluno();

    private Professor professor = new Professor();

    private String fileNameOnCapture;
    
     private UploadedFile fileOnUpload;

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

    public String getFileNameOnCapture() {
        return fileNameOnCapture;
    }

   

    
    

}
