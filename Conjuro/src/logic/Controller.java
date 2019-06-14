package logic;

import GUI.Cliente;
import GUI.Login;
import conjuronet.ConjuroComms;
import files.ReadFile;
import lib.Constants;
import lib.Logger;

public class Controller implements Constants, Runnable {

    private GameLogic game;
    private int numCards;
    private ConjuroComms comms;
    Login login;
    private boolean decrypt;
    private boolean getCards;
    private String movVrs;
    private Cliente window;

    public Controller() {
        game = new GameLogic(this);
        decrypt = false;
        getCards = false;
        numCards = 0;
        comms = new ConjuroComms(this);
        window = null;
        login = new Login();
        sendController();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        new Thread(this).start();

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

    public void sendMoves() {
        Card[] select = game.getJugada1().clone();
        String msg = "1,";

        for (int index = 0; index < 6; index++) {

            msg += "Name" + index + "=" + select[index % 3].getName() + ",Description" + index + "=" + select[index % 3].getDescription() + ",DescripEncryp" + index + "=" + select[index % 3].getDescripEncrypted() + ",Key1" + index + "=" + select[index % 3].getKey1() + ",Key2" + index + "=" + select[index % 3].getKey2() + ",";
            if (index == 2) {
                select = game.getJugada2();
            }
        }
        msg = msg.substring(0, msg.length() - 1);
        comms.sendMessage(msg);
        decrypt = true;
    }

    public void startGame() {
        comms.iniciarJuegoNuevo();
        generateCard();
    }

    public void searchGame() {
        if (!comms.conectarAJuego(HOST)) {
            login.getConnect();
        } else {
            login.initGame();
            generateCard();
        }
    }

    public void setCardCont(String pName, String pDescription, String pDescripEncrypted, String pKey1, String pKey2, int pPos, boolean pJugada) {
        window.appendMoves(("Name=" + pName + ",Description=" + pDescription + ",DescripEncryp=" + pDescripEncrypted + ",Key1=" + pKey1 + ",Key2=" + pKey2 + "dsd"));
        game.setSelectedCardCont(pName, pDescription, pDescripEncrypted, pKey1, pKey2, pPos, pJugada);
    }

    public String getMovVrs() {
        return movVrs;
    }

    public void setMovVrs(String movVrs) {
        this.movVrs = movVrs;
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

    public void sendController() {
        login.setController(this);
    }
    
    public void setGetCards(){
        getCards = true;
    }

    public void cleanMove(int pData) {
        game.cleanMove(pData);
        numCards = 0;
    }

    public void setCartTXT() {

    }
    
    public void findCard(int pType){
        String[] types = {"Sha256", "MD5", "TresDes", "AES", "Plain", "RSA", "Pgp"};
        window.appendFindCards(types[pType]);
    }
    
    public void discoverCard(int pType){
        String msg = "3,Type="+pType;
        comms.sendMessage(msg);
    }
    
    public void discoverHalfKey(){
        
    }

    public void discoverAllKey(){
        
    }
    
    @Override
    public void run() {
        while (!decrypt || !getCards) {
            try {
                Thread.sleep(THREAD_SLEEP_TIME);
            } catch (Exception ex) {
                Logger.Log(ex.getMessage());
            }
        }
      game.initDecode();
    }

    public void initWindow() {
        window = new Cliente(this);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
