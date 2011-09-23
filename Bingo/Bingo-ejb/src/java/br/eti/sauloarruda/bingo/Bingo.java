package br.eti.sauloarruda.bingo;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Bingo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToMany(cascade= CascadeType.ALL)
    private List<BingoNumero> numeros = new ArrayList<BingoNumero>();
    @OneToMany(mappedBy="bingo")
    private List<Cartela> cartelas;
    @Transient
    private List<Cartela> vencedores;

    public Integer getId() {
        return id;
    }

    public List<BingoNumero> getNumeros() {
        return numeros;
    }

    public List<Cartela> getCartelas() {
        return cartelas;
    }
    
    public List<Cartela> getVencedores() {
        vencedores = new ArrayList<Cartela>();
        for (Cartela cartela : cartelas) {
            if (cartela.getVencedor()) {
                vencedores.add(cartela);
            }
        }
        return vencedores;
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
        if (!(object instanceof Bingo)) {
            return false;
        }
        Bingo other = (Bingo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.eti.sauloarruda.bingo.Bingo[ id=" + id + " ]";
    }
}
