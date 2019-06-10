/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * https://www.geeksforgeeks.org/md5-hash-in-java/
 */
package encriptacion;

import java.math.BigInteger;
import java.security.MessageDigest;
import lib.Logger;

// Java program to calculate MD5 hash value 
public class MD5 implements IAlgorithm {
    private String key1;
    private String key2;
    
    public MD5(){
        key1 = "";
        key2 = "";
    }
    
    public String encrypt(String input) {
        String hashtext = "";
        try {

            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value 
            hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
        return hashtext;
    }

    public String decrypt(String pText, String pKey) {
        return pText;
    }

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }
}
