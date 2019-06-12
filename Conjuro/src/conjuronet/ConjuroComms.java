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
        /*ConjuroMsg msg = new ConjuroMsg("0,Nombre=juanmaa,n=j1");
        client.sendMessage(msg);*/
    }

    public void iniciarJuegoNuevo() {
        ServerNet.startListening(this);
    }

    public void notify(Object pData) {
        ConjuroMsg msg = (ConjuroMsg) pData;

	/*	switch (msg.getType()) {
                    case PLAYER_NAME:{
                        
                    }
                }*/
		
//		}
    }
}