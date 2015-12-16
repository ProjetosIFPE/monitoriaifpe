package br.com.projetoperiodo.model.usuario.dao;

import java.util.List;

import br.com.projetoperiodo.model.negocio.entidade.dao.EntidadeNegocioDao;
import br.com.projetoperiodo.model.usuario.Usuario;

public interface UsuarioDao extends EntidadeNegocioDao
{
	
	public Usuario salvar(Usuario usuario);
	
	public Usuario atualizar(Usuario usuario);
	
	public void remover(Usuario usuario);
	
	public List<Usuario> listar();
	
	public Usuario buscar(long l);

	Usuario buscar(String login);

	Long buscarCadastro(String login);
	
}
