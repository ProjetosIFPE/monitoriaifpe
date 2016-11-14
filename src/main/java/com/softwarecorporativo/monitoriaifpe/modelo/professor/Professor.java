package com.softwarecorporativo.monitoriaifpe.modelo.professor;

import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.Turma;
import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tb_professor")
@PrimaryKeyJoinColumn(name = "id_professor")
@DiscriminatorValue(value = "P")
@Access(AccessType.FIELD)
@NamedQueries(value = {
    @NamedQuery(name = Professor.PROFESSOR_POR_SIAPE, query = "select p from Professor as p where p.siape = ?1"),
    @NamedQuery(name = Professor.COUNT_PROFESSOR_POR_SIAPE, query = "select count(p) from Professor as p where p.siape = ?1")})
public class Professor extends Usuario {

    private static final long serialVersionUID = -8880339274163624313L;

    public static final String PROFESSOR_POR_SIAPE = "professorPorSiape";
    public static final String COUNT_PROFESSOR_POR_SIAPE = "countProfessorPorSiape";

    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    private List<Turma> turmas;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    private Curso curso;

    @NotBlank
    @Column(name = "txt_siape")
    @Pattern(regexp="[0-9]{9}" , message = "SIAPE deve conter 9 digitos")
    private String siape;
    
    @Lob
    @Column(name = "assinatura")
    private byte[] assinatura;

    public Turma getTurma(int index) {
        if (this.turmas == null) {
            this.turmas = new ArrayList<>();
        }
        return turmas.get(index);
    }

    public void addTurma(Turma turma) {
        if (this.turmas == null) {
            this.turmas = new ArrayList<>();
        }
        turma.setProfessor(this);
        this.turmas.add(turma);
    }

    public int getQuantidadeTurmasDoProfessor() {
        return turmas.size();
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public byte[] getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(byte[] assinatura) {
        this.assinatura = assinatura;
    }

    
}
