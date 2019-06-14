package logic;

import encriptacion.IAlgorithm;
import encriptacion.Security;

public class Card {
    private String name;
    private String description;
    private String descripEncrypted;
    private String key1;
    private String key2;
    private String type;
    
    public Card(String pName, String pDescription,String pType){
        name = pName;
        description = pDescription;
        type = pType;
        generateDescripEncrypted();
    }
    
    public Card(String pName, String pDescription, String pDescripEncrypted, String pKey1, String pKey2){
        name = pName;
        description = pDescription;
        descripEncrypted = pDescripEncrypted;
        key1 = pKey1;
        key2 = pKey2;
    }
    
    private void generateDescripEncrypted(){
        IAlgorithm alg = Security.generateAlgorithm(type);
        descripEncrypted = alg.encrypt(description); 
        key1 = alg.getKey1();
        key2 = alg.getKey2();
    }
    
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getDescripEncrypted(){
        return descripEncrypted;
    }
    
    public String getKey1(){
        return key1;
    }
    
    public String getKey2(){
        return key2;
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(int pType){
        String[] types = {"Sha256", "MD5", "TresDes", "AES", "Plain", "RSA", "Pgp"};
        type = types[pType];
    }
}