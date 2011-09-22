package br.eti.sauloarruda.bingo;

import javax.ejb.Remote;

/**
 *
 * @author sauloarruda
 */
@Remote
public interface Jogo {

    BingoNumero sortearNumero(Integer bingoId);
    
    Bingo novoJogo();
    
    Bingo buscarJogo(Integer id);
        
    Cartela novaCartela(Integer bingoId);
    
    Cartela buscarCartela(Integer id);
}
