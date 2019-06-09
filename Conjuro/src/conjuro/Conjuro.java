/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuro;

import conjuronet.ConjuroComms;
import ecriptacion.RSA;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adri-
 */
public class Conjuro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*ConjuroComms prueba = new ConjuroComms();
        if( 0== 0)
            prueba.iniciarJuegoNuevo();
        else
            prueba.conectarAJuego("172.19.49.37");*/
        Sha n = new Sha("1");
        Des m = new Des("HG58YZ3CR9");
        PGP b = new PGP();
        b.encriptar();
        b.desencriptar();
        //RSA j = new RSA();
        //j.decrypt(j.encrypt("hola me llamos ad"), "");
        
        
    }
    
}
