
package br.com.projetoperiodo.model.relatorio.frequencia;

import br.com.projetoperiodo.model.instituto.monitor.Monitoria;
import br.com.projetoperiodo.model.negocio.entidade.EntidadeNegocio;
import br.com.projetoperiodo.model.relatorio.semana.Semana;
import br.com.projetoperiodo.util.constantes.enumeracoes.Situacao;

public interface RelatorioFrequencia extends EntidadeNegocio
{
	
	int getMes();

	void setMes(int mes);

	Monitoria getMonitor();

	void setMonitor(Monitoria monitor);

	Semana getSemana(int index);
	
	void setSemanas(Semana semana);

	Situacao getSituacao();

	void setSituacao(Situacao situacao);

	String getDescricaoMes();


}