/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecriptacion;

import java.util.Random;
import lib.Logger;


public class Pgp implements IAlgorithm {
    private String key1;
    private String key2;
    
    private void generateKey(String pKey){
        RSA rsa = new RSA(1024);
        key1 = rsa.getKey2();
        key2= "fdsfsd";
    }
    
    public String encrypt(String pText) {
        TresDes encryption = new TresDes();
        generateKey(encryption.getKey1());
        return encryption.encrypt(pText);
    }
    
    public String decrypt(String pText, String pKey) {
        return "";
    }

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }
}
