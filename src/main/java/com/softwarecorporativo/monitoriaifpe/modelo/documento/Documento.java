/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.modelo.documento;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Edmilson Santana
 */
@Entity
@Table(name = "tb_documento")
@Access(AccessType.FIELD)
public class Documento implements Serializable {

    private static final long serialVersionUID = -8282569756939863496L;

    @Id
    @NotEmpty
    @Column(length = 64, name = "txt_hash", nullable = false)
    private String hashConteudo;

    @Lob
    @Column(name = "conteudo", nullable = false)
    private byte[] conteudo;

    @NotNull
    @Column(name = "dt_emissao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEmissao;

    public String getHashConteudo() {
        return hashConteudo;
    }

    public void setHashConteudo(String hashConteudo) {
        this.hashConteudo = hashConteudo;
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

}
