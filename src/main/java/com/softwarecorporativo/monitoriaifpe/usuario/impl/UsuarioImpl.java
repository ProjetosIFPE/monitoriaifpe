package com.softwarecorporativo.monitoriaifpe.usuario.impl;

import com.softwarecorporativo.monitoriaifpe.negocio.impl.EntidadeNegocioImpl;
import com.softwarecorporativo.monitoriaifpe.usuario.Usuario;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "USUARIO")
@Inheritance(strategy = InheritanceType.JOINED)
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "USUARIO_ID"))})
public class UsuarioImpl extends EntidadeNegocioImpl implements Usuario {

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

    @Column(name = "ULTIMO_ACESSO", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcesso;

    @Transient
    protected String PAPEL = "usuario";

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

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.usuario.impl.Usuario#getUltimoAcesso()
     */
    @Override
    public Date getUltimoAcesso() {

        return ultimoAcesso;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.usuario.impl.Usuario#setUltimoAcesso(java.util.Date)
     */
    @Override
    public void setUltimoAcesso(Date ultimoAcesso) {

        this.ultimoAcesso = ultimoAcesso;
    }

    @Override
    public String getSobrenome() {
        return this.sobrenome;
    }

    @Override
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public String getPapelUsuario() {

        return PAPEL;
    }

}
