/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuro;

import java.math.BigInteger;
import java.security.MessageDigest;
import lib.Logger;

/**
 *
 * @author adri-
 */
public class Sha {

    public Sha(String pInput) {
        try {

            // Static getInstance method is called with hashing SHA 
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // digest() method called 
            // to calculate message digest of an input 
            // and return array of byte 
            byte[] messageDigest = md.digest(pInput.getBytes());

            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value 
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            System.out.println(hashtext);
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
    }

}
