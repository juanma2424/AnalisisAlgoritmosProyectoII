/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encriptacion;

import java.math.BigInteger;
import java.security.MessageDigest;
import lib.Logger;

public class Sha256 implements IAlgorithm{
    private String key1;
    private String key2;
    
    public Sha256(){
        key1 = "";
        key2 = "";
    }
    
    public String encrypt(String pText){
        String hashText = "";
        try {

            // Static getInstance method is called with hashing SHA 
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // digest() method called 
            // to calculate message digest of an input 
            // and return array of byte 
            byte[] messageDigest = md.digest(pText.getBytes());

            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value 
            hashText = no.toString(16);

            while (hashText.length() < 32) {
                pText = "0" + pText;
            }

            System.out.println(pText);
        }catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
        return hashText;
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

