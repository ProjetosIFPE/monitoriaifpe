
package br.com.projetoperiodo.model.usuario.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.projetoperiodo.model.negocio.entidade.EntidadeNegocio;
import br.com.projetoperiodo.model.usuario.Usuario;
import br.com.projetoperiodo.util.exception.NegocioException;
import br.com.projetoperiodo.util.fachada.Fachada;

public class JDBCUsuarioDao implements UsuarioDao {

	private Connection connection;

	public JDBCUsuarioDao(Connection cn) {
		this.connection = cn;
	}

	@Override
	public EntidadeNegocio buscar(HashMap<String, Object> filter) throws NegocioException {

		StringBuilder consulta = new StringBuilder(" SELECT * FROM PROJETO_PERIODO.USUARIO ");
		String condicional = " WHERE ";
		boolean criteria = false;
		StringBuilder criterios = new StringBuilder();
		if ( filter.containsKey("email") ) {
			criteria = true;
			criterios.append( " USUARIO_EMAIL = ? ");
		} 
		if ( criteria ) {
			consulta.append(condicional).append(criterios.toString());
		}
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		Usuario usuario = null;
		try {

			ptmt = connection.prepareStatement(consulta.toString());
			if ( filter.containsKey("email") ){
				ptmt.setString(1, (String) filter.get("email"));
			}
			
			rs = ptmt.executeQuery();
			if (rs.next()) {
				// Verificar se pode usar a Fachada
				usuario = (Usuario) Fachada.getInstance().criarUsuario();
				usuario.setChavePrimaria(rs.getInt("USUARIO_ID"));
				usuario.setNome(rs.getString("USUARIO_NOME"));
				usuario.setSobrenome(rs.getString("USUARIO_SOBRENOME"));
				usuario.setSenha(rs.getString("USUARIO_SENHA"));
				usuario.setLogin(rs.getString("USUARIO_LOGIN"));
				usuario.setEmail(rs.getString("USUARIO_EMAIL"));
				usuario.setUltimaAlteracao(rs.getDate("ULTIMA_ALTERACAO"));
				usuario.setUltimoAcesso(rs.getDate("ULTIMO_ACESSO"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (ptmt != null) ptmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return usuario;
	}

	@Override
	public Usuario salvar(Usuario usuario) {

		StringBuilder builder = new StringBuilder();
		builder.append(" INSERT INTO PROJETO_PERIODO.USUARIO ");
		builder.append(" (USUARIO_NOME, USUARIO_SOBRENOME, USUARIO_LOGIN, USUARIO_SENHA, USUARIO_EMAIL, ULTIMO_ACESSO) ");
		builder.append(" VALUES(?, ?, ?, ? ,?, ?) ");
		PreparedStatement ptmt = null;
		ResultSet generatedKeys = null;
		try {

			ptmt = connection.prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
			ptmt.setString(1, usuario.getNome());
			ptmt.setString(2, usuario.getSobrenome());
			ptmt.setString(3, usuario.getLogin());
			ptmt.setString(4, usuario.getSenha());
			ptmt.setString(5, usuario.getEmail());
			ptmt.setDate(6, (Date) usuario.getUltimoAcesso());
			ptmt.executeUpdate();
			connection.commit();
			generatedKeys = ptmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				usuario.setChavePrimaria(generatedKeys.getLong(1));
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ee) {
				throw new RuntimeException(ee);
			}
		} finally {
			try {
				// TODO verificar se é null
				ptmt.close();
				generatedKeys.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return usuario;

	}

	@Override
	public Usuario atualizar(Usuario usuario) {

		StringBuilder builder = new StringBuilder();
		builder.append(" UPDATE PROJETO_PERIODO.USUARIO ");
		builder.append(" SET USUARIO_NOME = ?, USUARIO_SOBRENOME = ?, USUARIO_LOGIN = ?,  ");
		builder.append(" USUARIO_SENHA= ?, USUARIO_EMAIL = ?, ULTIMO_ACESSO = ? ");
		builder.append(" WHERE USUARIO_ID = ? ");
		PreparedStatement ptmt = null;
		try {

			ptmt = connection.prepareStatement(builder.toString());
			ptmt.setString(1, usuario.getNome());
			ptmt.setString(2, usuario.getSobrenome());
			ptmt.setString(3, usuario.getLogin());
			ptmt.setString(4, usuario.getSenha());
			ptmt.setString(5, usuario.getEmail());
			ptmt.setDate(6, (Date) usuario.getUltimoAcesso());
			ptmt.setLong(7, usuario.getChavePrimaria());
			ptmt.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ee) {
				throw new RuntimeException(ee);
			}
		} finally {
			try {
				if (ptmt != null) ptmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return usuario;

	}

	@Override
	public void remover(Usuario usuario) {

		StringBuilder builder = new StringBuilder();
		builder.append(" DELETE FROM PROJETO_PERIODO.USUARIO ");
		builder.append(" WHERE USUARIO_ID = ? ");
		PreparedStatement ptmt = null;
		try {

			ptmt = connection.prepareStatement(builder.toString());
			ptmt.setLong(1, usuario.getChavePrimaria());
			ptmt.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				// TODO Tratar erro para rollback
				connection.rollback();
			} catch (SQLException ee) {
				throw new RuntimeException(ee);
			}
		} finally {
			try {
				if (ptmt != null) ptmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}

	@Override
	public List<Usuario> listar() {

		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT * FROM PROJETO_PERIODO.USUARIO ");
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		Usuario usuario = null;
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try {

			ptmt = connection.prepareStatement(builder.toString());
			rs = ptmt.executeQuery();
			while (rs.next()) {
				// Verificar se pode usar a Fachada
				usuario = (Usuario) Fachada.getInstance().criarUsuario();
				usuario.setChavePrimaria(rs.getInt("USUARIO_ID"));
				usuario.setNome(rs.getString("USUARIO_NOME"));
				usuario.setSobrenome(rs.getString("USUARIO_SOBRENOME"));
				usuario.setSenha(rs.getString("USUARIO_SENHA"));
				usuario.setLogin(rs.getString("USUARIO_LOGIN"));
				usuario.setEmail(rs.getString("USUARIO_EMAIL"));
				usuario.setUltimaAlteracao(rs.getDate("ULTIMA_ALTERACAO"));
				usuario.setUltimoAcesso(rs.getDate("ULTIMO_ACESSO"));
				listaUsuarios.add(usuario);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (ptmt != null) ptmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return listaUsuarios;
	}

	@Override
	public Usuario buscar(long chavePrimaria) {

		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT * FROM PROJETO_PERIODO.USUARIO ");
		builder.append(" WHERE USUARIO_ID = ? ");
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		Usuario usuario = null;
		try {

			ptmt = connection.prepareStatement(builder.toString());
			ptmt.setLong(1, chavePrimaria);
			rs = ptmt.executeQuery();
			if (rs.next()) {
				// Verificar se pode usar a Fachada
				usuario = (Usuario) Fachada.getInstance().criarUsuario();
				usuario.setChavePrimaria(rs.getInt("USUARIO_ID"));
				usuario.setNome(rs.getString("USUARIO_NOME"));
				usuario.setSobrenome(rs.getString("USUARIO_SOBRENOME"));
				usuario.setSenha(rs.getString("USUARIO_SENHA"));
				usuario.setLogin(rs.getString("USUARIO_LOGIN"));
				usuario.setEmail(rs.getString("USUARIO_EMAIL"));
				usuario.setUltimaAlteracao(rs.getDate("ULTIMA_ALTERACAO"));
				usuario.setUltimoAcesso(rs.getDate("ULTIMO_ACESSO"));

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (ptmt != null) ptmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return usuario;
	}

	@Override
	public Usuario buscar(String login) {

		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT * FROM PROJETO_PERIODO.USUARIO ");
		builder.append(" WHERE USUARIO_LOGIN = ? ");
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		Usuario usuario = null;
		try {

			ptmt = connection.prepareStatement(builder.toString());
			ptmt.setString(1, login);
			rs = ptmt.executeQuery();
			if (rs.next()) {
				// Verificar se pode usar a Fachada
				usuario = (Usuario) Fachada.getInstance().criarUsuario();
				usuario.setChavePrimaria(rs.getInt("USUARIO_ID"));
				usuario.setNome(rs.getString("USUARIO_NOME"));
				usuario.setSobrenome(rs.getString("USUARIO_SOBRENOME"));
				usuario.setSenha(rs.getString("USUARIO_SENHA"));
				usuario.setLogin(rs.getString("USUARIO_LOGIN"));
				usuario.setEmail(rs.getString("USUARIO_EMAIL"));
				usuario.setUltimaAlteracao(rs.getDate("ULTIMA_ALTERACAO"));
				usuario.setUltimoAcesso(rs.getDate("ULTIMO_ACESSO"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (ptmt != null) ptmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return usuario;
	}

	@Override
	public Long buscarCadastroPorEmail(String login) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long buscarCadastroPorLogin(String login) {

		// TODO Auto-generated method stub
		return null;
	}


}
