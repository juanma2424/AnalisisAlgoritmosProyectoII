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
        game = new GameLogic();

        numCards = 0;
        comms = new ConjuroComms(this);

        login = new Login();
        sendController();
        login.setLocationRelativeTo(null);
        login.setVisible(true);

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
    
    public void sendMoves(){
        Card[] select = game.getJugada1().clone();
        String msg = "1,";
        for (int index = 0; index < 6; index++) {
            msg += "Name"+index+"=" + select[index%3].getName() + ",Description"+index+"=" + select[index%3].getDescription() + ",DescripEncryp"+index+"=" + select[index%3].getDescripEncrypted() + ",Key1"+index+"=" + select[index%3].getKey1() + ",Key2"+index+"=" + select[index%3].getKey2()+",";
            if(index == 2){
                select = game.getJugada2();
            }
        }
        msg = msg.substring(0, msg.length()-1);
        System.out.println("////"+msg);
        comms.sendMessage(msg);
    }

    public void startGame() {
        comms.iniciarJuegoNuevo();
        generateCard();
    }

    public void searchGame() {
        if (!comms.conectarAJuego(HOST)) {
            login.getConnect();
        }else{
            login.initGame();
            generateCard();
        }
    }
    
    public void setCardCont(String pName, String pDescription, String pDescripEncrypted, String pKey1, String pKey2, int pPos, boolean pJugada) {
        System.out.println("Name4=" + pName + ",Description=" + pDescription + ",DescripEncryp=" + pDescripEncrypted + ",Key1=" + pKey1 + ",Key2=" + pKey2 + "dsd");
        game.setSelectedCardCont(pName, pDescription, pDescripEncrypted, pKey1, pKey2, pPos, pJugada);
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
    
    
     public void cleanMove(int pData){
         game.cleanMove(pData);
     }
     
     public void setCartTXT(){
         
     }  
}
