/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.grupo.Grupo;
import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import javax.persistence.TypedQuery;

/**
 *
 * @author Edmilson Santana
 * @param <T>
 */
public abstract class UsuarioService<T extends Usuario> extends GenericService<T> {

    public Usuario getUsuarioPorLogin(String login) {
        TypedQuery<T> query = super.entityManager
                .createNamedQuery(T.USUARIO_POR_LOGIN, getClasseEntidade());
        query.setParameter(1, login);
        return query.getSingleResult();
    }

    
    
    
}
