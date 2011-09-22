package br.eti.sauloarruda.bingo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sauloarruda
 */
@Stateless(name = "jogo")
public class JogoBean implements Jogo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public BingoNumero sortearNumero(Integer bingoId) {
        Bingo bingo = buscarJogo(bingoId);
        List<Integer> disponiveis = new ArrayList<Integer>();
        for (int i = 1; i < 30; i++) {
            if (!bingo.isSorteado(i)) {
                disponiveis.add(i);
            }
        }
        if (disponiveis.size() == 0) {
            return null;
        }
        int random = new Random().nextInt(disponiveis.size());
        BingoNumero numero = new BingoNumero(bingo, disponiveis.get(random));
        em.persist(numero);
        bingo.getNumerosSorteados().add(numero.getNumero());
        bingo.getNumeros().add(numero);
        marcarCartelas(bingo);
        return numero;
    }

    private void marcarCartelas(Bingo bingo) {
        for (Cartela cartela : bingo.getCartelas().toArray(new Cartela[bingo.getCartelas().size()])) {
            int qtdSorteados = 0;
            for (CartelaNumero cartelaNumero : cartela.getNumeros().toArray(new CartelaNumero[cartela.getNumeros().size()])) {
               if (bingo.getNumerosSorteados().contains(cartelaNumero.getNumero())) {
                   cartelaNumero.setSorteado(true);
                   em.persist(cartelaNumero);
               }
               if (cartelaNumero.isSorteado()) {
                   qtdSorteados++;
               }
            }
            if (qtdSorteados == 9) {
                cartela.setVencedor(true);
                em.persist(cartela);
                bingo.setVencedora(cartela);
                em.persist(bingo);
            }
        }
    }
    
    public Cartela novaCartela(Integer bingoId) {
        Cartela cartela = new Cartela(buscarJogo(bingoId));
        em.persist(cartela);
        for (int i = 0; i < 9; i++) {
            Integer random = sortearNumero(i, cartela.getNumeros());
            CartelaNumero numero = new CartelaNumero(cartela, random);
            em.persist(numero);
            cartela.getNumeros().add(numero);
        }
        cartela = em.find(Cartela.class, cartela.getId()); // reorder
        return cartela;
    }

    private Integer sortearNumero(int i, Set<CartelaNumero> numeros) {
        Integer random = new Random().nextInt(8);
        if (i > 2 && i < 6) {
            random = random + 11;
        } else if (i > 5) {
            random = random + 21;
        } else {
            random++;
        }
        
        for (CartelaNumero numero: numeros.toArray(new CartelaNumero[numeros.size()])) {
            if (numero.getNumero() == random) {
                random = sortearNumero(i, numeros);
            }
        }
        return random;
    }

    public Bingo novoJogo() {
        Bingo bingo = new Bingo();
        em.persist(bingo);
        return bingo;
    }

    public Bingo buscarJogo(Integer id) {
        return em.find(Bingo.class, id);
    }

    public Cartela buscarCartela(Integer id) {
        return em.find(Cartela.class, id);
    }
}
