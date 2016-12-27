/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
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
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CursoService extends GenericService<Curso> {

    @Override
    public void remover(Curso entidadeNegocio) throws NegocioException {

        if (entidadeNegocio.isInativo()) {
            super.remover(entidadeNegocio);
        } else {
            throw new NegocioException(NegocioException.CURSO_POSSUI_RELACIONAMENTOS);
        }
    }

    @Override
    public Boolean verificarExistencia(Curso curso) {

        return this.cursoJaCadastrado(curso);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Curso getEntidadeNegocio() {
        return new Curso();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Class<Curso> getClasseEntidade() {
        return Curso.class;
    }

    public Boolean cursoJaCadastrado(Curso curso) {
        Object[] parametros = new Object[4];
        parametros[0] = curso.getCodigoCampus();
        parametros[1] = curso.getDescricao();
        parametros[2] = curso.getCodigoCurso();
        parametros[3] = curso.getChavePrimaria();
        return super.count(Curso.COUNT_CURSO_CADASTADO, parametros) > 0;
    }

    public Boolean possuiCursos() {
        return super.count(Curso.COUNT_CURSO, null) > 0;
    }

}
