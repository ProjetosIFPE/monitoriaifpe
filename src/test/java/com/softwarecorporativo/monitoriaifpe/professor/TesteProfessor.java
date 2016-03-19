/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.professor;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.impl.CursoImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.impl.DisciplinaImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.professor.impl.ProfessorImpl;
import com.softwarecorporativo.monitoriaifpe.util.Util;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Constantes;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Grau;
import java.util.List;
import javax.persistence.Query;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author douglas
 */
public class TesteProfessor extends MonitoriaTestCase {
    
    @Test
    public void testePersistProfessor() {
        
        Curso curso = montarObjetoCurso();
        super.entityManager.persist(curso);
        
        List<ProfessorImpl> lista_de_professores;
        Disciplina disciplina = montarObjetoDisciplina();
        disciplina.setCurso(curso);
        super.entityManager.persist(disciplina);
        
        lista_de_professores = quantidadeProfessores();
        assertNotNull(lista_de_professores);
        
        int valor_pre_cadastro = lista_de_professores.size();
        
        ProfessorImpl professor = montarObjetoProfessor();
        professor.setDisciplina(disciplina);
        
        super.entityManager.persist(professor);
        
        lista_de_professores = quantidadeProfessores();
        assertNotNull(lista_de_professores);
        
        int valor_pos_cadastro = lista_de_professores.size();
        
        assertEquals(valor_pre_cadastro + 1, valor_pos_cadastro);
        
    }
    
    private ProfessorImpl montarObjetoProfessor() {
        ProfessorImpl professor_criado = new ProfessorImpl();
        
        professor_criado.setNome("Marcos");
        professor_criado.setSobrenome("Costa");
        professor_criado.setEmail("marcos_andre@gmail.com");
        professor_criado.setLogin("MarcosCosta");
        String password = Util.criptografarSenha("senha", "ssenha", Constantes.CONSTANTE_CRIPTOGRAFIA);
        professor_criado.setSenha(password);
        
        return professor_criado;
    }
    
    private Disciplina montarObjetoDisciplina() {
        Disciplina disciplina = new DisciplinaImpl();
        disciplina.setDescricao("Desenvolvimento WEB");
        return disciplina;
    }
    
    private Curso montarObjetoCurso() {
        Curso curso = new CursoImpl();
        curso.setDescricao("TADS");
        curso.setModalidade(Grau.SUPERIOR);
        return curso;
    }
    
    private List<ProfessorImpl> quantidadeProfessores() {
        Query query = super.entityManager.createQuery(" select u from ProfessorImpl u ");
        List<ProfessorImpl> lista_professores = query.getResultList();
        
        return lista_professores;
    }
}
