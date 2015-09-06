package br.com.projetoperiodo.model.relatorio.atividade;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.projetoperiodo.util.persistencia.JPAUtil;

public class JPAAtividadeDao implements AtividadeDao
{

	@Override
	public void salvar(Atividade atividade) {

		EntityManager entityManager = JPAUtil.
						getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(atividade);
		entityTransaction.commit();
		entityManager.close();
	}

	@Override
	public void remover(Atividade atividade) {

		EntityManager entityManager = JPAUtil.
						getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Atividade atividadeAtualizada = (Atividade)entityManager.merge(atividade);
		entityManager.remove(atividadeAtualizada);
		entityTransaction.commit();
		entityManager.close();
		
	}

	@Override
	public void alterar(Atividade atividade) {

		EntityManager entityManager = JPAUtil.
						getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(atividade);
		entityTransaction.commit();
		entityManager.close();
		
	}

	@Override
	public List<Atividade> listar() {


		EntityManager entityManager = JPAUtil.
						getEntityManagerFactory().createEntityManager();
		List<Atividade> atividades = entityManager.
						createQuery("from Atividade").getResultList();
		entityManager.close();
		return atividades;
	}

	@Override
	public Atividade buscar(int primaryKey) {

		EntityManager entityManager = JPAUtil.
						getEntityManagerFactory().createEntityManager();
		Atividade atividade = entityManager.find(Atividade.class, primaryKey);
		entityManager.close();
		return atividade;
	}
	
}