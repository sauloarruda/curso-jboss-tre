package br.eti.sauloarruda.bingo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

/**
 *
 * @author sauloarruda
 */
@Entity
public class Cartela implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Boolean vencedor = Boolean.FALSE;
    @ManyToOne
    @JoinColumn(name = "bingo_id", nullable = false)
    private Bingo bingo;
    @OneToMany(fetch= FetchType.EAGER)
    @OrderBy("numero")
    private Set<CartelaNumero> numeros = new HashSet<CartelaNumero>();

    Cartela() {
    }

    Cartela(Bingo bingo) {
        this.bingo = bingo;
    }

    public Set<CartelaNumero> getNumeros() {
        return numeros;
    }
    
    public Bingo getBingo() {
        return bingo;
    }

    public Integer getId() {
        return id;
    }
    
    void setVencedor(boolean vencedor) {
        this.vencedor = vencedor;
    }
    
    public boolean isVencedor() {
        return vencedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cartela)) {
            return false;
        }
        Cartela other = (Cartela) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.eti.sauloarruda.bingo.Cartela[ id=" + id + " ]";
    }
}
