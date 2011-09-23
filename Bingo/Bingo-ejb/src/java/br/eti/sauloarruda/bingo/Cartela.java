package br.eti.sauloarruda.bingo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Cartela implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Bingo bingo;
    private Boolean vencedor = Boolean.FALSE;
    
    @OneToMany(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    @OrderBy("numero")
    private List<CartelaNumero> numeros = new ArrayList<CartelaNumero>();

    public Cartela() {}
    
    Cartela(Bingo bingo) {
        this.bingo = bingo;
    }
    
    public List<CartelaNumero> getNumeros() {
        return numeros;
    }
    
    public Integer getId() {
        return id;
    }
    
    public Boolean getVencedor() {
        return vencedor;
    }

    void vencedor() {
        vencedor = Boolean.TRUE;
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
