/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import lib.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ServerNet implements Constants, Runnable {

    private static ServerNet server;
    private List<ClientSocket> clients;
    private ServerSocket serversocket;
    private boolean isListening;
    private IObserver observer;

    private ServerNet(IObserver pObserver) throws Exception {
        clients = new ArrayList<ClientSocket>();
        serversocket = new ServerSocket(PORT_NUMBER);
        isListening = true;
        this.observer = pObserver;
    }

    public synchronized static void startListening(IObserver pObserver) {
        try {
            if (server == null) {
                server = new ServerNet(pObserver);
            }

            Thread listenThread = new Thread(server);
            listenThread.start();

        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
    }

    public void run() {
        while (isListening) {
            try {
                Socket newSocket = serversocket.accept();

                ClientSocket client = new ClientSocket(newSocket);
                client.addObserver(this.observer);
                clients.add(client);
                System.out.println(clients);
                Thread.sleep(THREAD_SLEEP_TIME);
            } catch (Exception ex) {
                Logger.Log(ex.getMessage());
            }
        }
    }

    public void stopListen() {
        isListening = false;
    }
}
