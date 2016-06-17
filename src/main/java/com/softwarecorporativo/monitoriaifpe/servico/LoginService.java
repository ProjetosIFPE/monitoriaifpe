/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Edmilson Santana
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class LoginService extends GenericService<Usuario> {

    @Override
    public Usuario getEntidadeNegocio() {
        return new Usuario();
    }

    @Override
    public Class<Usuario> getClasseEntidade() {
        return Usuario.class;
    }

    public Usuario buscarUsuarioPorLogin(String login) throws NegocioException {

        StringBuilder jpql = new StringBuilder();
        jpql.append(" select usuario from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as usuario ");
        jpql.append(" where usuario.login = :paramLogin ");

        TypedQuery<Usuario> query = entityManager.createQuery(jpql.toString(), getClasseEntidade());
        query.setParameter("paramLogin", login);
        try {
            Usuario usuario = query.getSingleResult();
            return usuario;
        } catch (NoResultException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    public Boolean verificarCadastroDeUsuario(Usuario usuario) {

        Boolean possuiCadastro = Boolean.FALSE;

        StringBuilder jpql = new StringBuilder();
        jpql.append(" select count(usuario) from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as usuario ");
        jpql.append(" where usuario.login = :paramLogin ");
        jpql.append(" and usuario.senha = :paramSenha ");

        TypedQuery<Long> query = entityManager.createQuery(jpql.toString(), Long.class);
        query.setParameter("paramLogin", usuario.getLogin());
        query.setParameter("paramSenha", usuario.getSenha());

        Long quantidadeCadastros = query.getSingleResult();
        if (quantidadeCadastros != null && quantidadeCadastros > 0) {
            possuiCadastro = Boolean.TRUE;
        }

        return possuiCadastro;
    }

}
