/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.grupo.Grupo;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ProfessorService extends UsuarioService<Professor> {

    @EJB
    private GrupoService grupoService;

    @EJB
    private SecurityAccessService securityAccessService;

    @Override
    public Professor salvar(Professor entidadeNegocio) throws NegocioException {
        adicionarGruposUsuario(entidadeNegocio);
        String sal = entidadeNegocio.gerarSal();
        entidadeNegocio = super.salvar(entidadeNegocio);
        String usuario = entidadeNegocio.getLogin();
        securityAccessService.salvarPropriedadesAcesso(sal, usuario);
        return entidadeNegocio;
    }

    public void adicionarGruposUsuario(Usuario usuario) {
        usuario.adicionarGrupo(grupoService.obterGrupo(Grupo.USUARIO));
        usuario.adicionarGrupo(grupoService.obterGrupo(Grupo.PROFESSOR));
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
