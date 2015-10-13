package br.com.projetoperiodo.model.instituto.disciplina.dao;

import java.util.List;

import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.model.negocio.entidade.EntidadeNegocio;
import br.com.projetoperiodo.model.negocio.entidade.dao.EntidadeNegocioDao;

public interface DisciplinaDao extends EntidadeNegocioDao
{

	public void salvar(Disciplina disciplina);
	
	public void atualizar(Disciplina disciplina);
	
	public void remover(Disciplina disciplina);
	
	public List<Disciplina> listar(); 

	public Disciplina buscar(int primaryK);
	
}
