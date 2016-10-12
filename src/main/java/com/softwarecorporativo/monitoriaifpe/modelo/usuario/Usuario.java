package com.softwarecorporativo.monitoriaifpe.modelo.usuario;

import com.softwarecorporativo.monitoriaifpe.modelo.grupo.Grupo;
import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "tb_usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "papel_usuario", discriminatorType = DiscriminatorType.STRING, length = 1)
@DiscriminatorValue(value = "U")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "id_usuario"))})
@Access(AccessType.FIELD)
@NamedQuery(name = Usuario.USUARIO_POR_EMAIL, query = "select u from Usuario as u where u.email = ?1")
public class Usuario extends EntidadeNegocio {

    private static final long serialVersionUID = -2083194086477441520L;
    public static final String USUARIO_POR_EMAIL = "usuarioPorEmail";

    @NotBlank
    @Size(min = 1, max = 30)
    @Pattern(regexp = "^[A-Z]{1}[A-Za-z\\s]+$", message = "{com.softwarecorporativo.monitoriaifpe.usuario.nome}")
    @Column(name = "txt_nome", nullable = false)
    private String nome;

    @NotBlank
    @Size(min = 1, max = 30)
    @Email
    @Column(name = "txt_email", length = 30, nullable = false)
    private String email;

    @CPF
    @Column(name = "txt_cpf", nullable = false)
    private String cpf;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_usuario_grupo", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_grupo"))
    private List<Grupo> grupos;

    @NotBlank
    @Size(min = 6, max = 18)
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "{com.softwarecorporativo.monitoriaifpe.usuario.senha}")
    @Transient
    private String senha;
    
    @Column(name = "txt_senha", nullable = false)
    private String senhaHash;

    @Column(name = "txt_sal", nullable = false)
    private String sal;

    @PrePersist
    public void gerarHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            setSenha(gerarSal().concat(senha));
            digest.update(senha.getBytes(Charset.forName("UTF-8")));
            setSenhaHash(Base64.getEncoder().encodeToString(digest.digest()));
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String gerarSal() {
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
            byte[] randomBytes = new byte[32];
            secureRandom.nextBytes(randomBytes);
            setSal(Base64.getEncoder().encodeToString(randomBytes));
            return this.sal;
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getSenha() {

        return senha;
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

    public String getNomeCompleto() {
        return String.format("%s", nome);
    }

    public void adicionarGrupo(Grupo grupo) {
        if (grupos == null) {
            this.grupos = new ArrayList<>();
        }
        grupos.add(grupo);
    }

    protected void setSal(String sal) {
        this.sal = sal;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    private String getSenhaHash() {
        return senhaHash;
    }

    protected void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }
    
    

}
