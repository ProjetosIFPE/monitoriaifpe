
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
import br.com.projetoperiodo.util.fachada.Persistencia;

public class ControladorUsuarioImpl extends ControladorNegocioImpl implements ControladorUsuario {

	private final String EMAIL_ASSUNTO = "Senha Sistema de Monitoria TADS";

	private final String EMAIL_MENSAGEM_CONTEUDO = "Sua senha é: ";

	private final String USUARIO_NAO_CADASTRADO = "Usuário não cadastrado";

	private final String SENHA_INVALIDA = "Senha inválida";

	public ControladorUsuarioImpl() {
		super();
	}

	@Override
	public Usuario autenticarUsuario(Usuario usuario) throws NegocioException {

		Usuario usuarioAutenticado;
		usuarioAutenticado = (Usuario) this.buscarCadastroDeUsuario(usuario);
		String senha = usuario.getSenha();
		String senhaCriptografada = Util.criptografarSenha(senha, senha, Constantes.CONSTANTE_CRIPTOGRAFIA);
		if (!usuarioAutenticado.getSenha().equals(senhaCriptografada)) {
			throw new NegocioException(SENHA_INVALIDA);
		}
		usuarioAutenticado.setUltimoAcesso(Calendar.getInstance().getTime());
		return usuarioAutenticado;

	}

	@Override
	public Usuario alterarSenha(Usuario usuario, String senhaNova) {

		String senhaNovaCriptografada = Util.criptografarSenha(senhaNova, senhaNova, Constantes.CONSTANTE_CRIPTOGRAFIA);
		usuario.setSenha(senhaNovaCriptografada);
		this.atualizarCadastroDeUsuario(usuario);
		return usuario;
	}

	@Override
	public void envioEmailSenha(Usuario usuario) throws NegocioException {

		String novaSenha = usuario.getSenha();
		novaSenha = EMAIL_MENSAGEM_CONTEUDO.concat(novaSenha);
		Util.enviarEmail(usuario.getEmail(), EMAIL_ASSUNTO, novaSenha);
	}

	@Override
	public void encaminharSenhaParaUsuario(Usuario usuario) throws NegocioException {

		HashMap<String, Object> filter = new HashMap<>();
		filter.put("email", usuario.getEmail());
		try {
			String novaSenha = Util.gerarSenhaAleatoria();
			alterarSenha(usuario, novaSenha);
			usuario.setSenha(novaSenha);
			envioEmailSenha(usuario);
		} catch (NegocioException e) {
			throw new NegocioException(e);
		}
	}

	@Override
	public EntidadeNegocio criarEntidadeNegocio() {

		return new UsuarioImpl();
	}

	@Override
	public Usuario cadastrarUsuario(Usuario usuario) {

		String senhaCriptografada = Util.criptografarSenha(usuario.getSenha(), usuario.getSenha(), Constantes.CONSTANTE_CRIPTOGRAFIA);
		usuario.setSenha(senhaCriptografada);
		Persistencia.getInstance().salvarUsuario(usuario);
		return usuario;
	}

	@Override
	public void atualizarCadastroDeUsuario(Usuario usuario) {

		Persistencia.getInstance().atualizarUsuario(usuario);
	}

	@Override
	public Usuario buscarCadastroDeUsuario(Usuario usuario) throws NegocioException {

		try {
			Usuario usuarioRequerente = (Usuario) Persistencia.getInstance().buscarUsuario(usuario.getLogin());
			return usuarioRequerente;
		} catch (NegocioException e) {
			throw new NegocioException(USUARIO_NAO_CADASTRADO);
		}
		
	}

	@Override
	public boolean verificarCadastroDeUsuarioPorLogin(Usuario usuario) {

		boolean cadastrado = Boolean.TRUE;
		Long quantidade = Persistencia.getInstance().buscarQuantidadeDeUsuariosPorLogin(usuario.getLogin());
		if (quantidade == 0L) {
			cadastrado = Boolean.FALSE;
		}
		return cadastrado;
	}

	@Override
	public boolean verificarCadastroDeUsuarioPorEmail(Usuario usuario) {

		boolean cadastrado = Boolean.TRUE;
		Long quantidade = Persistencia.getInstance().buscarQuantidadeDeUsuariosPorEmail(usuario.getEmail());
		if (quantidade == 0L) {
			cadastrado = Boolean.FALSE;
		}
		return cadastrado;
	}

	@Override
	public String getNomeClasseEntidade() {

		return UsuarioImpl.class.getSimpleName();
	}

	@Override
	public boolean compararSenhas(String senha, Usuario usuario) {

		boolean isEqual = Boolean.TRUE;
		senha = Util.criptografarSenha(senha, senha, Constantes.CONSTANTE_CRIPTOGRAFIA);
		if (!senha.equals(usuario.getSenha())) {
			isEqual = Boolean.FALSE;
		}
		return isEqual;
	}

	@Override
	public void alterarConfiguracoesDePersistencia(String estrategia, String banco) {

		Persistencia.getInstance().alterarFabrica(estrategia, banco);
	}

}
