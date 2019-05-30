package conjuronet;

import lib.*;
import net.*;

public class ConjuroComms implements IObserver, Constants {

    private ClientSocket client;

    public ConjuroComms() {

    }

    public void conectarAJuego(String pHost) {

        client = new ClientSocket(pHost, PORT_NUMBER);
        client.addObserver(this);
        System.out.println("envia msn");
        ConjuroMsg msn = new ConjuroMsg("1");
        client.sendMessage(msn);
        
    }

    public void iniciarJuegoNuevo() {
        ServerNet.startListening(this);
    }

    public void notify(Object pData) {
        ConjuroMsg msg = (ConjuroMsg) pData;
    }
}
