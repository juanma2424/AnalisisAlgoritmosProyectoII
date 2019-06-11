package encriptacion;

import java.util.Random;
import lib.Constants;

public class Plain implements IAlgorithm, Constants {
    private String key1;
    private String key2;
    
    public Plain(){
        key1 = generateKey();
        key2 = generateKey();
    }
    
    public String encrypt(String pText){
        return pText;
    }
    
    public String decrypt(String pText, String pKey){
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