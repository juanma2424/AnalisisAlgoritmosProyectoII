/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuro;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        MarcoServidor mimarco = new MarcoServidor();

        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

class MarcoServidor extends JFrame implements Runnable {

    public MarcoServidor() {

        setBounds(1200, 300, 280, 350);

        JPanel milamina = new JPanel();

        milamina.setLayout(new BorderLayout());

        areatexto = new JTextArea();

        milamina.add(areatexto, BorderLayout.CENTER);

        add(milamina);

        setVisible(true);

        Thread mihilo = new Thread(this);
        mihilo.start();

    }

    private JTextArea areatexto;

    @Override
    public void run() {

        try {
            ServerSocket ns = new ServerSocket(9999);
            String nick, ip, mns;

            pak pac;

            while (true) {
                Socket ms = ns.accept();
                ObjectInputStream pkdata = new ObjectInputStream(ms.getInputStream());//flujo
                pac = (pak) pkdata.readObject();

                nick = pac.getNick();
                ip = pac.getIp();
                mns = pac.getMns();

//DataInputStream  fs = new DataInputStream(ms.getInputStream());
//            String m = fs.readUTF();
                areatexto.append("\n");
                areatexto.append("\n" + nick);
                areatexto.append("\n" + ip);
                areatexto.append("\n" + mns);
                ms.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(MarcoServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MarcoServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
