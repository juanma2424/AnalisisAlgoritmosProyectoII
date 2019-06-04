/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import java.net.Socket;
import lib.*;
import java.io.*;
import conjuronet.*;

public class ClientSocket extends Observable implements Constants, Runnable {

    private Socket client;
    private ObjectInputStream inputReader;
    private ObjectOutputStream outputWriter;
    private boolean isListening = false;

    public ClientSocket(Socket pSocket) {
        client = pSocket;
    }

    public ClientSocket(String pIp, int pPort) {
        try {
            client = new Socket(pIp, pPort);
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
    }

    public void run() {
        while (isListening) {
            try {
                String msgData = (String) inputReader.readObject();
                ConjuroMsg msg = new ConjuroMsg(msgData);
                System.out.println("El nombre es: " + msg.getValue("Nombre"));
                System.out.println("El nombre2 es: " + msg.getValue("nombre2"));
                this.notifyObservers(msg);
                Thread.sleep(THREAD_SLEEP_TIME);
            } catch (Exception ex) {
                Logger.Log(ex.getMessage());
            }
            break;
        }
    }

    public void sendMessage(ConjuroMsg pMsg) {
        try {
            outputWriter.writeObject(pMsg.getStringMsg());
            outputWriter.flush();
            //outputWriter.close();

        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
    }

    public void stop() {
        try {
            isListening = false;
            inputReader.close();
            outputWriter.close();
            client.close();
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
    }

    private void initReaders() {
        if (client != null) {
            try {
                isListening = true;
                outputWriter = new ObjectOutputStream(client.getOutputStream());
                inputReader = new ObjectInputStream(client.getInputStream());
                Thread newthread = new Thread(this);
                newthread.start();
            } catch (Exception ex) {
                Logger.Log(ex.getMessage());
            }
        }
    }
}
