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
        for(int i = DATA_CERO; i < LENGTH_KEY; i++){
            key += (char)(rnd.nextInt(DATA_TWENTY_SIX)+SIXTY_FIVE);
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
        
    }
    
    public void setKey(String pKey){
        key1 = pKey;
    }
}