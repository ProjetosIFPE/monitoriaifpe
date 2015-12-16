package br.com.projetoperiodo.model.relatorio.frequencia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import br.com.projetoperiodo.model.relatorio.frequencia.RelatorioFrequencia;
import br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequenciaImpl;
import br.com.projetoperiodo.util.constantes.enumeracoes.Situacao;

public class JPARelatorioFrequenciaDao implements RelatorioFrequenciaDao
{
	private EntityManagerFactory entityManagerFactory;
	
	
	public JPARelatorioFrequenciaDao(EntityManagerFactory emf) {
		this.entityManagerFactory = emf;
	}
	@Override
	public RelatorioFrequencia salvar(RelatorioFrequencia relatorio) {

		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(relatorio);
		entityTransaction.commit();
		entityManager.close();
		return relatorio;
		
	}

	@Override
	public RelatorioFrequencia atualizar(RelatorioFrequencia relatorio) {

		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(relatorio);
		entityTransaction.commit();
		entityManager.close();
		return relatorio;
		
	}

	@Override
	public void remover(RelatorioFrequencia relatorio) {

		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		relatorio = entityManager.merge(relatorio);
		entityManager.remove(relatorio);
		entityTransaction.commit();
		entityManager.close();
		
	}

	@Override
	public List<RelatorioFrequencia> listar(String condicao) {

		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		List<RelatorioFrequencia> lista = entityManager.
						createQuery(condicao).getResultList();
		entityManager.close();
		return lista;
	}

	@Override
	public RelatorioFrequencia buscar(long primaryKey) {

		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		return entityManager.find(RelatorioFrequenciaImpl.class, primaryKey);
	}
	
	@Override
	public void remover(String condicao) {
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.createQuery(condicao).executeUpdate();
		entityTransaction.commit();
		entityManager.close();
		
	}

	@Override
	public List<Situacao> listarSituacaoDosRelatorios(long chave) {

		StringBuilder builder = new StringBuilder();
		builder.append(" select situacao from ");
		builder.append(" RelatorioFrequenciaImpl relatorio ");
		builder.append(" where relatorio.monitor = ");
		builder.append(chave);
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		List<Situacao> lista = entityManager.
						createQuery(builder.toString()).getResultList();
		entityManager.close();
		return lista;
	}
	
	@Override
	public RelatorioFrequencia buscarRelatoriosDeMonitoriaPorMes(long chave, int mes) {
		StringBuilder builder = new StringBuilder();
		builder.append(" select r from ");
		builder.append(" RelatorioFrequenciaImpl r ");
		builder.append(" where r.monitor.chavePrimaria =  ");
		builder.append(chave);
		builder.append(" and r.mes = ");
		builder.append(mes);
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		RelatorioFrequencia relatorio = (RelatorioFrequencia) entityManager.
						createQuery(builder.toString()).getSingleResult();
		entityManager.close();
		return relatorio;
	}
	
	@Override
	public List<RelatorioFrequencia> buscarRelatoriosDeMonitoria(long chave) {
		StringBuilder builder = new StringBuilder();
		builder.append(" from ");
		builder.append(" RelatorioFrequenciaImpl r ");
		builder.append(" where r.monitor.chavePrimaria = ");
		builder.append(chave);
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		List<RelatorioFrequencia> lista = entityManager.
						createQuery(builder.toString()).getResultList();
		entityManager.close();
		return lista;
	}
	
	

}
