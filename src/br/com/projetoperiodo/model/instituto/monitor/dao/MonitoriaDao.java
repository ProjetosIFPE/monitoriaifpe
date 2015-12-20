package br.com.projetoperiodo.model.instituto.monitor.dao;

import java.util.List;

import br.com.projetoperiodo.model.instituto.monitor.Monitoria;
import br.com.projetoperiodo.model.instituto.periodo.Periodo;

public interface MonitoriaDao{

	Monitoria salvar(Monitoria monitor);

	void atualizar(Monitoria monitor);

	void remover(Monitoria monitor);

	Monitoria buscar(long l);

	List<Monitoria> buscarMonitoriasDeDisciplina(long chave);

	List<Monitoria> listar();

	List<Monitoria> buscarMonitoriasDeAluno(long chave);

	List<Monitoria> buscarMonitoriaCadastrada(Monitoria monitoria);

	List<Monitoria> buscarMonitoriasDeDisciplinaDeUmPeriodo(long chaveMonitoria, long chavePeriodo);

	Long buscarQuantidadeMonitoriasDeAlunoEmUmPeriodo(Monitoria monitor);
	

}
