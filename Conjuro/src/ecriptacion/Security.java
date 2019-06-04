/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecriptacion;

/**
 *
 * @author adri-
 */
public class Security {
    
    public static IAlgorithm generateAlgorithm(String pType){
        switch(pType){
            case "Plain":
                return new Plain();
            case "Sha256":
                return new Sha256();
            case "TresDes":
                return new TresDes();
            case "Pgp":
                return new Pgp();
            case "Md5":
                return new Sha256();
            case "Aes":
                return new Sha256();
            default:
                return new Sha256();         
        }
    }
    
    
}
