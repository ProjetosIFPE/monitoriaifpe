
package br.com.projetoperiodo.model.instituto.periodo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.projetoperiodo.model.instituto.periodo.Periodo;
import br.com.projetoperiodo.util.constantes.enumeracoes.Semestre;
import br.com.projetoperiodo.util.fachada.Fachada;

public class JDBCPeriodoDao implements PeriodoDao {

	private Connection connection;

	public JDBCPeriodoDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Periodo salvar(Periodo periodo) {

		StringBuilder builder = new StringBuilder();
		builder.append(" INSERT INTO PROJETO_PERIODO.USUARIO ");
		builder.append(" (PERIODO_ANO, SEMESTRE) ");
		builder.append(" VALUES(?, ?) ");
		PreparedStatement ptmt = null;
		ResultSet generatedKeys = null;
		try {

			ptmt = connection.prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
			ptmt.setInt(1, periodo.getAno());
			ptmt.setString(2, periodo.getSemestre().name());
			ptmt.executeUpdate();
			connection.commit();
			generatedKeys = ptmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				periodo.setChavePrimaria(generatedKeys.getLong(1));
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ee) {
				throw new RuntimeException(ee);
			}
		} finally {
			try {
				if (ptmt != null) {
					ptmt.close();
				}
				if (generatedKeys != null) {
					generatedKeys.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return periodo;
	}

	@Override
	public void atualizar(Periodo periodo) {

		StringBuilder builder = new StringBuilder();
		builder.append(" UPDATE PROJETO_PERIODO.PERIODO ");
		builder.append(" SET PERIODO_ANO = ?, SEMESTRE = ? ");
		builder.append(" WHERE PERIODO_ID = ? ");
		PreparedStatement ptmt = null;
		try {

			ptmt = connection.prepareStatement(builder.toString());
			ptmt.setInt(1, periodo.getAno());
			ptmt.setString(2, periodo.getSemestre().name());
			ptmt.setLong(3, periodo.getChavePrimaria());
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
	}

	@Override
	public void remover(Periodo periodo) {

		StringBuilder builder = new StringBuilder();
		builder.append(" DELETE FROM PROJETO_PERIODO.PERIODO ");
		builder.append(" WHERE PERIODO_ID = ? ");
		PreparedStatement ptmt = null;
		try {

			ptmt = connection.prepareStatement(builder.toString());
			ptmt.setLong(1, periodo.getChavePrimaria());
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
	public Periodo buscar(long l) {

		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT * FROM PROJETO_PERIODO.PERIODO ");
		builder.append(" WHERE PERIODO_ID = ? ");
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		Periodo periodo = null;
		try {

			ptmt = connection.prepareStatement(builder.toString());
			rs = ptmt.executeQuery();
			if (rs.next()) {
				periodo = (Periodo) Fachada.getInstance().criarPeriodo();
				periodo.setChavePrimaria(rs.getInt("PERIODO_ID"));
				periodo.setAno(rs.getInt("PERIODO_ANO"));
				periodo.setSemestre(Semestre.valueOf(rs.getString("SEMESTRE")));
				periodo.setUltimaAlteracao(rs.getDate("ULTIMA_ALTERACAO"));
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
		return periodo;
	}

	@Override
	public List<Periodo> listar() {

		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT * FROM PROJETO_PERIODO.PERIODO ");
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		Periodo periodo = null;
		List<Periodo> listaPeriodos = new ArrayList<Periodo>();
		try {

			ptmt = connection.prepareStatement(builder.toString());
			rs = ptmt.executeQuery();
			while (rs.next()) {
				// Verificar se pode usar a Fachada
				periodo = (Periodo) Fachada.getInstance().criarPeriodo();
				periodo.setChavePrimaria(rs.getInt("PERIODO_ID"));
				periodo.setAno(rs.getInt("PERIODO_ANO"));
				periodo.setSemestre(Semestre.valueOf(rs.getString("SEMESTRE")));
				periodo.setUltimaAlteracao(rs.getDate("ULTIMA_ALTERACAO"));
				listaPeriodos.add(periodo);
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
		return listaPeriodos;
	}

	@Override
	public List<Periodo> buscarPeriodoPorAnoEsemestre(int ano, Semestre semestre) {

		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT * FROM PROJETO_PERIODO.PERIODO ");
		builder.append(" WHERE PERIODO_ANO = ? AND SEMESTRE = ?");
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		Periodo periodo = null;
		List<Periodo> listaPeriodos = new ArrayList<Periodo>();
		try {

			ptmt = connection.prepareStatement(builder.toString());
			ptmt.setInt(1, ano);
			ptmt.setString(2, semestre.name());
			rs = ptmt.executeQuery();
			while (rs.next()) {
				// Verificar se pode usar a Fachada
				periodo = (Periodo) Fachada.getInstance().criarPeriodo();
				periodo.setChavePrimaria(rs.getInt("PERIODO_ID"));
				periodo.setAno(rs.getInt("PERIODO_ANO"));
				periodo.setSemestre(Semestre.valueOf(rs.getString("SEMESTRE")));
				periodo.setUltimaAlteracao(rs.getDate("ULTIMA_ALTERACAO"));
				listaPeriodos.add(periodo);
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
		return listaPeriodos;
	}

}
