package conjuronet;

import lib.*;
import logic.Controller;
import net.*;

public class ConjuroComms implements IObserver, Constants {

    private ClientSocket client;
    private Controller globalController;

    // CONSTRUCTOR WITH CONTROLLER TIPE
    public ConjuroComms(Controller pController) {
        globalController = pController;
    }

    
    // CONEX CLIENT TO SERVER 
    public boolean conectarAJuego(String pHost) {
        client = new ClientSocket();
        boolean getMake = client.makeClient(pHost, PORT_NUMBER);
        client.addObserver(this);
        return getMake;
    }

    // START SERVER
    public void iniciarJuegoNuevo() {
        ServerNet.startListening(this);
    }

    
    public void sendMessage(String pMsg) {
        ConjuroMsg msg = new ConjuroMsg(pMsg);
        client.sendMessage(msg);// SET MSG BY SOCKET
    }

    // SET DIFERENT MSG
    public void notify(Object pData) {
        ConjuroMsg msg = (ConjuroMsg) pData;
        switch (msg.getType()) {
            case PLAYER_NAME: {
                globalController.setNameContr(msg.getValue("Name"));//SET NAME TO PLAYER 
                break;
            }
            case CARDS_SELECTED: {
                for (int index = 0; index < 6; index++) {
                    if(index < 3)
                        // FIRST MOVE
                        globalController.setCardCont(msg.getValue("Name" + index), 
                        msg.getValue("Description" + index), msg.getValue("DescripEncryp" + index),
                        msg.getValue("Key1" + index), msg.getValue("Key2" + index),index%3,true);
                    else
                        // SECOND MOVE
                        globalController.setCardCont(msg.getValue("Name" + index),
                        msg.getValue("Description" + index), msg.getValue("DescripEncryp" + index), 
                        msg.getValue("Key1" + index), msg.getValue("Key2" + index),index%3,false);
                }
                globalController.setGetCards();//CHANGE BOOL
                break;
            }
            case CARD_FOUND: {
                globalController.findCard(Integer.parseInt(msg.getValue("Type")));//SET A FIND CARD OPPONED
                break;
            }
            case KEY_MSG: {
                globalController.setKey(msg.getValue("Key"));// SET KEY 
                break;
            }
            case CONJURO: {
                globalController.setConjuro(msg.getValue("Conjuro"));// SET CONJURO
                break;
            }
            default: {
       
            }
        }
    }

    public void setClientServer(ClientSocket pClient) {
        this.client = pClient;
    }
}