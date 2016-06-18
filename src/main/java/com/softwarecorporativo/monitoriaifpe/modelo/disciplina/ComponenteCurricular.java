package com.softwarecorporativo.monitoriaifpe.modelo.disciplina;

import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "TB_COMPONENTE_CURRICULAR")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "COMPONENTE_CURRICULAR_ID"))})
@Access(AccessType.FIELD)
public class ComponenteCurricular extends EntidadeNegocio {

    private static final long serialVersionUID = -3079766681161299776L;

    @NotBlank
    @Column(name = "CODIGO_COMP_CURRICULAR", nullable = false, unique = true)
    private String codigoComponenteCurricular;

    @NotBlank
    @Size(min = 1, max = 150)
    @Pattern(regexp = "^[A-Z]{1}\\D+$", message = "{com.softwarecorporativo.monitoriaifpe.componenteCurricular.descricao}")
    @Column(name = "COMP_CURRICULAR_DS", nullable = false)
    private String descricao;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CURSO_ID", referencedColumnName = "CURSO_ID")
    private Curso curso;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componenteCurricular", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Disciplina> disciplinas;
    
    /* TODO: Construtor criado para facilitar a criação de objetos */ 
    public ComponenteCurricular(String codigoComponenteCurricular, String descricao){
        this.codigoComponenteCurricular = codigoComponenteCurricular;
        this.descricao = descricao;
    }
    
    public ComponenteCurricular(){
    }
    
    
    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

    public Curso getCurso() {

        return curso;
    }

    public void setCurso(Curso curso) {

        this.curso = curso;
    }

    public String getCodigoComponenteCurricular() {
        return codigoComponenteCurricular;
    }

    public void setCodigoComponenteCurricular(String codigoComponenteCurricular) {
        this.codigoComponenteCurricular = codigoComponenteCurricular;
    }
    
    public void addDisciplina(Disciplina disciplina) {
        if (this.disciplinas == null) {
            this.disciplinas = new ArrayList<>();
        }
        disciplina.setComponenteCurricular(this);
        this.disciplinas.add(disciplina);
    }
}
