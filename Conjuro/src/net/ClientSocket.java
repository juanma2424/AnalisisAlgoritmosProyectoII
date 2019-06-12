package net;

import java.net.Socket;
import lib.*;
import java.io.*;
import conjuronet.*;

public class ClientSocket extends Observable implements Constants, Runnable {

    private Socket client;
    private DataInputStream inputReader;
    private DataOutputStream outputWriter;
    private boolean isListening = false;

    public ClientSocket(Socket pSocket) {
        client = pSocket;
        initReaders();
    }

    public ClientSocket() {
       
    }
    
    public boolean makeClient(String pIp, int pPort) {
        try {
            client = new Socket(pIp, pPort);
            initReaders();
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
            return false;
        }
        return true;
    }

    public void run() {
        while (isListening) {
            try {
                String msgData = inputReader.readUTF();
                ConjuroMsg msg = new ConjuroMsg(msgData);
                this.notifyObservers(msg);
            
                Thread.sleep(THREAD_SLEEP_TIME);
            } catch (Exception ex) {
                stop();
                Logger.Log(ex.getMessage());
            }
        }
    }

    public void sendMessage(ConjuroMsg pMsg) {
        try {
            outputWriter.writeUTF(pMsg.getStringMsg());
            outputWriter.flush();
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
                outputWriter = new DataOutputStream(client.getOutputStream());
                inputReader = new DataInputStream(client.getInputStream());
                Thread newthread = new Thread(this);
                newthread.start();

            } catch (Exception ex) {
                Logger.Log(ex.getMessage());
            }
        }
    }
}