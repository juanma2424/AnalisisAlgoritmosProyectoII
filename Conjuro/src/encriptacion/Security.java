package encriptacion;

import lib.Logger;

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