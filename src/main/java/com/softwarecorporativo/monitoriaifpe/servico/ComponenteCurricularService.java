/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.ComponenteCurricular;
import java.util.List;
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
public class ComponenteCurricularService extends GenericService<ComponenteCurricular> {

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ComponenteCurricular getEntidadeNegocio() {
        return new ComponenteCurricular();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Class<ComponenteCurricular> getClasseEntidade() {
        return ComponenteCurricular.class;
    }

    public List<ComponenteCurricular> consultarComponentes(Curso curso) {
        Object[] parametros = {curso};
        return super.getResultList(ComponenteCurricular.COMPONENTE_POR_CURSO, parametros);
    }

    @Override
    public Boolean verificarExistencia(ComponenteCurricular componente) {
        Object[] parametros = new Object[4];
        parametros[0] = componente.getCurso();
        parametros[1] = componente.getDescricao();
        parametros[2] = componente.getCodigoComponenteCurricular();
        parametros[3] = componente.getChavePrimaria();
        return super.count(ComponenteCurricular.COUNT_COMPONENTE_CADASTRADO, parametros) > 0;
    }

    @Override
    public void remover(ComponenteCurricular entidadeNegocio) throws NegocioException {
        if (entidadeNegocio.isInativo()) {
            super.remover(entidadeNegocio);
        } else {
            throw new NegocioException(NegocioException.COMPONENTE_POSSUI_RELACIONAMENTOS);
        }
    }
    
     @Override
    public void atualizar(ComponenteCurricular componenteCurricular) throws NegocioException {
        this.validarCadastro(componenteCurricular);
        super.atualizar(componenteCurricular); 
    }
    

    public Boolean componenteNaoPossuiTurmas(ComponenteCurricular entidadeNegocio) {
        Object[] parametros = {entidadeNegocio};
        return super.count(ComponenteCurricular.COUNT_COMPONENTE_NAO_POSSUI_TURMAS, parametros) > 0;
    }

}
