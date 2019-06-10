/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecriptacion;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adri-
 */
public class Security {
    
    public static IAlgorithm generateAlgorithm(String pType){
        
        IAlgorithm cls = null;
        try {
            Class clss = Class.forName(pType);
            cls = (IAlgorithm)clss.newInstance();  
        } catch (Exception ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cls;
    }  
}
