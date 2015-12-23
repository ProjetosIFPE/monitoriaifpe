package br.com.projetoperiodo.model.instituto.periodo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.projetoperiodo.model.instituto.periodo.Periodo;
import br.com.projetoperiodo.model.instituto.periodo.impl.PeriodoImpl;
import br.com.projetoperiodo.util.constantes.enumeracoes.Semestre;
import br.com.projetoperiodo.util.fachada.Persistencia;

public class JPAPeriodoDao implements PeriodoDao
{
	private EntityManagerFactory entityManagerFactory;
	
	public JPAPeriodoDao(EntityManagerFactory emf) {
		this.entityManagerFactory = emf;
	}
	@Override
	public Periodo salvar(Periodo periodo) {
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(periodo);
		entityTransaction.commit();
		entityManager.close();
		return periodo;
	}

	@Override
	public void atualizar(Periodo periodo) {
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(periodo);
		entityManager.flush();
		entityTransaction.commit();
		entityManager.close();
		
	}

	@Override
	public void remover(Periodo periodo) {
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.remove(periodo);
		entityTransaction.commit();
		entityManager.close();
	}

	@Override
	public Periodo buscar(long l) {
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Periodo periodo = entityManager.find(PeriodoImpl.class, l);
		entityTransaction.commit();
		entityManager.close();
		
		return periodo;
	}

	@Override
	public List<Periodo> listar() {
		StringBuilder builder = new StringBuilder();
		builder.append(" from PeriodoImpl p ");
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		List<Periodo> monitores = entityManager.createQuery(builder.toString()).getResultList();
		entityManager.close();
			
		return monitores;
	}
	
	@Override
	public List<Periodo> buscarPeriodoPorAnoEsemestre(int ano, Semestre semestre) {
		StringBuilder builder = new StringBuilder();
		builder.append(" select p from ");
		builder.append(" PeriodoImpl p ");
		builder.append(" where p.ano =  ");
		builder.append(ano);
		builder.append(" and ");
		builder.append(" p.semestre = ");
		builder.append(" :semestre ");
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		Query consulta =  entityManager.createQuery(builder.toString());
		consulta.setParameter("semestre", semestre);
		List<Periodo> periodos = consulta.getResultList();
		entityManager.close();
			
		return periodos;
	}
	
	public static void main(String[] args) {
		
		Persistencia.getInstance().buscarPeriodo(2015, Semestre.SEGUNDO);
	}

}
