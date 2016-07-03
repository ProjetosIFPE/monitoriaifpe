/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.grupo.Grupo;
import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import com.softwarecorporativo.monitoriaifpe.modelo.util.Util;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ejb.EJB;

/**
 *
 * @author Douglas Albuqerque
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AlunoService extends UsuarioService<Aluno> {

    @EJB
    private GrupoService grupoService;

    @EJB
    private SecurityAccessService securityAccessService;

    @Override
    public Aluno salvar(Aluno entidadeNegocio) {
        adicionarGruposUsuario(entidadeNegocio);
        String sal = entidadeNegocio.gerarSal();
        String usuario = entidadeNegocio.getLogin();
        securityAccessService.salvarPropriedadesAcesso(sal, usuario);
        return super.salvar(entidadeNegocio);
    }

    public void adicionarGruposUsuario(Usuario usuario) {
        usuario.adicionarGrupo(grupoService.obterGrupo(Grupo.USUARIO));
        usuario.adicionarGrupo(grupoService.obterGrupo(Grupo.ALUNO));
    }

    @Override
    public Aluno getEntidadeNegocio() {
        return new Aluno();
    }

    @Override
    public Class<Aluno> getClasseEntidade() {
        return Aluno.class;
    }

}
