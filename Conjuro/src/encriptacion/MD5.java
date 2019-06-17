package encriptacion;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;
import lib.Constants;
import lib.Logger;

public class MD5 implements IAlgorithm, Constants{
    private String key1;
    private String key2;
    private String text;
    
    public MD5(){
        key1 = generateKey();
        key2 = generateKey();
        text = "";
    }
    
    public String encrypt(String input) {
        String hashtext = "";
        try {

            //getInstance con hash MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() calcula el resultado del mns
            byte[] messageDigest = md.digest(input.getBytes());

        
            // msg a hex
            BigInteger no = new BigInteger(DATA_ONE, messageDigest);
            hashtext = no.toString(DATA_SIXTEEN);//16 palabras 
            
            while (hashtext.length() < DATA_THIRTY_TWO ) {// 32 bits
                hashtext = "0" + hashtext;
            }

        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
        return hashtext;
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