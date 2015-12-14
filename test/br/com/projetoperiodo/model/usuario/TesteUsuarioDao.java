package br.com.projetoperiodo.model.usuario;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.projetoperiodo.model.usuario.controller.ControladorUsuario;
import br.com.projetoperiodo.model.usuario.dao.JPAUsuarioDao;
import br.com.projetoperiodo.model.usuario.dao.UsuarioDao;
import br.com.projetoperiodo.util.Fachada;
import br.com.projetoperiodo.util.Util;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.persistencia.CreatorFabrica;
import br.com.projetoperiodo.util.persistencia.jpa.JPAUtil;


public class TesteUsuarioDao {

	
	private static UsuarioDao dao = CreatorFabrica.
					createFactory(CreatorFabrica.FABRICA_JPA).criarUsuarioDAO();
	private ControladorUsuario controlador = Fachada.getInstance().getControladorUsuario();
	
	@BeforeClass
	public static void setUp() {
		dao = new JPAUsuarioDao();
	}
	
	@Test
	public void testeInserirUsuario() {
		
	
	}
	
	@Test
	public void testeRemoverUsuario() {
		Usuario usuario = montarObjetoUsuario();
		dao.salvar(usuario);
		int qtdInicio = dao.listar().size();
		dao.remover(usuario);
		int qtdFim = dao.listar().size();
		Assert.assertEquals(qtdInicio - 1, qtdFim );
	}
	
	@Test
	public void testeAtualizarUsuario() {
	
		Usuario usuarioInserido = montarObjetoUsuario();
		dao.salvar(usuarioInserido);
		
		Usuario usuarioPesquisado = dao.buscar(usuarioInserido.getChavePrimaria());
		String senhaAntesAlteracao = usuarioPesquisado.getSenha();
		String novaSenha = "admin321";
		usuarioPesquisado.setSenha(Util.criptografarSenha(novaSenha, novaSenha, Constantes.CONSTANTE_CRIPTOGRAFIA));
		dao.atualizar(usuarioPesquisado);
		String senhaPosAlteracao = dao.buscar(usuarioPesquisado.getChavePrimaria()).getSenha();
		Assert.assertNotNull(senhaPosAlteracao);
		Assert.assertNotEquals(senhaAntesAlteracao, senhaPosAlteracao); 
	}
	
	@AfterClass
	public static void tearDown() {
		JPAUtil.getInstance().destroyInstance();
	}
	
	public Usuario montarObjetoUsuario() {
		Usuario usuario = (Usuario) controlador.criarEntidadeNegocio();
		usuario.setNome("admin");
		usuario.setLogin("admin");
		usuario.setEmail("admin@email.com");
		String senha = "admin";
		usuario.setSenha(Util.criptografarSenha(senha, senha, Constantes.CONSTANTE_CRIPTOGRAFIA));
		usuario.setUltimaAlteracao(new Date());
		usuario.setUltimoAcesso(new Date());
		return usuario;
	}
}
