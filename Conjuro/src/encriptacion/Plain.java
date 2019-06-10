/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encriptacion;

/**
 *
 * @author adri-
 */
public class Plain implements IAlgorithm {
    private String key1;
    private String key2;
    
    public Plain(){
        key1 = "";
        key2 = "";
    }
    
    public String encrypt(String pText){
        return pText;
    }
    
    public String decrypt(String pText, String pKey){
        return pText;
    }

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }
}
