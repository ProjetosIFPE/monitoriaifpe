
package br.com.projetoperiodo.model.instituto.aluno.controller.impl;

import java.util.Calendar;
import java.util.HashMap;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.instituto.aluno.controller.ControladorAluno;
import br.com.projetoperiodo.model.instituto.aluno.impl.AlunoImpl;
import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
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

	public ControladorAlunoImpl() {

	}

	@Override
	public EntidadeNegocio criarEntidadeNegocio() {

		return new AlunoImpl();
	}

	@Override
	public Aluno cadastrarAluno(Aluno aluno) throws NegocioException {
		try {
			validarCadastroDeAluno(aluno);
			String senhaCriptografada = Util.criptografarSenha(aluno.getSenha(), aluno.getSenha(), Constantes.CONSTANTE_CRIPTOGRAFIA);
			aluno.setSenha(senhaCriptografada);
			aluno.setUltimaAlteracao(Calendar.getInstance().getTime());
			Persistencia.getInstance().salvarAluno(aluno);
			return aluno;
		} catch (NegocioException e ) {
			throw e;
		}
		

	}

	public boolean verificarCadastroPorMatricula(Aluno aluno) {

		boolean cadastrado = Boolean.TRUE;
		Long quantidade = Persistencia.getInstance().buscarQuantidadeDeAlunos(aluno.getMatricula());
		if (quantidade == 0L) {
			cadastrado = Boolean.FALSE;
		}
		return cadastrado;
	}

	public void validarCadastroDeAluno(Aluno aluno) throws NegocioException {

		ControladorUsuario controladorUsuario = Fachada.getInstance().getControladorUsuario();
		boolean loginCadastrado = controladorUsuario.verificarCadastroDeUsuarioPorLogin(aluno);
		boolean matriculaCadastrada = this.verificarCadastroPorMatricula(aluno);
		boolean emailCadastrado = controladorUsuario.verificarCadastroDeUsuarioPorEmail(aluno);
		if (loginCadastrado || matriculaCadastrada || emailCadastrado) {
			HashMap<String, Object> camposInvalidos = new HashMap<>();
			camposInvalidos.put("login", String.valueOf(loginCadastrado));
			camposInvalidos.put("matricula", String.valueOf(matriculaCadastrada));
			camposInvalidos.put("email", String.valueOf(emailCadastrado));
			throw new NegocioException(Constantes.CAMPOS_INVALIDOS, camposInvalidos);
		}
	}

	@Override
	public Aluno buscarUsuarioAluno(Usuario usuario) {

		return (Aluno) Persistencia.getInstance().buscarAluno(usuario.getChavePrimaria());
	}
	@Override
	public void adicionarDisciplinaEmCadastroDeAluno(Aluno aluno, Disciplina disciplina){
		aluno.setDisciplinas(disciplina);
		Persistencia.getInstance().atualizarCadastroDeAluno(aluno);
	}

	@Override
	public Aluno buscarAluno(String matricula) {

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
		if (quantidade.longValue() == 0L) {
			isAluno = Boolean.FALSE;
		}
		return isAluno;
	}

}
