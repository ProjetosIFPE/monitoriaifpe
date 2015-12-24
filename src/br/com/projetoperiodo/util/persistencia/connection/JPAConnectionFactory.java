package br.com.projetoperiodo.util.persistencia.connection;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Persistence;

import br.com.projetoperiodo.util.persistencia.persistencia.DatabaseUnit;

public class JPAConnectionFactory extends ConnectionFactory{


	private JPAConnectionFactory() { }

	public JPAConnectionFactory(DatabaseUnit unit) {
		super.databaseUnit = unit;
	}
	@Override
	public Object getConnection() {

		Map<String, String> properties = new HashMap<String, String>();
		properties.put("hibernate.connection.driver_class", databaseUnit.getDriverClass());
		properties.put("hibernate.connection.url", databaseUnit.getConnectionURL());
		properties.put("hibernate.connection.username", databaseUnit.getUser());
		properties.put("hibernate.connection.password", databaseUnit.getSenha());
		properties.put("hibernate.dialect", databaseUnit.getDialetoPersistenceUnit());
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.id.new_generator_mappings", "false");
		return Persistence.createEntityManagerFactory("local_unit",properties);
	}

}
