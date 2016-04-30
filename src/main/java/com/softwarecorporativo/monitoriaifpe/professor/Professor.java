package com.softwarecorporativo.monitoriaifpe.professor;

import com.softwarecorporativo.monitoriaifpe.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.usuario.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PROFESSOR")
@PrimaryKeyJoinColumn(name = "PROFESSOR_ID")
@DiscriminatorValue(value = "P")
@Access(AccessType.FIELD)
public class Professor extends Usuario  {

    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    private List<Disciplina> disciplinas;

    
    public Disciplina getDisciplina(int index) {
        if ( this.disciplinas == null ) {
            this.disciplinas = new ArrayList<>();
        }
        return disciplinas.get(index);
    }

    
    public void addDisciplina(Disciplina disciplina) {
        if ( this.disciplinas == null ) {
            this.disciplinas = new ArrayList<>();
        }
        disciplina.setProfessor(this);
        this.disciplinas.add(disciplina);
    }

    
    public int getQuantidadeDisciplinasDoProfessor() {
        return disciplinas.size();
    }

   

}
