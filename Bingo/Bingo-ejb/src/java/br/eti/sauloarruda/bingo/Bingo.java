package br.eti.sauloarruda.bingo;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Bingo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToMany(fetch= FetchType.EAGER, mappedBy="bingo")
    @OrderBy("ordem")
    private Set<BingoNumero> numeros;
    @Transient
    private List<Integer> numerosList;
    @OneToMany(mappedBy="bingo")
    private Set<Cartela> cartelas;
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="cartela_vencedora_id")
    private Cartela vencedora;

    Bingo() {}
    
    public Integer getId() {
        return id;
    }

    public Set<BingoNumero> getNumeros() {
        return numeros;
    }

    public boolean isSorteado(Integer numero) {
        return getNumerosSorteados().contains(numero);
    }
    
    public Integer ordemUltimo() {
        return getNumerosSorteados().size();
    }
    
    void setVencedora(Cartela vencedora) {
        this.vencedora = vencedora;
    }

    public Cartela getVencedora() {
        return vencedora;
    }
    
    public List<Integer> getNumerosSorteados() {
        if (numerosList == null) {
            numerosList = new ArrayList<Integer>();
            Iterator<BingoNumero> it = numeros.iterator();
            while (it.hasNext()) {
                numerosList.add(it.next().getNumero());
            }
        }
        return numerosList;
    }
    
    Set<Cartela> getCartelas() {
        return cartelas;
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
