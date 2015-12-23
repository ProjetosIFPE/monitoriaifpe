package br.com.projetoperiodo.model.relatorio.atividade.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import br.com.projetoperiodo.model.relatorio.atividade.Atividade;
import br.com.projetoperiodo.model.relatorio.atividade.impl.AtividadeImpl;

public class JPAAtividadeDao implements AtividadeDao
{
	private EntityManagerFactory entityManagerFactory;
	
	public JPAAtividadeDao(EntityManagerFactory emf) {
		this.entityManagerFactory = emf;
	}
	@Override
	public void salvar(Atividade atividade) {

		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(atividade);
		entityTransaction.commit();
		entityManager.close();
	}

	@Override
	public void remover(Atividade atividade) {

		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Atividade atividadeAtualizada = (Atividade)entityManager.merge(atividade);
		entityManager.remove(atividadeAtualizada);
		entityTransaction.commit();
		entityManager.close();
		
	}

	@Override
	public void alterar(Atividade atividade) {

		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(atividade);
		entityManager.flush();
		entityTransaction.commit();
		entityManager.close();
		
	}

	@Override
	public List<Atividade> listar() {


		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		List<Atividade> atividades = entityManager.
						createQuery(" from AtividadeImpl ").getResultList();
		entityManager.close();
		return atividades;
	}

	@Override
	public Atividade buscar(long primaryKey) {

		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		AtividadeImpl atividade = entityManager.find(AtividadeImpl.class, primaryKey);
		entityManager.close();
		return atividade;
	}
	
	@Override
	public List<Atividade> buscarAtividadesDeSemana(long chave) {
		
		StringBuilder builder = new StringBuilder();
		builder.append(" from ");
		builder.append(" AtividadeImpl a ");
		builder.append(" where a.semana.chavePrimaria = ");
		builder.append(chave);
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		List<Atividade> atividades = entityManager.
						createQuery(builder.toString()).getResultList();
		entityManager.close();
		return atividades;
	}

	
}
