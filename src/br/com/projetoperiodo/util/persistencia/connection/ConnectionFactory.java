package br.com.projetoperiodo.util.persistencia.connection;

import br.com.projetoperiodo.util.persistencia.persistencia.DatabaseUnit;

public abstract class ConnectionFactory {
	
	protected DatabaseUnit databaseUnit;
	
	public abstract Object getConnection();
}
