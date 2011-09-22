package br.eti.sauloarruda.bingo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author sauloarruda
 */
@Entity
public class BingoNumero implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private Integer ordem;
    private Integer numero;
    @ManyToOne
    @JoinColumn(name="bingo_id", nullable=false)
    private Bingo bingo;

    BingoNumero() {}
    
    BingoNumero(Bingo bingo, Integer numero) {
        this.ordem = bingo.ordemUltimo();
        this.bingo = bingo;
        this.numero = numero;
    }
    
    public Bingo getBingo() {
        return bingo;
    }
    
    public Integer getId() {
        return id;
    }

    public Integer getNumero() {
        return numero;
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
        if (!(object instanceof BingoNumero)) {
            return false;
        }
        BingoNumero other = (BingoNumero) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.eti.sauloarruda.bingo.BingoSorteio[ id=" + id + " ]";
    }
    
}
