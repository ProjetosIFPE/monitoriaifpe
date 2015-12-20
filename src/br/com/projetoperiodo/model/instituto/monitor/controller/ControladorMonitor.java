
package br.com.projetoperiodo.model.instituto.monitor.controller;

import java.util.List;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.model.instituto.monitor.Monitoria;
import br.com.projetoperiodo.model.negocio.controlador.ControladorNegocio;
import br.com.projetoperiodo.util.constantes.enumeracoes.Modalidade;
import br.com.projetoperiodo.util.exception.NegocioException;

public interface ControladorMonitor extends ControladorNegocio
{
	
	Monitoria criarMonitoriaDeAluno(Aluno aluno, Disciplina disciplina, Modalidade modalidade);
	
	Monitoria buscarMonitoriasDeAluno(Aluno aluno) throws NegocioException;
	
	boolean validarCadastroMonitoria(Monitoria monitor);
	
	Monitoria cadastrarMonitoria(Monitoria monitor);
	
	List<Monitoria> listarMonitorias(Aluno aluno);

	boolean verificaExistenciaCadastroMonitoria(Monitoria monitor);

	Monitoria buscarMonitoria(long chavePrimaria) throws NegocioException;

	void removerMonitoria(long chavePrimaria);

	List<Monitoria> buscarMonitoriasDeDiscplina(Disciplina disciplina);

}