package br.eti.sauloarruda.bingo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class JogoBean implements Jogo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Bingo novoBingo() {
        Bingo bingo = new Bingo();
        em.persist(bingo);
        return bingo;
    }

    public Bingo buscarBingo(Integer id) {
        Bingo bingo = em.find(Bingo.class, id);
        bingo.getNumeros().iterator(); // EAGER Loading
        bingo.getVencedores().iterator();
        return bingo;
    }

    public Cartela novaCartela(Integer bingoId) {
        Cartela cartela = new Cartela(buscarBingo(bingoId));
        for (int i = 0; i < 9; i++) {
            cartela.getNumeros().add(
                    new CartelaNumero(cartela, sortearNumero(cartela, i)));
        }

        em.persist(cartela);
        cartela = buscarCartela(cartela.getId()); // reordenar marotamente...
        return cartela;
    }

    private Integer sortearNumero(Cartela cartela, int i) {
        Integer numero;
        Integer random = new Random().nextInt(9);
        if (i > 5) {
            numero = random + 21;
        } else if (i > 2) {
            numero = random + 11;
        } else {
            numero = random + 1;
        }

        // Garante que não vai repetir
        for (CartelaNumero cartelaNumero : cartela.getNumeros()) {
            if (cartelaNumero.getNumero() == numero) {
                numero = sortearNumero(cartela, i);
            }
        }

        return numero;
    }

    public Cartela buscarCartela(Integer id) {
        return em.find(Cartela.class, id);
    }

    public BingoNumero sortear(Integer id) {
        Bingo bingo = buscarBingo(id);
        BingoNumero numero = sortearNumero(bingo);
        atualizarCartelas(numero);
        em.persist(bingo);
        return numero;
    }
    
    private BingoNumero sortearNumero(Bingo bingo) {
        List<Integer> indisponiveis = new ArrayList<Integer>();
        for (BingoNumero bingoNumero : bingo.getNumeros()) {
            indisponiveis.add(bingoNumero.getNumero());
        }

        List<Integer> disponiveis = new ArrayList<Integer>();
        for (int i = 0; i < 29; i++) {
            if (!indisponiveis.contains(i)) {
                disponiveis.add(i);
            }
        }
        
        Integer random = new Random().nextInt(disponiveis.size());
        BingoNumero bingoNumero = new BingoNumero(bingo, 
                disponiveis.get(random));
        bingo.getNumeros().add(bingoNumero);
        return bingoNumero;
    }
    
    private void atualizarCartelas(BingoNumero bingoNumero) {
        for (Cartela cartela : bingoNumero.getBingo().getCartelas()) {
            int qtdSorteados = 0;
            for (CartelaNumero numero : cartela.getNumeros()) {
                if (numero.getNumero().equals(bingoNumero.getNumero())) {
                    numero.sorteado();
                    em.persist(numero);
                }
                if (numero.getSorteado()) {
                    qtdSorteados++;
                }
            }
            if (qtdSorteados == 9) {
                cartela.vencedor();
                em.persist(cartela);
            }
        }
    }
}
