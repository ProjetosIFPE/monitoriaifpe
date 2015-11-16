
package br.com.projetoperiodo.model.usuario.controller.impl;

import java.util.Calendar;
import java.util.HashMap;

import br.com.projetoperiodo.model.negocio.controlador.ControladorNegocioImpl;
import br.com.projetoperiodo.model.negocio.entidade.EntidadeNegocio;
import br.com.projetoperiodo.model.usuario.Usuario;
import br.com.projetoperiodo.model.usuario.controller.ControladorUsuario;
import br.com.projetoperiodo.model.usuario.impl.UsuarioImpl;
import br.com.projetoperiodo.util.Util;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.exception.NegocioException;
import br.com.projetoperiodo.util.persistencia.CreatorFabrica;

public class ControladorUsuarioImpl extends ControladorNegocioImpl implements ControladorUsuario {

	private final String EMAIL_ASSUNTO = "Senha Sistema de Monitoria TADS";

	private final String EMAIL_MENSAGEM_CONTEUDO = "Sua senha é: ";

	public ControladorUsuarioImpl() {

	}

	@Override
	public Usuario autenticarUsuario(Usuario usuario) throws NegocioException {

		String senha = usuario.getSenha();
		Usuario usuarioAutenticado = CreatorFabrica.getFabricaDAO().criarUsuarioDAO().buscar(usuario.getLogin());
		if (usuarioAutenticado != null) {
			String senhaCriptografada = Util.criptografarSenha(senha, senha, Constantes.CONSTANTE_CRIPTOGRAFIA);
			if (!usuarioAutenticado.getSenha().equals(senhaCriptografada)) {
				throw new NegocioException(Constantes.ERRO_ACESSO_NEGADO);
			}
			usuarioAutenticado.setUltimoAcesso(Calendar.getInstance().getTime());
			return usuarioAutenticado;
		} else {
			throw new NegocioException(Constantes.ERRO_ACESSO_NEGADO);
		}

	}

	@Override
	public Usuario alterarSenha(Usuario usuario, String senhaAntiga, String senhaNova) {

		try {

			senhaAntiga.equals(senhaNova);
			String senhaNovaCriptografada = Util.criptografarSenha(senhaNova, senhaNova, Constantes.CONSTANTE_CRIPTOGRAFIA);
			usuario.setSenha(senhaNovaCriptografada);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return CreatorFabrica.getFabricaDAO().criarUsuarioDAO().atualizar(usuario);
	}

	@Override
	public void envioEmailSenha(Usuario usuario) throws NegocioException {

		String novaSenha = usuario.getSenha();
		novaSenha = EMAIL_MENSAGEM_CONTEUDO.concat(novaSenha);
		Util.enviarEmail(usuario.getEmail(), EMAIL_ASSUNTO, novaSenha);
	}

	@Override
	public void alterarSenhaUsuario(Usuario usuario) throws NegocioException {

		HashMap<String, Object> filter = new HashMap<>();
		filter.put(Usuario.ATRIBUTO_USUARIO_EMAIL, usuario.getEmail());
		try {
			usuario = (Usuario) CreatorFabrica.getFabricaDAO().criarUsuarioDAO().buscar(filter);
			String novaSenha = Util.gerarSenhaAleatoria();
			String senhaCriptografada = Util.criptografarSenha(novaSenha, novaSenha, Constantes.CONSTANTE_CRIPTOGRAFIA);
			usuario.setSenha(senhaCriptografada);
			usuario.setSenhaExpirada(Boolean.TRUE);
			CreatorFabrica.getFabricaDAO().criarUsuarioDAO().atualizar(usuario);
			usuario.setSenha(novaSenha);
			envioEmailSenha(usuario);
		} catch (NegocioException e) {
			throw new NegocioException(EMAIL_NAO_CADASTRADO);
		}
	}

	@Override
	public boolean validarLogon(Usuario usuario) {

		Usuario usuarioLogado = CreatorFabrica.getFabricaDAO().criarUsuarioDAO().buscar(usuario.getNome());
		if (usuarioLogado.isSenhaExpirada()) {

		}
		return Boolean.TRUE;
	}

	@Override
	public EntidadeNegocio criarEntidadeNegocio() {

		return new UsuarioImpl();
	}

	@Override
	public Usuario cadastrarUsuario(Usuario usuario) {

		String senhaCriptografada = Util.criptografarSenha(usuario.getSenha(), usuario.getSenha(), Constantes.CONSTANTE_CRIPTOGRAFIA);
		usuario.setSenha(senhaCriptografada);
		CreatorFabrica.getFabricaDAO().criarUsuarioDAO().salvar(usuario);
		return usuario;
	}

	@Override
	public Usuario verificarExistenciaUsuario(Usuario usuario) {

		Usuario usuarioRequerente = CreatorFabrica.getFabricaDAO().criarUsuarioDAO().buscar(usuario.getLogin());
		return usuarioRequerente;
	}

	@Override
	public String getNomeClasseEntidade() {

		return UsuarioImpl.class.getSimpleName();
	}

	public boolean compararSenhas(String senha, String senhaCriptografada) {
		boolean isEqual = Boolean.TRUE;
		senha = Util.criptografarSenha(senha, senha, Constantes.CONSTANTE_CRIPTOGRAFIA);
		if ( !senha.equals(senhaCriptografada) ) {
			isEqual = Boolean.FALSE;
		}
		return isEqual;
	}

	@Override
	public Usuario alterarSenha(Usuario usuario) {

		// TODO Auto-generated method stub
		return null;
	}

}
