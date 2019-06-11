package logic;

import lib.Constants;

public class GameLogic implements Constants {

    private Player jugador;
    private Card[] deck;
    private Card[] jugada1;
    private Card[] jugada2;

    public GameLogic() {
        jugador = new Player();
        deck = new Card[TOTAL_CARDS];
        jugada1 = new Card[JUGADA_NUMBER];
        jugada2 = new Card[JUGADA_NUMBER];
    }
    
    public void setName(String pName){
        jugador.setName(pName);
    }

    public String getName(){
        return jugador.getName();
    }
    
    public void insertCard(String pName, String pDescription, String pType, int pPos) {
        deck[pPos] = new Card(pName, pDescription, pType);
    }

    public void setSelectedCard(int pType, int pPos, boolean pJugada) {
        if (pJugada) {
            jugada1[pPos] = deck[pType];
        } else {
            jugada2[pPos] = deck[pType];
        }
    }
}
