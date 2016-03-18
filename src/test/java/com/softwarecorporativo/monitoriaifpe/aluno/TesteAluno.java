/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.aluno;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.impl.AlunoImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.impl.CursoImpl;
import com.softwarecorporativo.monitoriaifpe.util.Util;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Constantes;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Grau;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author douglas
 */
public class TesteAluno extends MonitoriaTestCase {

    @Test
    public void testePersistAluno() {
        super.entityManager.getTransaction().begin();
        List<AlunoImpl> lista_de_alunos;
        Curso curso = montarObjetoCurso();
        super.entityManager.persist(curso);

        lista_de_alunos = quantidadeAlunos(super.entityManager);
        assertNotNull(lista_de_alunos);

        int valor_pre_cadastro = lista_de_alunos.size();

        AlunoImpl aluno = montarObjetoAluno();
        aluno.setCurso(curso);

        super.entityManager.persist(aluno);
        super.entityManager.getTransaction().commit();

        lista_de_alunos = quantidadeAlunos(super.entityManager);
        assertNotNull(lista_de_alunos);

        int valor_pos_cadastro = lista_de_alunos.size();

        assertEquals(valor_pre_cadastro + 1, valor_pos_cadastro);

    }

    private AlunoImpl montarObjetoAluno() {
        AlunoImpl aluno_criado = new AlunoImpl();
        aluno_criado.setNome("Douglas");
        aluno_criado.setSobrenome("Albuquerque");
        aluno_criado.setEmail("douglasalbuquerque@gmail.com");
        aluno_criado.setLogin("doug21");
        aluno_criado.setMatricula("20142Y6-RC2222");
        String password = Util.criptografarSenha("sport", "sport", Constantes.CONSTANTE_CRIPTOGRAFIA);
        aluno_criado.setSenha(password);

        return aluno_criado;
    }

    private List<AlunoImpl> quantidadeAlunos(EntityManager entity_manager) {
        Query query = entity_manager.createQuery(" select u from AlunoImpl u ");
        List<AlunoImpl> lista_alunos = query.getResultList();

        return lista_alunos;
    }

    private Curso montarObjetoCurso() {
        Curso curso = new CursoImpl();
        curso.setDescricao("Tec Edificacoes");
        curso.setModalidade(Grau.TECNICO);
        return curso;
    }

}
