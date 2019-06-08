/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import ecriptacion.IAlgorithm;
import ecriptacion.Security;

/**
 *
 * @author adri-
 */
public class Card {
    private String name;
    private String description;
    private String descriptionEncryted;
    private String key1;
    private String key2;
    private String type;
    
    public Card(String pName, String pDescription, String pKey1, String pKey2, String pType){
        name = pName;
        description = pDescription;
        key1 = pKey1;
        key2 = pKey2;
        type = pType;
        generateDescripEncrypted();
    }
    
    private void generateDescripEncrypted(){
        IAlgorithm alg = Security.generateAlgorithm(type);
        descriptionEncryted = alg.encrypt(description, key1);  
    }
    
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getDescriptionEncryted(){
        return descriptionEncryted;
    }
    
    public String getKey1(){
        return key1;
    }
    
    public String getKey2(){
        return key2;
    }
}
