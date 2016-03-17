/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.relatorio;

import com.softwarecorporativo.monitoriaifpe.MonitoriaTestCase;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.impl.AlunoImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.impl.CursoImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.impl.DisciplinaImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.instituto.monitoria.impl.MonitoriaImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.instituto.periodo.impl.PeriodoImpl;
import com.softwarecorporativo.monitoriaifpe.relatorio.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.relatorio.atividade.impl.AtividadeImpl;
import com.softwarecorporativo.monitoriaifpe.relatorio.frequencia.RelatorioFrequencia;
import com.softwarecorporativo.monitoriaifpe.relatorio.frequencia.impl.RelatorioFrequenciaImpl;
import com.softwarecorporativo.monitoriaifpe.relatorio.semana.Semana;
import com.softwarecorporativo.monitoriaifpe.relatorio.semana.impl.SemanaImpl;
import com.softwarecorporativo.monitoriaifpe.util.Util;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Constantes;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Grau;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Modalidade;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Semestre;
import com.softwarecorporativo.monitoriaifpe.util.constantes.Situacao;
import java.util.Calendar;
import java.util.Locale;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author EdmilsonS
 */
public class TesteRelatorioFrequencia extends MonitoriaTestCase {

    @Test
    public void testeCadastrarRelatorioFrequencia() {
        
        super.entityManager.getTransaction().begin();
        RelatorioFrequencia relatorio = montarObjetoRelatorioFrequencia();
        relatorio = this.prepararCenarioInsercao(relatorio);
        super.entityManager.persist(relatorio);
 
        super.entityManager.getTransaction().commit();

        assertTrue(relatorio.getChavePrimaria() > 0);
        
        RelatorioFrequencia relatorioObtido = super.entityManager
                .find(relatorio.getClass(), relatorio.getChavePrimaria());
        
        int index = 0;
        
        assertEquals(relatorio.getChavePrimaria(),relatorioObtido.getChavePrimaria());
        assertEquals(relatorio.getSemana(index), relatorioObtido.getSemana(index));
        assertEquals(relatorio.getSemana(index).getAtividade(index), relatorioObtido.getSemana(index).getAtividade(index));
    }
    
    private RelatorioFrequencia prepararCenarioInsercao(RelatorioFrequencia relatorio) {
        
        Monitoria monitoria = relatorio.getMonitoria();
        Disciplina disciplina = monitoria.getDisciplina();
        Periodo periodo = monitoria.getPeriodo();
        Aluno aluno = monitoria.getAluno();
        Curso curso = aluno.getCurso();

        super.entityManager.persist(curso);
        super.entityManager.persist(periodo);

        aluno.setCurso(curso);
        disciplina.setCurso(curso);

        super.entityManager.persist(aluno);
        super.entityManager.persist(disciplina);
        super.entityManager.persist(monitoria);
        return relatorio;
    }

    private RelatorioFrequencia montarObjetoRelatorioFrequencia() {
        RelatorioFrequencia relatorio = new RelatorioFrequenciaImpl();
        relatorio.setMes(01);
        relatorio.setSituacao(Situacao.APROVADO);
        relatorio.setSemanas(montarObjetoSemana());
        relatorio.setMonitoria(montarObjetoMonitoria());
        return relatorio;
    }

    private Semana montarObjetoSemana() {
        Semana semana = new SemanaImpl();
        semana.setDescricao("Semana de Teste");
        semana.setObservacoes("Observações de Teste");
        semana.setAtividades(montarObjetoAtividade());
        return semana;
    }

    private Atividade montarObjetoAtividade() {
        Atividade atividade = new AtividadeImpl();
        atividade.setData(Calendar.getInstance().getTime());
        atividade.setHorarioEntrada("14:00");
        atividade.setHorarioSaida("18:00");
        return atividade;
    }

    private Monitoria montarObjetoMonitoria() {
        Monitoria monitoria = new MonitoriaImpl();
        monitoria.setAluno(montarObjetoAluno());
        monitoria.setDisciplina(montarObjetoDisciplina());
        monitoria.setModalidade(Modalidade.BOLSISTA);
        monitoria.setPeriodo(montarObjetoPeriodo());
        return monitoria;
    }

    private Aluno montarObjetoAluno() {
        Aluno aluno = new AlunoImpl();
        String senha = "alunoteste";
        aluno.setNome("Aluno");
        aluno.setSobrenome("Teste");
        aluno.setLogin("AlunoTeste");
        aluno.setMatricula("00001Y6-AA0000");
        aluno.setEmail("aluno_teste@gmail.com");
        aluno.setSenha(Util.criptografarSenha(senha, senha, Constantes.CONSTANTE_CRIPTOGRAFIA));
        return aluno;
    }

    private Periodo montarObjetoPeriodo() {
        Periodo periodo = new PeriodoImpl();
        periodo.setAno(2015);
        periodo.setSemestre(Semestre.SEGUNDO);
        return periodo;
    }

    private Disciplina montarObjetoDisciplina() {
        Disciplina disciplina = new DisciplinaImpl();
        disciplina.setDescricao("Desenvolvimento de Software Corporativo");
        return disciplina;
    }

    private Curso montarObjetoCurso() {
        Curso curso = new CursoImpl();
        curso.setDescricao("Análise de Sistemas");
        curso.setModalidade(Grau.SUPERIOR);
        return curso;
    }

}
