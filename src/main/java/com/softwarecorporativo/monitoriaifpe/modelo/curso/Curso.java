package com.softwarecorporativo.monitoriaifpe.modelo.curso;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.ComponenteCurricular;
import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.modelo.util.constantes.Grau;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Edmilson Santana
 */
@Entity
@Table(name = "tb_curso")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "id_curso"))})
@Access(AccessType.FIELD)
public class Curso extends EntidadeNegocio {

    private static final long serialVersionUID = -7352251272569804380L;

    @NotBlank
    @Size(min = 1, max = 100)
    @Pattern(regexp = "^[A-Z]{1}\\D+$", message = "{com.softwarecorporativo.monitoriaifpe.curso.descricao}")
    @Column(name = "txt_descricao", nullable = false)
    private String descricao;

    @NotNull
    @Column(name = "txt_grau", nullable = false)
    @Enumerated(EnumType.STRING)
    private Grau grau;

    @NotBlank
    @Pattern(regexp = "^[A-Z][0-9]$", message = "{com.softwarecorporativo.monitoriaifpe.curso.codigoCurso}")
    @Column(name = "txt_codigo", nullable = false, unique = true)
    private String codigoCurso;

    @NotBlank
    @Pattern(regexp = "^[A-Z]{2}$", message = "{com.softwarecorporativo.monitoriaifpe.curso.codigoCampus}")
    @Column(name = "txt_codigo_campus", nullable = false)
    private String codigoCampus;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ComponenteCurricular> componentesCurriculares;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Aluno> alunos;

    public ComponenteCurricular getComponenteCurricular(int index) {
        if (this.componentesCurriculares == null) {
            this.componentesCurriculares = new ArrayList<>();
        }
        return componentesCurriculares.get(index);
    }

    public void addComponenteCurricular(ComponenteCurricular componenteCurricular) {
        if (this.componentesCurriculares == null) {
            this.componentesCurriculares = new ArrayList<>();
        }
        componenteCurricular.setCurso(this);
        this.componentesCurriculares.add(componenteCurricular);
    }

    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

    public Aluno getAluno(int index) {
        if (this.alunos == null) {
            this.alunos = new ArrayList<>();
        }
        return alunos.get(index);
    }

    public void addAluno(Aluno aluno) {
        if (this.alunos == null) {
            this.alunos = new ArrayList<>();
        }
        aluno.setCurso(this);
        this.alunos.add(aluno);
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getCodigoCampus() {
        return codigoCampus;
    }

    public void setCodigoCampus(String codigoCampus) {
        this.codigoCampus = codigoCampus;
    }

    public Grau getGrau() {
        return grau;
    }

    public void setGrau(Grau grau) {
        this.grau = grau;
    }
   
    @Override
    public boolean isInativo(){
        if(this.alunos.isEmpty() && 
                this.componentesCurriculares.isEmpty()){
            return Boolean.TRUE;
        }
       return Boolean.FALSE;
    }
}
