package br.com.projetoperiodo.model.instituto.monitor.dao;

import java.util.List;

import br.com.projetoperiodo.model.instituto.monitor.Monitoria;

public interface MonitoriaDao{

	Monitoria salvar(Monitoria monitor);

	void atualizar(Monitoria monitor);

	void remover(Monitoria monitor);

	Monitoria buscar(long l);

	List<Monitoria> buscarMonitoriasDeDisciplina(long chave);

	List<Monitoria> listar();

	List<Monitoria> buscarMonitoriasDeAluno(long chave);

	Long buscarQuantidadeMonitoriasDeAluno(long chave);

	List<Monitoria> buscarMonitoriaCadastrada(Monitoria monitoria);

	List<Monitoria> buscarMonitoriasDeDisciplinaDeUmPeriodo(long chaveMonitoria, long chavePeriodo);
	

}
