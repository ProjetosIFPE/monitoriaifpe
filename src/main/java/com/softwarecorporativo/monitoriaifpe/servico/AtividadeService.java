/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;

/**
 *
 * @author Edmilson Santana
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AtividadeService extends GenericService<Atividade> {

    @Override
    public Atividade getEntidadeNegocio() {
        return new Atividade();
    }

    @Override
    public Class<Atividade> getClasseEntidade() {
        return Atividade.class;
    }
    
    
    public byte[] obterRelatorioFrequencia(Monitoria monitoria, Integer mes) {
        List<Atividade> atividades = this.consultarAtividadesMensaisDaMonitoria(monitoria, mes);
       
        StringBuilder descricaoAcumulada = new StringBuilder();
        StringBuilder observacaoAcumulada = new StringBuilder();
      
        for ( Atividade atividade : atividades ) {
            descricaoAcumulada.append(atividade.getDescricao());
            descricaoAcumulada.append(", ");
            observacaoAcumulada.append(atividade.getObservacoes());
            observacaoAcumulada.append(", ");
        }
        
        return null;
    }
    
    
    public List<Atividade> consultarAtividadesMensaisDaMonitoria(Monitoria monitoria, Integer mes) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select a from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as a where a.monitoria = :paramMonitoria ");
        jpql.append(" and a.data ");
        Query query = super.entityManager.createQuery(jpql.toString(), Atividade.class);
        return query.getResultList();
    }

}
