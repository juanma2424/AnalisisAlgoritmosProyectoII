/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    }

}

class LaminaMarcoCliente extends JPanel {

    public LaminaMarcoCliente() {
        campo2 = new JTextField(5);
        add(campo2);
        
        campo3 = new JTextField(8);
        add(campo3);
        

        JLabel texto = new JLabel("Chat");

        add(texto);
        at = new JTextArea(12,20);
        add(at);
        campo1 = new JTextField(20);

        add(campo1);

        miboton = new JButton("Enviar");

        EnviaTexto mi = new EnviaTexto();
        
        miboton.addActionListener(mi);

        add(miboton);

    }

    private class EnviaTexto implements ActionListener {

        
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // System.out.println("funk");
                
                // se crea socket
                // dir ip
                
                
                
                Socket miso =new Socket("192.168.150.1",9999);
                pak data = new pak();
                data.setNick(campo2.getText());
                data.setIp(campo3.getText());
                data.setMns(campo1.getText());
                ObjectOutputStream  flujosalida= new ObjectOutputStream(miso.getOutputStream()); //flujo de salida
                flujosalida.writeObject(data);
                miso.close();
                
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
    private JTextField campo2;
    private JTextField campo3;
     private JTextArea at;

    private JButton miboton;

}


class pak implements Serializable{
    private String nick,ip,mns;

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