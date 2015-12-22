package br.com.projetoperiodo.model.instituto.professor.controller.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.instituto.disciplina.controller.ControladorDisciplina;
import br.com.projetoperiodo.model.instituto.professor.Professor;
import br.com.projetoperiodo.model.instituto.professor.controller.ControladorProfessor;
import br.com.projetoperiodo.model.instituto.professor.impl.ProfessorImpl;
import br.com.projetoperiodo.model.negocio.controlador.ControladorNegocioImpl;
import br.com.projetoperiodo.model.negocio.entidade.EntidadeNegocio;
import br.com.projetoperiodo.model.usuario.Usuario;
import br.com.projetoperiodo.model.usuario.controller.ControladorUsuario;
import br.com.projetoperiodo.util.Util;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.exception.NegocioException;
import br.com.projetoperiodo.util.fachada.Fachada;
import br.com.projetoperiodo.util.fachada.Persistencia;
import br.com.projetoperiodo.util.persistencia.fabrica.CreatorFabrica;

public class ControladorProfessorImpl extends ControladorNegocioImpl implements ControladorProfessor{

	/* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.professor.controller.ControladorProfessor#criarEntidadeNegocio()
	 */
	@Override
	public EntidadeNegocio criarEntidadeNegocio() {

		return new ProfessorImpl();
	}

	/* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.professor.controller.ControladorProfessor#getNomeClasseEntidade()
	 */
	
	
	
	@Override
	public String getNomeClasseEntidade() {

		return ProfessorImpl.class.getSimpleName();
	}
	
	@Override
	public boolean verificarPapelDeProfessorDoUsuario(Usuario usuario) {
		boolean isProfessor = Boolean.TRUE;
		
		Professor professor = (Professor) Persistencia.getInstance().buscarProfessor(usuario.getChavePrimaria());
		if ( Util.isNull(professor) ) {
			isProfessor = Boolean.FALSE;
		}
		return isProfessor;
	}

	@Override
	public Professor cadastrarProfessor(Professor professor) throws NegocioException {
		try {
			validarCadastroDeProfessor(professor);
			String senhaCriptografada = Util.criptografarSenha(professor.getSenha(),professor.getSenha(), Constantes.CONSTANTE_CRIPTOGRAFIA);
			professor.setSenha(senhaCriptografada);
			professor.setUltimaAlteracao(Calendar.getInstance().getTime());
			return (Professor) Persistencia.getInstance().salvarProfessor(professor);		
		} catch (NegocioException e ) {
			throw e;
		}
		
	}
	
	
	public void validarCadastroDeProfessor(Professor professor) throws NegocioException {

		ControladorUsuario controladorUsuario = Fachada.getInstance().getControladorUsuario();
		boolean loginCadastrado = controladorUsuario.verificarCadastroDeUsuarioPorLogin(professor);
		boolean emailCadastrado = controladorUsuario.verificarCadastroDeUsuarioPorEmail(professor);
		if (loginCadastrado || emailCadastrado) {
			HashMap<String, Object> camposInvalidos = new HashMap<>();
			camposInvalidos.put("login", Util.invalidarCampoCadastrado(loginCadastrado));
			camposInvalidos.put("email", Util.invalidarCampoCadastrado(emailCadastrado));
			throw new NegocioException(Constantes.CAMPOS_INVALIDOS, camposInvalidos);
		}
	}

	

}
