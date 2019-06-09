/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * https://www.geeksforgeeks.org/md5-hash-in-java/
 */
package ecriptacion;

import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
  
// Java program to calculate MD5 hash value 
public class MD5 implements IAlgorithm  { 
    public static String getMd5(String input) 
    { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }
    
     public String encrypt(String pdata, String pkey) {
         System.out.println(""+getMd5(pdata));
         return getMd5(pdata);
     }
     
      public String decrypt(String pText, String pKey){
        return pText;
    }
  
    // Driver code 
    public static void main(String args[]) throws NoSuchAlgorithmException 
    { 
          MD5 md5 = new MD5(); 
          md5.encrypt("hola", "0"); 
    } 
} 
