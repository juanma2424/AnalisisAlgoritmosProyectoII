package logic;

import GUI.Cliente;
import GUI.Login;
import conjuronet.ConjuroComms;
import files.FileManagement;
import files.ReadFile;
import java.util.Random;
import lib.Constants;
import lib.Logger;

public class Controller implements Constants, Runnable {

    private GameLogic game;
    private int numCards;
    private ConjuroComms comms;
    private Login login;
    private boolean decrypt;
    private boolean getCards;
    private String movVrs;
    private Cliente window;
    private boolean isServer;

    public Controller() {
        game = new GameLogic(this);
        decrypt = false;
        getCards = false;
        numCards = 0;
        comms = new ConjuroComms(this);
        window = null;
        login = new Login();
        sendController();
        new Thread(this).start();
        isServer = false;
    }

    public void insertName(String pName) {
        game.setName(pName);
    }

    public boolean selectCard(int pType, boolean pJugada) {
        if (numCards < SELECT_CARDS) {
            game.setSelectedCard(pType, numCards % JUGADA_NUMBER, pJugada);
            numCards++;
            return true;
        }
        return false;
    }

    public void sendMoves() {
        Card[] select = game.getJugada1().clone();
        String msg = "1,";
        Random rand = new Random();
        String key1;
        String key2;
        int pos = 0;
        for (int index = 0; index < SELECT_CARDS; index++) {
            pos = index % JUGADA_NUMBER;
            key1 = select[pos].getKey1();
            key2 = select[pos].getKey2();
            if(rand.nextInt() < 0.5){
                key1 = select[pos].getKey2();
                key2 = select[pos].getKey1();
            }
            msg += "Name" + index + "=" + select[pos].getName() + ",Description" + index + "=" + select[pos].getDescription() + ",DescripEncryp" + index + "=" + select[pos].getDescripEncrypted() + ",Key1" + index + "=" + key1 + ",Key2" + index + "=" + key2 + ",";
            if (index == JUGADA_NUMBER-1) {
                select = game.getJugada2();
            }
        }
        msg = msg.substring(0, msg.length() - 1);
        comms.sendMessage(msg);
        decrypt = true;
        sendName();
        if(isServer)
            sendKey();
    }

    public void startGame() {
        comms.iniciarJuegoNuevo();
        generateCard();
        FileManagement fileKey = new FileManagement();
        game.setKey(fileKey.readKeys());
        isServer = true;
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

    public void setGetCards() {
        getCards = true;
    }

    public void cleanMove(int pData) {
        game.cleanMove(pData);
        numCards = 0;
    }

    public void findCard(int pType) {
        String[] types = {"Sha256", "MD5", "TresDes", "AES", "Plain", "RSA", "Pgp"};
        window.appendFindCards(types[pType]);
    }
    
    public void setKey(String pKey) {
        game.setKey(pKey);
        sendName();
    }
    
    public void sendKey() {
        String msg = "4,Key="+game.getKey();
        comms.sendMessage(msg);
    }
    
    public void sendName(){
        String msg = "0,Name="+game.getName();
        comms.sendMessage(msg);
    }

    public void discoverCard(int pType) {
        String[] types = {"Sha256", "MD5", "TresDes", "AES", "Plain", "RSA", "Pgp"};
        String msg = "3,Type=" + pType;
        comms.sendMessage(msg);
        window.appendFindCardsC(types[pType]);
    }

    public void discoverHalfKey() {

    }
    
    public void sendConjuro(String pConjuro){
        String msg = "2,Conjuro="+pConjuro;
        comms.sendMessage(msg);
    }
    
    public void setConjuro(String pConjuro){
        window.viewConjuro(pConjuro);
    }

    public void discoverAllKey() {

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
        window.viewKey(game.getKey());

    }

    public void initWindow() {
        window = new Cliente(this);
    }

    public void setNameContr(String pName) {
        game.setNameContr(pName);
        window.setNameVS(pName);
    }
}
