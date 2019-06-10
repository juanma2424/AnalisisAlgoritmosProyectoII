/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encriptacion;

import lib.Logger;

/**
 *
 * @author adri-
 */
public class Security {
    
    public static IAlgorithm generateAlgorithm(String pType){
        
        IAlgorithm cls = null;
        try {
            Class clss = Class.forName("encriptacion."+pType);
            cls = (IAlgorithm)clss.newInstance();  
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
        
        return cls;
    }  
}
