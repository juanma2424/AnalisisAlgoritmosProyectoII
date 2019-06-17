package logic;

import encriptacion.IAlgorithm;
import encriptacion.Security;
import java.util.Hashtable;
import lib.Constants;

public class GameLogic implements Constants {

    private Player jugador;
    private Player contrincante;
    private Card[] deck;
    private Card[] jugada1;
    private Card[] jugada2;
    private Card[] jugadaCont1;
    private Card[] jugadaCont2;
    private Hashtable<String, Integer> types;
    private Controller globalController;
    private String key;
    private boolean gameOver;

    public GameLogic(Controller pController) {
        jugador = new Player();
        globalController = pController;
        gameOver = false;
        contrincante = new Player();
        deck = new Card[TOTAL_CARDS];
        jugada1 = new Card[JUGADA_NUMBER];
        jugada2 = new Card[JUGADA_NUMBER];
        jugadaCont1 = new Card[JUGADA_NUMBER];
        jugadaCont2 = new Card[JUGADA_NUMBER];
        types = new Hashtable<String, Integer>();
        fillHash();
    }

    public void setName(String pName) {
        jugador.setName(pName);
    }

    public String getName() {
        return jugador.getName();
    }
    
    public void setKey(String pKey) {
        key = pKey;//SAVE KEY
    }

    public String getKey() {
        return key;
    }
    
    private void fillHash() {
        types.put("Sha256", DATA_CERO);
        types.put("MD5", DATA_ONE);
        types.put("TresDes", DATA_TWO);
        types.put("AES", DATA_THREE);
        types.put("Plain", DATA_FOUR);
        types.put("RSA", DATA_FIVE);
        types.put("Pgp", DATA_SIX);
    }

    public void setNameContr(String pName) {
        contrincante.setName(pName);
    }
    
    public void gameOverLoser(){
        gameOver = true;
    }

    public Card[] getJugada1() {
        return jugada1;
    }

    public Card[] getJugada2() {
        return jugada2;
    }

    // SAVE CARDS LIKE NAME DECRIPTION AND TYPE , IN DECK                         
    public void insertCard(String pName, String pDescription, String pType, int pPos) {
        deck[pPos] = new Card(pName, pDescription, pType);
    }

    
    // SAVE SELECT CARD IN TWO MOVES 
    public void setSelectedCard(int pType, int pPos, boolean pJugada) {
        if (pJugada) {
            jugada1[pPos] = deck[pType];
        } else {
            jugada2[pPos] = deck[pType];
        }
    }
    
    // SAVE SELECT CARD IN TWO MOVES OF CONT 
    public void setSelectedCardCont(String pName, String pDescription, String pDescripEncrypted, String pKey1, String pKey2, int pPos, boolean pJugada) {
        if (pJugada) {
            jugadaCont1[pPos] = new Card(pName, pDescription, pDescripEncrypted, pKey1, pKey2);
        } else {
            jugadaCont2[pPos] = new Card(pName, pDescription, pDescripEncrypted, pKey1, pKey2);
        }
    }

    // DECODE MOVES 
    public void initDecode() {
        Card[] jugadaCont = jugadaCont1.clone();
        Card[] jugada = jugada1.clone();
        int typeCard;
        for (int index = 0; index < SELECT_CARDS && !gameOver; index++) {
            typeCard = decodeCard(jugadaCont[index%JUGADA_NUMBER], types.get(jugada[index%JUGADA_NUMBER].getType()));
            if(typeCard == -1)
                break;
            jugadaCont[index%JUGADA_NUMBER].setType(typeCard);
            globalController.discoverCard(typeCard);
            if (index == JUGADA_NUMBER - 1) {
                jugadaCont = jugadaCont2.clone();
                jugada = jugada2.clone();
            }
        }
    }

    //DECODE CARD 
    private int decodeCard(Card pCard, int pType) {
        String[] types = {"Sha256", "MD5", "TresDes", "AES", "Plain", "RSA", "Pgp"};
        boolean found = true;
        IAlgorithm alg;
        String text;
        while (found) {
            if(gameOver)
                return -1;
            alg = Security.generateAlgorithm(types[pType % TOTAL_CARDS]);
            alg.setText(pCard.getDescription());
            alg.setKey(pCard.getKey2());
            text = alg.decrypt(pCard.getDescripEncrypted(), pCard.getKey1());
            if (text.equals(pCard.getDescription())) {
                pType++;
                break;
            }
            alg.setKey(pCard.getKey1());
            text = alg.decrypt(pCard.getDescripEncrypted(), pCard.getKey2());
            found = !text.equals(pCard.getDescription());
            pType++;
        }
        pType--;
        return pType%TOTAL_CARDS;
    }

    
    public void cleanMove(int pData) {
        if (pData == DATA_CERO) {// if clean a first move
            for (int index = DATA_CERO; index < JUGADA_NUMBER; index++) {
                jugada1[index] = null;//set first move null
            }
        } else {
            for (int index = DATA_CERO; index < JUGADA_NUMBER; index++) {
                jugada2[index] = null;//set first move null
            }
        }
    }
}
