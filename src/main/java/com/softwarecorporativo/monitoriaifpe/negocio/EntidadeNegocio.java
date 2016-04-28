package com.softwarecorporativo.monitoriaifpe.negocio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class EntidadeNegocio implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1874851173773909132L;

    @Column(name = "ULTIMA_ALTERACAO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaAlteracao;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long chavePrimaria;

    @PrePersist
    @PreUpdate
    public void setUltimaAlteracao() {
        this.setUltimaAlteracao(Calendar.getInstance().getTime());

    }

    public Date getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Long getChavePrimaria() {
        return chavePrimaria;
    }

    public void setChavePrimaria(Long chavePrimaria) {
        this.chavePrimaria = chavePrimaria;
    }

    @Override
    public int hashCode() {

        int result = 5;
        int hash = this.chavePrimaria == null ? 0 : this.chavePrimaria.hashCode();
        return (37 * result) + hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof EntidadeNegocio)) {
            return false;
        }
        final EntidadeNegocio other = (EntidadeNegocio) object;

        if ((this.chavePrimaria == null && other.chavePrimaria != null) || (this.chavePrimaria != null && !this.chavePrimaria.equals(other.chavePrimaria))) {
            return false;
        }
        return true;
    }

}
