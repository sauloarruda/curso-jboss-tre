package br.eti.sauloarruda.bingo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CartelaNumero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Cartela cartela;
    private Integer numero;
    private Boolean sorteado = Boolean.FALSE;

    public CartelaNumero() {
    }

    CartelaNumero(Cartela cartela, Integer numero) {
        this.cartela = cartela;
        this.numero = numero;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumero() {
        return numero;
    }

    public Boolean getSorteado() {
        return sorteado;
    }

    void sorteado() {
        this.sorteado = Boolean.TRUE;
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
        if (!(object instanceof CartelaNumero)) {
            return false;
        }
        CartelaNumero other = (CartelaNumero) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.eti.sauloarruda.bingo.CartelaNumero[ id=" + id + " ]";
    }
}
