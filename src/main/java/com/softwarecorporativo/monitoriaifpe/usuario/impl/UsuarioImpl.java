package com.softwarecorporativo.monitoriaifpe.usuario.impl;

import com.softwarecorporativo.monitoriaifpe.negocio.impl.EntidadeNegocioImpl;
import com.softwarecorporativo.monitoriaifpe.usuario.Usuario;
import com.sun.scenario.effect.Effect;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USUARIO")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "PAPEL_USUARIO", discriminatorType = DiscriminatorType.STRING, length = 1)
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "USUARIO_ID"))})
public abstract class UsuarioImpl extends EntidadeNegocioImpl implements Usuario {

    @Column(name = "USUARIO_NOME", nullable = false)
    private String nome;

    @Column(name = "USUARIO_SOBRENOME", nullable = false)
    private String sobrenome;

    @Column(name = "USUARIO_LOGIN", nullable = false)
    private String login;

    @Column(name = "USUARIO_SENHA", nullable = false)
    private String senha;

    @Column(name = "USUARIO_EMAIL", nullable = false)
    private String email;

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.usuario.impl.Usuario#getLogin()
     */
    @Override
    public String getLogin() {

        return login;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.usuario.impl.Usuario#getSenha()
     */
    @Override
    public String getSenha() {

        return senha;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.usuario.impl.Usuario#setLogin(java.lang.String)
     */
    @Override
    public void setLogin(String login) {

        this.login = login;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.usuario.impl.Usuario#setSenha(java.lang.String)
     */
    @Override
    public void setSenha(String senha) {

        this.senha = senha;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.usuario.impl.Usuario#getEmail()
     */
    @Override
    public String getEmail() {

        return email;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.usuario.impl.Usuario#setEmail(java.lang.String)
     */
    @Override
    public void setEmail(String email) {

        this.email = email;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.usuario.impl.Usuario#setNome(java.lang.String)
     */
    @Override
    public void setNome(String nome) {

        this.nome = nome;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.usuario.impl.Usuario#getNome()
     */
    @Override
    public String getNome() {

        return nome;
    }


    @Override
    public String getSobrenome() {
        return this.sobrenome;
    }

    @Override
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

   

}
