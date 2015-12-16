
package br.com.projetoperiodo.model.instituto.aluno.controller.impl;

import java.util.Calendar;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.instituto.aluno.controller.ControladorAluno;
import br.com.projetoperiodo.model.instituto.aluno.impl.AlunoImpl;
import br.com.projetoperiodo.model.negocio.controlador.ControladorNegocioImpl;
import br.com.projetoperiodo.model.negocio.entidade.EntidadeNegocio;
import br.com.projetoperiodo.model.usuario.Usuario;
import br.com.projetoperiodo.model.usuario.controller.ControladorUsuario;
import br.com.projetoperiodo.util.Util;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.exception.NegocioException;
import br.com.projetoperiodo.util.fachada.Fachada;
import br.com.projetoperiodo.util.fachada.Persistencia;

public class ControladorAlunoImpl extends ControladorNegocioImpl implements ControladorAluno {


	private final String MENSAGEM_CADASTRO_INVALIDO = "Aluno já está cadastrado";
	
	public ControladorAlunoImpl() {
	
	}
	@Override
	public EntidadeNegocio criarEntidadeNegocio() {

		return new AlunoImpl();
	}

	@Override
	public Aluno cadastrarAluno(Aluno aluno) throws NegocioException {
		ControladorUsuario controladorUsuario = Fachada.getInstance().getControladorUsuario();
		boolean cadastrado = controladorUsuario.verificarCadastroDeUsuario(aluno);
		if ( !cadastrado ) {
			String senhaCriptografada = Util.criptografarSenha(
							aluno.getSenha(), aluno.getSenha(), Constantes.CONSTANTE_CRIPTOGRAFIA);
			aluno.setSenha(senhaCriptografada);
			aluno.setUltimaAlteracao(Calendar.getInstance().getTime());
			Persistencia.getInstance().salvarAluno(aluno);
			return aluno;
		}
		throw new NegocioException(MENSAGEM_CADASTRO_INVALIDO);
	}
	@Override
	public Aluno buscarUsuarioAluno(Usuario usuario) {
		return (Aluno) Persistencia.getInstance().buscarAluno(usuario.getChavePrimaria());
	}
	
	@Override
	public Aluno buscarAluno(String matricula){
		return (Aluno) Persistencia.getInstance().buscarAluno(matricula);
	}
	
	@Override
	public String getNomeClasseEntidade() {
		
		return AlunoImpl.class.getSimpleName();
	}
	
	
	@Override
	public boolean verificarPapelDeAlunoDoUsuario(Usuario usuario) {
		boolean isAluno = Boolean.TRUE;
		Long quantidade = Persistencia.getInstance().buscarQuantidadeDeAlunos(usuario.getChavePrimaria());
		if ( quantidade.longValue() == 0L ) {
			isAluno = Boolean.FALSE;
		}
		return isAluno;
	}

	
}
