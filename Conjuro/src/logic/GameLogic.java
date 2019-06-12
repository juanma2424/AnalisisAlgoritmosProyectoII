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

    public GameLogic(Controller pController) {
        jugador = new Player();
        globalController = pController;
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

    private void fillHash() {
        types.put("Sha256", 0);
        types.put("MD5", 1);
        types.put("TresDes", 2);
        types.put("AES", 3);
        types.put("Plain", 4);
        types.put("RSA", 5);
        types.put("Pgp", 6);
    }

    public void setNameContr(String pName) {
        contrincante.setName(pName);
    }

    public Card[] getJugada1() {
        return jugada1;
    }

    public Card[] getJugada2() {
        return jugada2;
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

    public void initDecode() {
        Card[] jugadaCont = jugadaCont1.clone();
        Card[] jugada = jugada1.clone();
        int typeCard;
        for (int index = 0; index < JUGADA_NUMBER*2; index++) {
            typeCard = decodeCard(jugadaCont[index%JUGADA_NUMBER], types.get(jugada[index%JUGADA_NUMBER].getType()));
            jugadaCont[index%JUGADA_NUMBER].setType(typeCard);
            globalController.discoverCard(typeCard);
            if (index == JUGADA_NUMBER - 1) {
                jugadaCont = jugadaCont2.clone();
                jugada = jugada2.clone();
            }
        }
    }

    private int decodeCard(Card pCard, int pType) {
        String[] types = {"Sha256", "MD5", "TresDes", "AES", "Plain", "RSA", "Pgp"};
        boolean found = true;
        IAlgorithm alg;
        String text;
        while (found) {
            alg = Security.generateAlgorithm(types[pType % TOTAL_CARDS]);
            alg.setText(pCard.getDescription());
            text = alg.decrypt(pCard.getDescripEncrypted(), pCard.getKey1());
            if (text.equals(pCard.getDescription())) {
                pType++;
                break;
            }
            text = alg.decrypt(pCard.getDescripEncrypted(), pCard.getKey2());
            found = !text.equals(pCard.getDescription());
            pType++;
        }
        pType--;
        return pType%TOTAL_CARDS;
    }

    public void cleanMove(int pData) {
        if (pData == 0) {
            for (int index = 0; index < 3; index++) {
                jugada1[index] = null;
            }

        } else {

            for (int index = 0; index < 3; index++) {
                jugada2[index] = null;
            }
        }
    }
}
