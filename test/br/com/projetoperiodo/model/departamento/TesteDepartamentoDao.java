
package br.com.projetoperiodo.model.departamento;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.projetoperiodo.model.instituto.departamento.Departamento;
import br.com.projetoperiodo.model.instituto.departamento.DepartamentoDao;
import br.com.projetoperiodo.model.instituto.departamento.HibernateDepartamentoDao;
import br.com.projetoperiodo.model.instituto.departamento.JPADepartamentoDao;
import br.com.projetoperiodo.util.persistencia.HibernateUtil;

public class TesteDepartamentoDao {

	private DepartamentoDao departamentoDao;

	@BeforeClass
	public void setUp() {

		departamentoDao = new JPADepartamentoDao();
	}

	@Test
	public void testeInserirDepartamento() {
		int qtdInicio = departamentoDao.listar().size();
		departamentoDao.salvar(montarObjetoDepartamento());
		int qtdFim = departamentoDao.listar().size();
		Assert.assertEquals(qtdInicio + 1, qtdFim);
	}
	
	@Test
	public void testeRemoverDepartamento() {
		Departamento departamentoInserido = montarObjetoDepartamento();
		departamentoDao.salvar(departamentoInserido);
		
		int qtdInicio = departamentoDao.listar().size();
		departamentoDao.remover(departamentoInserido);
		int qtdFim = departamentoDao.listar().size();
		Assert.assertEquals(qtdInicio - 1, qtdFim);
	}

	private Departamento montarObjetoDepartamento() {
		Departamento departamento = new Departamento();
		departamento.setDescricao( "Departamento de Pesquisa" );
		departamento.setDescricaoAbreviada("DPE");
		return departamento;
	}

	@AfterClass
	public void close() {
		
	}
}
