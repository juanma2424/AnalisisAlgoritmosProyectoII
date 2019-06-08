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
public interface IAlgorithm {
    
    public String encrypt(String pText, String pKey);
    
    public String decrypt(String pText, String pKey);
}
