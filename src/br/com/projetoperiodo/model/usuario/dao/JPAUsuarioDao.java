
package br.com.projetoperiodo.model.usuario.dao;

import java.sql.Connection;
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

import br.com.projetoperiodo.model.usuario.Usuario;
import br.com.projetoperiodo.model.usuario.impl.UsuarioImpl;
import br.com.projetoperiodo.util.constantes.Constantes;
import br.com.projetoperiodo.util.exception.NegocioException;
import br.com.projetoperiodo.util.fachada.Persistencia;
import br.com.projetoperiodo.util.persistencia.connection.JPAConnectionFactory;
import br.com.projetoperiodo.util.persistencia.persistencia.OracleDatabaseUnit;

public class JPAUsuarioDao implements UsuarioDao {

	private EntityManagerFactory entityManagerFactory;
	

	public JPAUsuarioDao(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}


	@Override
	public Usuario salvar(Usuario usuario) {

		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(usuario);
		entityTransaction.commit();
		entityManager.close();
		return usuario;

	}

	@Override
	public Usuario atualizar(Usuario usuario) {

		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(usuario);
		entityTransaction.commit();
		entityManager.close();
		return usuario;
	}

	@Override
	public void remover(Usuario usuario) {

		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Object object = entityManager.merge(usuario);
		entityManager.remove(object);
		entityTransaction.commit();
		entityManager.close();

	}

	@Override
	public List<Usuario> listar() {

		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		List<Usuario> usuarios = entityManager.createQuery
						("from UsuarioImpl u where TYPE(u) = UsuarioImpl").getResultList();
		entityManager.close();
		return usuarios;
	}
	@Override
	public Long buscarCadastroPorLogin(String login) {
		StringBuilder builder = new StringBuilder();
		builder.append("select count(*) from UsuarioImpl u ");
		builder.append("where u.login = :login");
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery(builder.toString());
		query.setParameter("login", login);
		Long quantidade = (Long) query.getSingleResult();
		entityManager.close();
		return quantidade;
	}
	
	@Override
	public Long buscarCadastroPorEmail(String email) {
		StringBuilder builder = new StringBuilder();
		builder.append("select count(*) from UsuarioImpl u ");
		builder.append("where u.email = :email");
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery(builder.toString());
		query.setParameter("email", email);
		Long quantidade = (Long) query.getSingleResult();
		entityManager.close();
		return quantidade;
	}

	@Override
	public Usuario buscar(String login) {

		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("select u from UsuarioImpl u " + "where u.login = :login");
		query.setParameter("login", login);
		Usuario usuario = (Usuario) query.getSingleResult();
		entityManager.close();
		return usuario;
	}

	@Override
	public Usuario buscar(long l) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Usuario usuario = (Usuario) entityManager.find(UsuarioImpl.class, l);
		entityManager.close();
		return usuario;
	}

	@Override
	public Usuario buscar(HashMap<String, Object> filter) throws NegocioException {
		EntityManager entityManager =  entityManagerFactory.createEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery  criteria = builder.createQuery(UsuarioImpl.class);
		Root root = criteria.from(UsuarioImpl.class);
		
		if ( filter.containsKey("email") ) {
			criteria.where( builder.like(root.get("email"), 
							(String)filter.get("email")) );
		}
		Usuario usuario;
		try {
			usuario =   (Usuario) entityManager.createQuery(criteria).getSingleResult();
		} catch( NoResultException e ) {
			throw new NegocioException(Constantes.ENTIDADE_NAO_ENCONTRADA);
		}
		entityManager.close();
		return usuario;
	}
	
}
