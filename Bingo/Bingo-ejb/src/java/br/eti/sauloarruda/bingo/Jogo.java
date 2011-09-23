package br.eti.sauloarruda.bingo;

import javax.ejb.Local;

@Local
public interface Jogo {

    Bingo novoBingo();

    public Bingo buscarBingo(Integer id);

    public Cartela novaCartela(Integer bingoId);

    public Cartela buscarCartela(Integer id);

    public BingoNumero sortear(Integer id);
    
}
