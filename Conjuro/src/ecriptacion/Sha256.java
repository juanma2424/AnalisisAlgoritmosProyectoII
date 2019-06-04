/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecriptacion;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256 extends Security{
    
    public String encrypt(String pText, String pKey){
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
        } // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown"
                    + " for incorrect algorithm: " + e);

        }
        return hashText;
    }
    
    public String decrypt(String pText, String pKey){
        return pText;
    }
}

