/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JComponent.TOOL_TIP_TEXT_KEY;

public class Cliente {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        MarcoCliente mimarco = new MarcoCliente();
        
        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
}

class MarcoCliente extends JFrame {
    
    public MarcoCliente() {
        
        setBounds(600, 300, 280, 350);
        
        LaminaMarcoCliente milamina = new LaminaMarcoCliente();
        
        add(milamina);
        
        setVisible(true);
        addWindowListener(new EnvioOnline());
    }
    
}

class EnvioOnline extends WindowAdapter {

    public void windowOpened(WindowEvent e) {
        try {
            Socket socketServidor = new Socket("192.168.150.1", 9999);
            Empaque datos = new Empaque();
            datos.setMns("online");
            ObjectOutputStream paquetedatos = new ObjectOutputStream(socketServidor.getOutputStream());
            paquetedatos.writeObject(datos);
            paquetedatos.close();
            
        } catch (IOException ex) {
            Logger.getLogger(EnvioOnline.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

class LaminaMarcoCliente extends JPanel implements Runnable {
    
    public LaminaMarcoCliente() {
        
        String nick_user = JOptionPane.showInputDialog("Nick: ");
        
        JLabel nickName = new JLabel("Nick: ");
        add(nickName);
        nick = new JLabel();
        nick.setText(nick_user);
        add(nick);
        
        ip = new JComboBox();
        ip.addItem("u 1");
        ip.addItem("u 2");
        ip.addItem("u 3");
        
        JLabel texto = new JLabel("|Online");
        
        add(texto);
        add(ip);
        chat = new JTextArea(12, 20);
        add(chat);
        campo1 = new JTextField(20);
        
        add(campo1);
        
        miboton = new JButton("Enviar");
        
        EnviaTexto mi = new EnviaTexto();
        
        miboton.addActionListener(mi);
        
        add(miboton);
        Thread mihilo = new Thread(this);
        mihilo.start();
        
    }
    
    @Override
    public void run() {
        try {
            ServerSocket sercli = new ServerSocket(9090);
            Socket cliente;
            Empaque paquete;
            while (true) {
                cliente = sercli.accept();
                ObjectInputStream flojoEntrada = new ObjectInputStream(cliente.getInputStream());
                paquete = (Empaque) flojoEntrada.readObject();
                chat.append(paquete.getMns());
            }
            
        } catch (IOException ex) {
            Logger.getLogger(MarcoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MarcoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private class EnviaTexto implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // System.out.println("funk");

                // se crea socket
                // dir ip
                Socket MiSocket = new Socket("192.168.150.1", 9999);
                Empaque data = new Empaque();
                data.setNick(nick.getText());
                data.setIp((String) ip.getSelectedItem());
                data.setMns(campo1.getText());
                ObjectOutputStream flujosalida = new ObjectOutputStream(MiSocket.getOutputStream()); //flujo de salida
                flujosalida.writeObject(data);
                MiSocket.close();

//                DataOutputStream flujosalida = new  DataOutputStream(miso.getOutputStream());
//                flujosalida.writeUTF(campo1.getText());
//                flujosalida.close();
                ////////////////////////////////
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(LaminaMarcoCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    private JTextField campo1;
    private JLabel nick;
    private JComboBox ip;
    public JTextArea chat;
    private JButton miboton;
    
}

class Empaque implements Serializable {
    
    private String nick, ip, mns;
    
    public void setNick(String nick) {
        this.nick = nick;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public void setMns(String mns) {
        this.mns = mns;
    }
    
    public String getNick() {
        return nick;
    }
    
    public String getIp() {
        return ip;
    }
    
    public String getMns() {
        return mns;
    }
    
}
