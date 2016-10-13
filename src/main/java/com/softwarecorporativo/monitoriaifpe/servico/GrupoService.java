/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.grupo.Grupo;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 *
 * @author Edmilson Santana
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class GrupoService extends GenericService<Grupo> {

    @Override
    public Grupo getEntidadeNegocio() {
        return new Grupo();
    }

    @Override
    public Class<Grupo> getClasseEntidade() {
        return Grupo.class;
    }

    public Grupo obterGrupo(String nomeGrupo) {
        String[] atributos = new String[]{nomeGrupo};
        return get(Grupo.GRUPO_POR_NOME, atributos);
    }

    public Long contarGrupoPorNome(String nomeGrupo) {
        String[] atributos = new String[]{nomeGrupo};
        return count(Grupo.COUNT_GRUPO_POR_NOME, atributos);
    }

    @Override
    public Boolean verificarExistencia(Grupo entidadeNegocio) {
        return contarGrupoPorNome(entidadeNegocio.getNome()) > 0;
    }

}
