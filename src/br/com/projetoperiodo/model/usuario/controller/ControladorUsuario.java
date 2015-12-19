
package br.com.projetoperiodo.model.usuario.controller;

import br.com.projetoperiodo.model.negocio.controlador.ControladorNegocio;
import br.com.projetoperiodo.model.usuario.Usuario;
import br.com.projetoperiodo.util.exception.NegocioException;

public interface ControladorUsuario extends ControladorNegocio {

	Usuario autenticarUsuario(Usuario usuario) throws NegocioException;

	void envioEmailSenha(Usuario usuario) throws NegocioException;

	void encaminharSenhaParaUsuario(Usuario usuario) throws NegocioException;

	Usuario cadastrarUsuario(Usuario usuario);

	Usuario buscarCadastroDeUsuario(Usuario usuario);

	Usuario alterarSenha(Usuario usuario, String senhaNova);

	boolean compararSenhas(String senha, Usuario usuario);

	void alterarConfiguracoesDePersistencia(String estrategia, String banco);

	boolean verificarCadastroDeUsuarioPorLogin(Usuario usuario);
	
	boolean verificarCadastroDeUsuarioPorEmail(Usuario usuario);
}
