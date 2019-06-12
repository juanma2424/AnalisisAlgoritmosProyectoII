package logic;

import encriptacion.IAlgorithm;
import encriptacion.Security;
import lib.Constants;

public class GameLogic implements Constants {

    private Player jugador;
    private Player contrincante;
    private Card[] deck;
    private Card[] jugada1;
    private Card[] jugada2;
    private Card[] jugadaCont1;
    private Card[] jugadaCont2;

    public GameLogic() {
        jugador = new Player();
        contrincante = new Player();
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
    
    public void setNameContr(String pName){
        contrincante.setName(pName);
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
    
    public void setSelectedCardCont(String pName, String pDescription, String pDescripEncrypted, String pKey1, String pKey2, int pPos, boolean pJugada) {
        if (pJugada) {
            jugadaCont1[pPos] = new Card(pName, pDescription, pDescripEncrypted, pKey1, pKey2);
        } else {
            jugadaCont2[pPos] = new Card(pName, pDescription, pDescripEncrypted, pKey1, pKey2);
        }
    }
    
    public int decodeCard(Card pCard, int pType){
        String[] types = {"Sha256","MD5","TresDes","AES","Plain","RSA","Pgp"};
        boolean found = true;
        IAlgorithm alg;
        String text;
        while(found){
            alg = Security.generateAlgorithm(types[pType%TOTAL_CARDS]);
            text = alg.decrypt(pCard.getDescripEncrypted(), pCard.getKey1());
            if(text.equals(pCard.getDescription())){
                pType++;
                break;
            }
            text = alg.decrypt(pCard.getDescripEncrypted(), pCard.getKey2());
            found = !text.equals(pCard.getDescription());
            pType++;
        }
        pType--;
        return pType;
    }
}
