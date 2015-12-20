
package br.com.projetoperiodo.model.instituto.disciplina.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.model.instituto.disciplina.impl.DisciplinaImpl;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.exception.NegocioException;

public class JPADisciplinaDao implements DisciplinaDao {

	private EntityManagerFactory entityManagerFactory;

	public JPADisciplinaDao(EntityManagerFactory emf) {
		this.entityManagerFactory = emf;
	}

	@Override
	public void salvar(Disciplina disciplina) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(disciplina);
		entityTransaction.commit();
		entityManager.close();
	}

	@Override
	public void atualizar(Disciplina disciplina) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(disciplina);
		entityTransaction.commit();
		entityManager.close();
	}

	@Override
	public void remover(Disciplina disciplina) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Disciplina disciplinaAtualizada = (Disciplina) entityManager.merge(disciplina);
		entityManager.remove(disciplinaAtualizada);
		entityTransaction.commit();
		entityManager.close();
	}

	@Override
	public void removerProfessorDaDisciplina(long chaveDisciplina) {

		StringBuilder builder = new StringBuilder();
		builder.append(" update DisciplinaImpl d ");
		builder.append(" set d.professor.chavePrimaria = :professor ");
		builder.append(" where d.chavePrimaria = :chave ");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery(builder.toString()).setParameter("professor", null).setParameter("chave", chaveDisciplina);
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		query.executeUpdate();
		entityTransaction.commit();
		entityManager.close();
	}

	@Override
	public List<Disciplina> listar() {

		StringBuilder builder = new StringBuilder();
		builder.append(" from ");
		builder.append(" DisciplinaImpl ");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Disciplina> disciplinas = entityManager.createQuery(builder.toString()).getResultList();
		entityManager.close();

		return disciplinas;
	}

	@Override
	public List<Disciplina> listarDisciplinasDeProfessor(long chave) {

		StringBuilder builder = new StringBuilder();
		builder.append(" from ");
		builder.append(" DisciplinaImpl d ");
		builder.append(" where d.professor.chavePrimaria = ");
		builder.append(chave);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Disciplina> disciplinas = entityManager.createQuery(builder.toString()).getResultList();
		entityManager.close();
		return disciplinas;
	}

	@Override
	public List<Disciplina> listarDisciplinasSemProfessor() {

		StringBuilder builder = new StringBuilder();
		builder.append(" from ");
		builder.append(" DisciplinaImpl d ");
		builder.append(" where d.professor is null ");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Disciplina> disciplinas = entityManager.createQuery(builder.toString()).getResultList();
		entityManager.close();
		return disciplinas;
	}

	@Override
	public List<Disciplina> listarDisciplinasComProfessor() {

		StringBuilder builder = new StringBuilder();
		builder.append(" from ");
		builder.append(" DisciplinaImpl d ");
		builder.append(" where d.professor is not null ");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Disciplina> disciplinas = entityManager.createQuery(builder.toString()).getResultList();
		entityManager.close();
		return disciplinas;
	}

	@Override
	public List<Disciplina> listarDisciplinasDeAluno(long chave) {

		StringBuilder builder = new StringBuilder();
		builder.append(" select d from ");
		builder.append(" DisciplinaImpl d ");
		builder.append(" inner join d.pagantes p ");
		builder.append(" where p.chavePrimaria = ");
		builder.append(chave);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Disciplina> disciplinas = entityManager.createQuery(builder.toString()).getResultList();
		entityManager.close();
		return disciplinas;
	}

	

	@Override
	public Disciplina buscar(long primaryK) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Disciplina disciplina = entityManager.find(DisciplinaImpl.class, primaryK);
		entityTransaction.commit();
		entityManager.close();

		return disciplina;
	}

	@Override
	public Disciplina buscar(HashMap<String, Object> filter) throws NegocioException {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteria = builder.createQuery(DisciplinaImpl.class);
		Root root = criteria.from(DisciplinaImpl.class);

		if (filter.containsKey("descricao")) {
			criteria.where(builder.like(root.get("descricao"), (String) filter.get("descricao")));
		}
		Disciplina disciplina;
		try {
			disciplina = (Disciplina) entityManager.createQuery(criteria).getSingleResult();
		} catch (NoResultException e) {
			throw new NegocioException(Constantes.ENTIDADE_NAO_ENCONTRADA);
		}
		entityManager.close();
		return disciplina;
	}

}
