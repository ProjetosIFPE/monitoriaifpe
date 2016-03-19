package com.softwarecorporativo.monitoriaifpe.negocio.impl;

import com.softwarecorporativo.monitoriaifpe.negocio.EntidadeNegocio;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;

@MappedSuperclass
public abstract class EntidadeNegocioImpl implements EntidadeNegocio, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1874851173773909132L;

    @Column(name = "ULTIMA_ALTERACAO", nullable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ultimaAlteracao;
    
    
    @PrePersist
    @PreUpdate
    public void setUltimaAlteracao() {
        this.ultimaAlteracao = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long chavePrimaria;

    @Override
    public Date getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    @Override
    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    @Override
    public long getChavePrimaria() {
        return chavePrimaria;
    }

    @Override
    public void setChavePrimaria(long chavePrimaria) {
        this.chavePrimaria = chavePrimaria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (int) (this.chavePrimaria ^ (this.chavePrimaria >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof EntidadeNegocioImpl)) {
            return false;
        }
        final EntidadeNegocioImpl other = (EntidadeNegocioImpl) object;

        if ((this.chavePrimaria == null && other.chavePrimaria != null) || (this.chavePrimaria != null && !this.chavePrimaria.equals(other.chavePrimaria))) {
            return false;
        }
        return true;
    }

}
