package br.com.projetoperiodo.model.instituto.monitor.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import br.com.projetoperiodo.model.instituto.monitor.Monitoria;
import br.com.projetoperiodo.model.instituto.monitor.impl.MonitorImpl;
import br.com.projetoperiodo.model.instituto.periodo.Periodo;

public class JPAMonitoriaDao implements MonitoriaDao{

	private EntityManagerFactory entityManagerFactory;
	
	
	public JPAMonitoriaDao(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	@Override
	public Monitoria salvar(Monitoria monitoria) {
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(monitoria);
		entityTransaction.commit();
		entityManager.close();
		return monitoria;
	}

	@Override
	public void atualizar(Monitoria monitor) {
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(monitor);
		entityManager.flush();
		entityTransaction.commit();
		entityManager.close();
	}

	@Override
	public void remover(Monitoria monitoria) {
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		monitoria = entityManager.find(MonitorImpl.class, monitoria.getChavePrimaria());
		entityManager.remove(monitoria);
		entityTransaction.commit();
		entityManager.close();
		
	}

	@Override
	public List<Monitoria> listar() {
		StringBuilder builder = new StringBuilder();
		builder.append(" from MonitorImpl m ");
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		List<Monitoria> monitorias = entityManager.createQuery(builder.toString()).getResultList();
		entityManager.close();
		return monitorias;
	}

	@Override
	public Monitoria buscar(long primaryK) {
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Monitoria monitoria = entityManager.find(MonitorImpl.class, primaryK);
		entityManager.close();
		
		return monitoria;
	}
	
	@Override
	public List<Monitoria> buscarMonitoriasDeDisciplina(long chave) {
		StringBuilder builder = new StringBuilder();
		builder.append(" select m from ");
		builder.append(" MonitorImpl m ");
		builder.append(" where m.disciplina.chavePrimaria = ");
		builder.append(chave);
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		List<Monitoria> monitorias = entityManager.createQuery(builder.toString()).getResultList();
		entityManager.close();
		return monitorias;
	}
	@Override
	public List<Monitoria> buscarMonitoriasDeDisciplinaDeUmPeriodo(long chaveDisciplina, long chavePeriodo) {
		StringBuilder builder = new StringBuilder();
		builder.append(" select m from ");
		builder.append(" MonitorImpl m ");
		builder.append(" where m.disciplina.chavePrimaria = :chaveDisciplina ");
		builder.append( " and ");
		builder.append(" m.periodo.chavePrimaria = :chavePeriodo");
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		List<Monitoria> monitorias = entityManager.createQuery(builder.toString())
						.setParameter("chaveDisciplina", chaveDisciplina).setParameter("chavePeriodo", chavePeriodo).getResultList();
		entityManager.close();
		return monitorias;
	}
	
	@Override
	public List<Monitoria> buscarMonitoriasDeAluno(long chave) {
		StringBuilder builder = new StringBuilder();
		builder.append(" from MonitorImpl m ");
		builder.append(" where m.aluno.chavePrimaria = ");
		builder.append(chave);
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		List<Monitoria> monitorias = entityManager.createQuery(builder.toString()).getResultList();
		entityManager.close();
		return monitorias;
	}
	
	@Override
	public Long buscarQuantidadeMonitoriasDeAlunoEmUmPeriodo(Monitoria monitor) {
		StringBuilder builder = new StringBuilder();
		builder.append(" select count(*) ");
		builder.append(" from MonitorImpl m ");
		builder.append(" where m.periodo.ano = :ano ");
		builder.append(" and m.periodo.semestre = :semestre ");
		builder.append(" and m.aluno.chavePrimaria = :chave ");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Long quantidade = (Long) entityManager.createQuery(builder.toString())
						.setParameter("ano", monitor.getPeriodo().getAno())
						.setParameter("semestre", monitor.getPeriodo().getSemestre())
						.setParameter("chave", monitor.getAluno().getChavePrimaria()).getSingleResult();
		entityManager.close();
		return quantidade;
	}
	@Override
	public List<Monitoria> buscarMonitoriaCadastrada(Monitoria monitoria) {
		StringBuilder builder = new StringBuilder();
		builder.append(" select m from MonitorImpl m");
		builder.append(" where m.periodo.chavePrimaria = ");
		builder.append(monitoria.getPeriodo().getChavePrimaria());
		builder.append(" and  m.disciplina.chavePrimaria = ");
		builder.append(monitoria.getDisciplina().getChavePrimaria());
		builder.append(" and m.aluno.chavePrimaria = ");
		builder.append(monitoria.getAluno().getChavePrimaria());
		builder.append(" and  m.habilitado = ");
		builder.append(Boolean.TRUE);
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		List<Monitoria> monitorias = entityManager.createQuery(builder.toString()).getResultList();
		entityManager.close();
		return monitorias;
	}

	
	

}
