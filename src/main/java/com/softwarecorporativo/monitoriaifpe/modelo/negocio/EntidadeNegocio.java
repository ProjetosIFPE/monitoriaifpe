package com.softwarecorporativo.monitoriaifpe.modelo.negocio;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntidadeNegocio implements Serializable {

    private static final long serialVersionUID = 268142493479959195L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long chavePrimaria;

    public Long getChavePrimaria() {
        return chavePrimaria;
    }

    public void setChavePrimaria(Long chavePrimaria) {
        this.chavePrimaria = chavePrimaria;
    }

    //TO-DO:Rever hash
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

    public boolean isInativo() {
        return false;
    }

}
