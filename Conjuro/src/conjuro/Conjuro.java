/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuro;

import conjuronet.ConjuroComms;

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
        ConjuroComms prueba = new ConjuroComms();
        if( 1 == 0)
            prueba.iniciarJuegoNuevo();
        else
        prueba.conectarAJuego("172.19.49.37");
    }
    
}
