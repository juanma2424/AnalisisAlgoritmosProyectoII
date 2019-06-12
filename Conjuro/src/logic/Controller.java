package logic;

import GUI.Login;
import conjuronet.ConjuroComms;
import files.ReadFile;
import lib.Constants;

public class Controller implements Constants {

    private GameLogic game;
    private int numCards;
    private ConjuroComms comms;
    Login login;

    public Controller() {
        System.out.println("xx");
        game = new GameLogic();
        System.out.println("xxx");
        numCards = 0;
        comms = new ConjuroComms();
        System.out.println("ar");
        login = new Login();

        login.setLocationRelativeTo(null);
        login.setVisible(true);

        System.out.println("ab");

    }

    public void insertName(String pName) {
        game.setName(pName);
    }

    public boolean selectCard(int pType, boolean pJugada) {
        if (numCards < TOTAL_CARDS - 1) {
            game.setSelectedCard(pType, numCards % JUGADA_NUMBER, pJugada);
            numCards++;
            return true;
        }
        return false;
    }

    public void startGame() {
        comms.iniciarJuegoNuevo();
    }

    public void searchGame() {
        if (!comms.conectarAJuego(HOST)) {
            login.getConnect();
        }
    }

    public void generateCard() {
        String[] types = {"Sha256", "MD5", "TresDes", "AES", "Plain", "RSA", "Pgp"};
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
