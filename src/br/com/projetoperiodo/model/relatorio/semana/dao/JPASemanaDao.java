package br.com.projetoperiodo.model.relatorio.semana.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import br.com.projetoperiodo.model.relatorio.semana.Semana;
import br.com.projetoperiodo.model.relatorio.semana.impl.SemanaImpl;

public class JPASemanaDao implements SemanaDao 
{
	private EntityManagerFactory entityManagerFactory;
	
	public JPASemanaDao(EntityManagerFactory emf) {
		this.entityManagerFactory = emf;
	}
	@Override
	public Semana salvar(Semana semana) {

		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(semana);
		entityTransaction.commit();
		entityManager.close();
		return semana;
	}

	@Override
	public void remover(Semana semana) {

		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		semana  = (SemanaImpl)entityManager.merge(semana);
		entityManager.remove(semana);
		entityTransaction.commit();
		entityManager.close();
		
		
	}

	@Override
	public void alterar(Semana semana) {

		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(semana);
		entityManager.flush();
		entityTransaction.commit();
		entityManager.close();
		
		
	}

	@Override
	public List<Semana> listar() {

		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		List<Semana> semanas = entityManager.
						createQuery(" from SemanaImpl s ").getResultList();
		entityManager.close();
		return semanas;
	}

	@Override
	public Semana buscar(long primaryKey) {

		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		SemanaImpl semana = entityManager.find(SemanaImpl.class, primaryKey);
		entityManager.close();
		return semana;
	}
	
	@Override
	public List<Semana> buscarSemanasDeRelatorio(long chave) {
		
		StringBuilder builder = new StringBuilder();
		builder.append(" from SemanaImpl s ");
		builder.append(" where s.relatorio.chavePrimaria = ");
		builder.append(chave);
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		List<Semana> semanas = entityManager.
						createQuery(builder.toString()).getResultList();
		entityManager.close();
		return semanas;
	}

	
}
