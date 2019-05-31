/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        ConjuroMsg msg = new ConjuroMsg("0,Nombre=Adrian");
        client.sendMessage(msg);
    }

    public void iniciarJuegoNuevo() {
        ServerNet.startListening(this);
    }

    public void notify(Object pData) {
        ConjuroMsg msg = (ConjuroMsg) pData;

//		switch (msg.getType()) {
//		
//		}
    }
}
