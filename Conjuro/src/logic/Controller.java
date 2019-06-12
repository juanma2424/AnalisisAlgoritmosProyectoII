package logic;

import conjuronet.ConjuroComms;
import files.ReadFile;
import lib.Constants;

public class Controller implements Constants {
    private GameLogic game;
    private int numCards;
    private ConjuroComms comms;

    public Controller() {
        game = new GameLogic();
        numCards = 0;
        comms = new ConjuroComms();
    }

    public void insertName(String pName) {
        game.setName(pName);
    }

    public boolean selectCard(int pType, boolean pJugada) {
        if (numCards < TOTAL_CARDS-1) {
            game.setSelectedCard(pType, numCards % JUGADA_NUMBER, pJugada);
            numCards++;
            return true;
        }
        return false;
    }
    
    public boolean startGame(){
        if(game.getName().isEmpty())
            return false;
        comms.iniciarJuegoNuevo();
        return true;
    }
    
    public boolean searchGame(){
        if(game.getName().isEmpty())
            return false;
        comms.conectarAJuego(HOST);
        return true;
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
            game.insertCard(parts[0], parts[1], types[count], count);
            count++;
        }
    }
}