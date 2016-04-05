package com.softwarecorporativo.monitoriaifpe.usuario;

import com.softwarecorporativo.monitoriaifpe.negocio.EntidadeNegocio;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "TB_USUARIO")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "PAPEL_USUARIO", discriminatorType = DiscriminatorType.STRING, length = 1)
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "USUARIO_ID"))})
@Access(AccessType.FIELD)
public abstract class Usuario extends EntidadeNegocio  {

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

   
    public String getLogin() {

        return login;
    }

  
    public String getSenha() {

        return senha;
    }

  
    public void setLogin(String login) {

        this.login = login;
    }

    
    public void setSenha(String senha) {

        this.senha = senha;
    }

 
    public String getEmail() {

        return email;
    }

   
    public void setEmail(String email) {

        this.email = email;
    }

  
    public void setNome(String nome) {

        this.nome = nome;
    }

   
    public String getNome() {

        return nome;
    }


   
    public String getSobrenome() {
        return this.sobrenome;
    }

    
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

   

}
