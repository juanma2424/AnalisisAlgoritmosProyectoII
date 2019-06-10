/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import files.ReadFile;
import java.util.StringTokenizer;
import lib.Constants;

/**
 *
 * @author adri-
 */
public class Controller implements Constants {

    private Player jugador;
    private Card[] deck;
    private Card[] jugada1;
    private Card[] jugada2;
    private int numCards;

    public Controller() {
        jugador = new Player();
        deck = new Card[TOTAL_CARDS];
        jugada1 = new Card[JUGADA_NUMBER];
        jugada2 = new Card[JUGADA_NUMBER];
        numCards = 0;
    }

    public void insertName(String pName) {
        jugador.setName(pName);
    }

    public boolean selectCard(int pType, boolean pJugada) {
        if (numCards < 6) {
            if (pJugada) {
                jugada1[numCards % JUGADA_NUMBER] = deck[pType];
            } else {
                jugada2[numCards % JUGADA_NUMBER] = deck[pType];
            }
            numCards++;
            return true;
        }
        return false;
    }

    public void generateCard() {
        String[] types = {"Sha256","MD5","TresDes","AES","Plain","RSA","Pgp"};
        ReadFile fr = new ReadFile();
        String text = fr.readDescription();
        String[] description = text.split(LIMIT_DESCRIPTION);
        int count = 0;
        String[] parts;
        while (count < TOTAL_CARDS) {
            parts = description[count].split(LIMIT_NAME);
            deck[count] = new Card(parts[0],parts[1],types[count]);
            System.out.println("Nombre: "+ deck[count].getName() + " Description: "+deck[count].getDescription() + "Tipo: " + types[count]);
            count++;
        }

    }

}
