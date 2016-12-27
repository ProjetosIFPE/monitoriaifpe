/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

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
    private EmailService emailService;

    @Override
    public Professor getEntidadeNegocio() {
        return new Professor();
    }

    @Override
    public Class<Professor> getClasseEntidade() {
        return Professor.class;
    }

    @Override
    void adicionarGrupos(Usuario usuario) {
        usuario.adicionarGrupo(grupoService.obterGrupo(Grupo.PROFESSOR));
    }

    @Override
    GrupoService inicializarServicoGrupo() {
        return grupoService;
    }

    @Override
    EmailService inicializarServicoEmail() {
        return emailService;
    }

    public Long contarProfessorPorSiape(String siape) {
        String[] atributos = new String[]{siape};
        return count(Professor.COUNT_PROFESSOR_POR_SIAPE, atributos);
    }

    @Override
    public Boolean verificarExistencia(Professor entidadeNegocio) {
         Boolean existe = super.verificarExistencia(entidadeNegocio);
         if(existe){
             return existe;
         } else
             existe = (contarProfessorPorSiape(entidadeNegocio.getSiape()) > 0);
         return existe;
    }

}
