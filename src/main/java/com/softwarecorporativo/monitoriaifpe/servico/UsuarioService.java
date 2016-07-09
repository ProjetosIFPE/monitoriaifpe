/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.grupo.Grupo;
import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import javax.annotation.PostConstruct;
import javax.persistence.TypedQuery;

/**
 *
 * @author Edmilson Santana
 * @param <T>
 */
public abstract class UsuarioService<T extends Usuario> extends GenericService<T> {

    private GrupoService servicoGrupo;

    private SecurityAccessService securityAccessService;

    private EmailService emailService;

    @PostConstruct
    public void inicializarServicos() {
        servicoGrupo = inicializarServicoGrupo();
        securityAccessService = inicializarServicoSeguranca();
        emailService = inicializarServicoEmail();
    }

    @Override
    public T salvar(T usuario) throws NegocioException {
        adicionarGrupoUsuario(usuario);
        String sal = usuario.gerarSal();
        securityAccessService.salvarPropriedadesAcesso(sal, usuario.getLogin());
        usuario = super.salvar(usuario);
        emailService.enviarMensagem(usuario.getEmail());
        return usuario;

    }

    protected void adicionarGrupoUsuario(Usuario usuario) {
        usuario.adicionarGrupo(servicoGrupo.obterGrupo(Grupo.USUARIO));
        this.adicionarGrupos(usuario);
    }

    public Usuario getUsuarioPorLogin(String login) {
        TypedQuery<T> query = super.entityManager
                .createNamedQuery(T.USUARIO_POR_LOGIN, this.getClasseEntidade());
        query.setParameter(1, login);
        return query.getSingleResult();
    }

    @Override
    public T verificarExistencia(T entidadeNegocio) {
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select usuario ");
        jpql.append(" from ");
        jpql.append(this.getClasseEntidade().getSimpleName());
        jpql.append(" as usuario ");
        jpql.append(" where usuario.login = ?1 or usuario.email = ?2 ");

        TypedQuery<T> query = entityManager.createQuery(jpql.toString(),
                getClasseEntidade());
        query.setParameter(1, entidadeNegocio.getLogin());
        query.setParameter(2, entidadeNegocio.getEmail());
        return query.getSingleResult();
    }

    @Override
    public Class<T> getClasseEntidade() {
        return (Class<T>) Usuario.class;
    }

    abstract void adicionarGrupos(Usuario usuario);

    abstract GrupoService inicializarServicoGrupo();

    abstract SecurityAccessService inicializarServicoSeguranca();

    abstract EmailService inicializarServicoEmail();

}
