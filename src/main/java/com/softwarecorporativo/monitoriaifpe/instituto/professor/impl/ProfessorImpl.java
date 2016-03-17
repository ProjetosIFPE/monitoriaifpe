package com.softwarecorporativo.monitoriaifpe.instituto.professor.impl;

import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.impl.DisciplinaImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.usuario.impl.UsuarioImpl;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PROFESSOR")
@PrimaryKeyJoinColumn(name = "PROFESSOR_ID")
public class ProfessorImpl extends UsuarioImpl implements Professor {

    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY, targetEntity = DisciplinaImpl.class)
    private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
    
    @Transient
    private final String PAPEL_PROFESSOR = "PROFESSOR";

    public ProfessorImpl() {
        PAPEL = PAPEL_PROFESSOR;
    }

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.professor.Professor#getDisciplina(int)
     */
    @Override
    public Disciplina getDisciplina(int index) {
        return disciplinas.get(index);
    }

    /* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.professor.Professor#setDisciplina(br.com.projetoperiodo.model.instituto.disciplina.Disciplina)
     */
    @Override
    public void setDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

    @Override
    public int getQuantidadeDisciplinasDoProfessor() {
        return disciplinas.size();
    }

}
