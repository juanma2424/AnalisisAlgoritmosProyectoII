package conjuronet;

import lib.*;
import logic.Controller;
import net.*;

public class ConjuroComms implements IObserver, Constants {

    private ClientSocket client;
    private Controller globalController;

    public ConjuroComms(Controller pController) {
        globalController = pController;
    }

    public boolean conectarAJuego(String pHost) {
        client = new ClientSocket();
        boolean getMake = client.makeClient(pHost, PORT_NUMBER);
        client.addObserver(this);
        return getMake;
        /*ConjuroMsg msg = new ConjuroMsg("0,Nombre=juanmaa,n=j1");
        client.sendMessage(msg);*/
    }

    public void iniciarJuegoNuevo() {
        ServerNet.startListening(this);
    }

    public void sendMessage(String pMsg) {
        ConjuroMsg msg = new ConjuroMsg(pMsg);
        client.sendMessage(msg);
    }

    public void notify(Object pData) {
        ConjuroMsg msg = (ConjuroMsg) pData;
        switch (msg.getType()) {
            case PLAYER_NAME: {
                System.out.println("aaaa");
                break;
            }
            case CARDS_SELECTED: {
                for (int index = 0; index < 6; index++) {
                    if(index < 3)
                        globalController.setCardCont(msg.getValue("Name" + index), msg.getValue("Description" + index), msg.getValue("DescripEncryp" + index), msg.getValue("Key1" + index), msg.getValue("Key2" + index),index%3,true);
                    else
                        globalController.setCardCont(msg.getValue("Name" + index), msg.getValue("Description" + index), msg.getValue("DescripEncryp" + index), msg.getValue("Key1" + index), msg.getValue("Key2" + index),index%3,false);
                }

                break;
            }
            default: {
                System.out.println("nooo");
            }
        }
    }

    public void setClientServer(ClientSocket pClient) {
        this.client = pClient;
    }
}