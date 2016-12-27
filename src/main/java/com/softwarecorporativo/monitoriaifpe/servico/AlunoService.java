/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.modelo.grupo.Grupo;
import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ejb.EJB;

/**
 *
 * @author Douglas Albuquerque
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AlunoService extends UsuarioService<Aluno> {

    @EJB
    private GrupoService grupoService;

    @EJB
    private EmailService emailService;

    @Override
    public Aluno getEntidadeNegocio() {
        return new Aluno();
    }

    @Override
    public Class<Aluno> getClasseEntidade() {
        return Aluno.class;
    }

    @Override
    void adicionarGrupos(Usuario usuario) {
        usuario.adicionarGrupo(grupoService.obterGrupo(Grupo.ALUNO));
    }

    @Override
    GrupoService inicializarServicoGrupo() {
        return grupoService;
    }

    @Override
    EmailService inicializarServicoEmail() {
        return emailService;
    }

    public Long contarAlunoPorMatricula(String matricula) {
        String[] atributos = new String[]{matricula};
        return super.count(Aluno.COUNT_ALUNO_POR_MATRICULA, atributos);
    }

    @Override
    public Boolean verificarExistencia(Aluno entidadeNegocio) {
        Boolean existe = super.verificarExistencia(entidadeNegocio);
        if (existe) {
            return existe;
        } else {
            existe = (this.contarAlunoPorMatricula(
                    entidadeNegocio.getMatricula()) > 0);
            return existe;
        }
    }

}
