package br.com.projetoperiodo.model.instituto.aluno.dao;

import java.util.List;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;

public interface AlunoDao 
{
	public Aluno salvar(Aluno aluno);
	
	public void atualizar(Aluno aluno);
	
	public void remover(Aluno aluno);
	
	public List<Aluno> listar(); 
	
	public Aluno buscar(long l);

	Aluno buscarPelaMatricula(String matricula);

	Long buscarQuantidadeDeAlunosPorChave(long chave);

	Long buscarQuantidadeDeAlunosPorMatricula(String matricula);
}
