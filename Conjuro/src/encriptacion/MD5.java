package encriptacion;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;
import lib.Constants;
import lib.Logger;

// Java program to calculate MD5 hash value 
public class MD5 implements IAlgorithm, Constants{
    private String key1;
    private String key2;
    
    public MD5(){
        key1 = generateKey();
        key2 = generateKey();
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
    
    private String generateKey(){
        Random rnd = new Random();
        String key = "";
        for(int i = 0; i < LENGTH_KEY; i++){
            key += (char)(rnd.nextInt(26)+65);
            if(rnd.nextInt(4) < 1)
                key += rnd.nextInt(10);
        }
        return key;
    }

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }
}