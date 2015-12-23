
package br.com.projetoperiodo.model.instituto.professor.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import br.com.projetoperiodo.model.instituto.professor.Professor;
import br.com.projetoperiodo.model.instituto.professor.impl.ProfessorImpl;

public class JPAProfessorDao implements ProfessorDao  {

	private EntityManagerFactory entityManagerFactory;

	public JPAProfessorDao(EntityManagerFactory emf) {
		this.entityManagerFactory = emf;
	}
	@Override
	public Professor salvar(Professor professor) {

		EntityManager entma = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entma.getTransaction();
		transaction.begin();
		entma.persist(professor);
		transaction.commit();
		entma.close();
		return professor;
	}
	@Override
	public void atualizar(Professor professor) {

		EntityManager entma = entityManagerFactory.createEntityManager();

		EntityTransaction transaction = entma.getTransaction();
		transaction.begin();
		entma.merge(professor);
		entma.flush();
		transaction.commit();
		entma.close();
	}
	@Override
	public void remover(Professor professor) {

		EntityManager entma = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entma.getTransaction();
		transaction.begin();
		entma.merge(professor);
		entma.remove(professor);
		transaction.commit();
		entma.close();
	}
	
	@Override
	public List<Professor> listar() {

		EntityManager entma = entityManagerFactory.createEntityManager();
		@SuppressWarnings("unchecked")
		List<Professor> professor = entma.createQuery("from ProfessorImpl").getResultList();
		entma.close();
		return professor;

	}

	@Override
	public Professor buscar(long primaryKey) {

		EntityManager entma = entityManagerFactory.createEntityManager();

		Professor professor = (Professor) entma.find(ProfessorImpl.class, primaryKey);
		entma.close();
		return professor;
	}

	


}
