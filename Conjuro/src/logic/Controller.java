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
        numCards = DATA_CERO;
        comms = new ConjuroComms(this);
        window = null;
        login = new Login();
        sendController();
        new Thread(this).start();
        isServer = false;
    }

    public void insertName(String pName) {
        game.setName(pName);// SET NAME TO PLAYER
    }

    // SEPARED SIX CARDS IN TWO MOVES
    public boolean selectCard(int pType, boolean pJugada) {
        if (numCards < SELECT_CARDS) {
            game.setSelectedCard(pType, numCards % JUGADA_NUMBER, pJugada);
            numCards++;
            return true;
        }
        return false;
    }

    //
    public void sendMoves() {
        Card[] select = game.getJugada1().clone();// fisrt move
        String msg = "1,";//start msg
        Random rand = new Random();
        String key1;
        String key2;
        int pos = DATA_CERO;
        for (int index = DATA_CERO; index < SELECT_CARDS; index++) {
            pos = index % JUGADA_NUMBER;
            key1 = select[pos].getKey1();
            key2 = select[pos].getKey2();
            if(rand.nextInt() < DATA_CEROFIVE){
                key1 = select[pos].getKey2();
                key2 = select[pos].getKey1();
            }
            
            msg += "Name" + index + "=" + select[pos].getName() 
            + ",Description" + index + "=" + select[pos].getDescription() 
            + ",DescripEncryp" + index + "=" + select[pos].getDescripEncrypted() 
            + ",Key1" + index + "=" + key1 + ",Key2" + index + "=" + key2 + ",";
            
            if (index == JUGADA_NUMBER-DATA_ONE) {
                select = game.getJugada2();// past a next move 
            }
        }
        msg = msg.substring(DATA_CERO, msg.length() - DATA_ONE);
        
        comms.sendMessage(msg);
        decrypt = true;
        sendName();
        if(isServer)
           sendKey();
    }

    //levanta un server 
    public void startGame() {
        comms.iniciarJuegoNuevo();// levanta server y lo pone a la escucha 
        generateCard();
        FileManagement fileKey = new FileManagement();// lee archivo de llaves
        game.setKey(fileKey.readKeys());// set de la llave seleccionada aleatoriamenre
        isServer = true;
    }

    
    public void searchGame() {
        if (!comms.conectarAJuego(HOST)) {// if not are servers
            login.getConnect();// show msg
        } else {
            login.initGame();// new window
            generateCard(); 
        }
    }

    
    // SET CARDS CONDIF MY AND CONTRICAND
    public void setCardCont(String pName, String pDescription, String pDescripEncrypted, 
                                  String pKey1, String pKey2, int pPos, boolean pJugada) {
        
        window.appendMoves(("Name=" + pName + ",Description=" + pDescription + 
                ",DescripEncryp=" + pDescripEncrypted + ",Key1=" + pKey1 + ",Key2=" + pKey2 + "dsd"));// PRINCIPAL BOARD
        
        game.setSelectedCardCont(pName, pDescription, pDescripEncrypted, pKey1, pKey2, pPos, pJugada);
    }

    public String getMovVrs() {
        return movVrs;
    }

    public void setMovVrs(String movVrs) {
        this.movVrs = movVrs;
    }

    public void generateCard() {
        String[] types = {"Sha256", "MD5", "TresDes", "AES", "Plain", "RSA", "Pgp"};// tipo de carta
        ReadFile fr = new ReadFile();
        String text = fr.readDescription();
        String[] description = text.split(LIMIT_DESCRIPTION);// descripcion de cada carta
        int count = DATA_CERO;
        String[] parts;
        while (count < TOTAL_CARDS) {
            parts = description[count].split(LIMIT_NAME);
                                  // name   , descripction   ,    type     , num             
            game.insertCard(parts[DATA_CERO], parts[DATA_ONE], types[count], count);// gamelogic 
            count++;                             
        }
    }

    public void sendController() {
        login.setController(this);// set controlle to login
    }

    public void setGetCards() {
        getCards = true;//IF HAVE A CARDS
    }

    public void cleanMove(int pData) {
        game.cleanMove(pData);// clean in game logic
        numCards = DATA_CERO;
    }

    public void findCard(int pType) {
        String[] types = {"Sha256", "MD5", "TresDes", "AES", "Plain", "RSA", "Pgp"};
        window.appendFindCards(types[pType]);//SHOW A FIND CARD OPPONED
    }
    
    public void setKey(String pKey) {
        game.setKey(pKey);//STE KEY TO GAME LOGIC
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

    
    public void sendConjuro(String pConjuro){
        String msg = "2,Conjuro="+pConjuro;
        comms.sendMessage(msg);
    }
    
    public void setConjuro(String pConjuro){
        window.viewConjuro(pConjuro);// SEND CONJURO TO GUI
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
        window = new Cliente(this);// windows game ande set controller
    }

    public void setNameContr(String pName) {
        game.setNameContr(pName);
        window.setNameVS(pName);
    }
}
