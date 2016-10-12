/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.modelo.grupo;

import com.softwarecorporativo.monitoriaifpe.modelo.usuario.Usuario;
import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Edmilson Santana
 */
@Entity
@Table(name = "tb_grupo")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "id_grupo"))})
@Access(AccessType.FIELD)
@NamedQuery(name = Grupo.GRUPO_POR_NOME, query = "select g from Grupo as g where g.nome = ?1")
public class Grupo extends EntidadeNegocio {

    public static final String USUARIO = "usuario";
    public static final String ADMINISTRADOR = "admin";
    public static final String PROFESSOR = "professor";
    public static final String ALUNO = "aluno";
    public static final String GRUPO_POR_NOME = "grupoPorNome";
    private static final long serialVersionUID = -9166253705253329278L;

    @NotBlank
    @Size(max = 45)
    @Column(name = "txt_nome", nullable = false)
    private String nome;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Usuario> usuarios;

    public void adicionaUsuario(Usuario usuario) {
        if (usuarios == null) {
            this.usuarios = new ArrayList<>();
        }
        usuarios.add(usuario);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
