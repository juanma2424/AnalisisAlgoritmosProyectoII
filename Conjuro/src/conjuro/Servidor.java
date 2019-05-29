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
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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

            ServerSocket socketConect = new ServerSocket(9999);
            String nick, ip, mns;

            Empaque paquete;

            while (true) {

                Socket miSocket = socketConect.accept();

                ObjectInputStream DataPaquete = new ObjectInputStream(miSocket.getInputStream());//flujo
                paquete = (Empaque) DataPaquete.readObject();

                nick = paquete.getNick();
                ip = paquete.getIp();
                mns = paquete.getMns();

//DataInputStream  fs = new DataInputStream(ms.getInputStream());
//            String m = fs.readUTF();
                if (!mns.equals("online")) {

                    areatexto.append("\n" + nick + ", " + ip + ", " + mns);

                    Socket enviadest = new Socket(ip, 9090);//server-cliente
                    ObjectOutputStream pkrev = new ObjectOutputStream(enviadest.getOutputStream());//flujo de datos
                    pkrev.writeObject(paquete);
                    enviadest.close();

                    miSocket.close();
                } else {
                    //-------------detec online
                    InetAddress localizacion = miSocket.getInetAddress();

                    String ipRemota = localizacion.getHostAddress();

                    System.out.println("Ip " + ipRemota);
                    ArrayList <String> listaIP = new ArrayList<String>(); 
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MarcoServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MarcoServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
