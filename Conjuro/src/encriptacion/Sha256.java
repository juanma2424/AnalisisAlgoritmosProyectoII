package encriptacion;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;
import lib.Constants;
import lib.Logger;

public class Sha256 implements IAlgorithm, Constants{
    private String key1;
    private String key2;
    private String text;
    
    public Sha256(){
        key1 = generateKey();
        key2 = generateKey();
        text = "";
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
            BigInteger no = new BigInteger(DATA_ONE, messageDigest);

            // Convert message digest into hex value 
            hashText = no.toString(DATA_SIXTEEN);

            while (hashText.length() < DATA_THIRTY_TWO) {
                pText = "0" + pText;
            }
        }catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
        return hashText;
    }
    
    public String decrypt(String pText, String pKey) {
        if(pText.equals(encrypt(text))){
            return text;
        }
        return encrypt(pText);
    }
    
    private String generateKey(){
        Random rnd = new Random();
        String key = "";
         for(int i = DATA_CERO; i < LENGTH_KEY; i++){
            key += (char)(rnd.nextInt(DATA_TWENTY_SIX)+ SIXTY_FIVE);
            if(rnd.nextInt(DATA_FOUR) < DATA_ONE)
                key += rnd.nextInt(DATA_TEN);
        }
        return key;
    }

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }
    
    public void setText(String pText){
        text = pText;
    }
    
    public void setKey(String pKey){
        key1 = pKey;
    }
}