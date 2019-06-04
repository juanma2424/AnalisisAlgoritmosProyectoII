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
public abstract class Security {
    
    public abstract String encrypt(String pText, String pKey);
    
    public abstract String decrypt(String pText, String pKey);
    
    
}
