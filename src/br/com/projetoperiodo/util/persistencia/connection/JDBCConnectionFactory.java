
package br.com.projetoperiodo.util.persistencia.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.projetoperiodo.util.persistencia.persistencia.DatabaseUnit;

public class JDBCConnectionFactory extends ConnectionFactory {

	private JDBCConnectionFactory() { }

	public JDBCConnectionFactory(DatabaseUnit unit) {
		super.databaseUnit = unit;
	}

	@Override
	public Object getConnection() {
		try {		
			Class.forName(databaseUnit.getDriverClass());
			DriverManager.registerDriver(databaseUnit.getDriver());
			Connection connection = DriverManager.getConnection(databaseUnit.getConnectionURL(), databaseUnit.getUser(),
							databaseUnit.getSenha());
			connection.setAutoCommit(false);
			return connection;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
