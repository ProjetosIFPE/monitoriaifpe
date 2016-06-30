/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.grupo.Grupo;
import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ProfessorService extends GenericService<Professor>
{
    @EJB
    private GrupoService grupoService;
    
    @Override
    public Professor salvar(Professor entidadeNegocio) {
        entidadeNegocio.adicionarGrupo(grupoService.obterGrupo(Grupo.USUARIO));
        entidadeNegocio.adicionarGrupo(grupoService.obterGrupo(Grupo.PROFESSOR));
        return super.salvar(entidadeNegocio);
    }


    @Override
    public Professor getEntidadeNegocio() {
       return new Professor();
    }

    @Override
    public Class<Professor> getClasseEntidade() {
       return Professor.class;
    }
    
}
