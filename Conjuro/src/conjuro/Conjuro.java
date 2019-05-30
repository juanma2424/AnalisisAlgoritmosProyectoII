/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuro;

import conjuronet.ConjuroComms;
import conjuronet.ConjuroMsg;

/**
 *
 * @author Juanma
 */
public class Conjuro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ConjuroComms prueba = new ConjuroComms();
       prueba.conectarAJuego("172.19.49.37");
      
        
       
       }
    
}
