/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.grupo.Grupo;
import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ejb.EJB;
import javax.persistence.TypedQuery;

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
    public Aluno salvar(Aluno entidadeNegocio) throws NegocioException {
        adicionarGruposUsuario(entidadeNegocio);
        String sal = entidadeNegocio.gerarSal();
        entidadeNegocio = super.salvar(entidadeNegocio);
        String usuario = entidadeNegocio.getLogin();
        securityAccessService.salvarPropriedadesAcesso(sal, usuario);
        return entidadeNegocio;
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

    @Override
    public Aluno verificarExistencia(Aluno entidadeNegocio) {
        super.verificarExistencia(entidadeNegocio);
        return this.getAlunoPorMatricula(entidadeNegocio.getMatricula());

    }

    public Aluno getAlunoPorMatricula(String matricula) {
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select aluno ");
        jpql.append(" from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as aluno ");
        jpql.append(" where aluno.matricula = ?1 ");

        TypedQuery<Aluno> query = entityManager.createQuery(jpql.toString(),
                getClasseEntidade());
        query.setParameter(1, matricula);
        return query.getSingleResult();
    }

}
