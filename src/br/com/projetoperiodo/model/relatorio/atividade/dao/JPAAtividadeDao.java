package br.com.projetoperiodo.model.relatorio.atividade.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.projetoperiodo.model.relatorio.atividade.Atividade;
import br.com.projetoperiodo.model.relatorio.atividade.impl.AtividadeImpl;
import br.com.projetoperiodo.util.persistencia.jpa.FabricaJPA;
import br.com.projetoperiodo.util.persistencia.jpa.JPAUtil;

public class JPAAtividadeDao implements AtividadeDao
{

	@Override
	public void salvar(Atividade atividade) {

		EntityManager entityManager =  JPAUtil.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(atividade);
		entityTransaction.commit();
		entityManager.close();
	}

	@Override
	public void remover(Atividade atividade) {

		EntityManager entityManager =  JPAUtil.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Atividade atividadeAtualizada = (Atividade)entityManager.merge(atividade);
		entityManager.remove(atividadeAtualizada);
		entityTransaction.commit();
		entityManager.close();
		
	}

	@Override
	public void alterar(Atividade atividade) {

		EntityManager entityManager =  JPAUtil.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(atividade);
		entityTransaction.commit();
		entityManager.close();
		
	}

	@Override
	public List<Atividade> listar() {


		EntityManager entityManager =  JPAUtil.getInstance().getEntityManagerFactory().createEntityManager();
		List<Atividade> atividades = entityManager.
						createQuery("from AtividadeImpl").getResultList();
		entityManager.close();
		return atividades;
	}

	@Override
	public Atividade buscar(long primaryKey) {

		EntityManager entityManager =  JPAUtil.getInstance().getEntityManagerFactory().createEntityManager();
		AtividadeImpl atividade = entityManager.find(AtividadeImpl.class, primaryKey);
		entityManager.close();
		return atividade;
	}
	
}
