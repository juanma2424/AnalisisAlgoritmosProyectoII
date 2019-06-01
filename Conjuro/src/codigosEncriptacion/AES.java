/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * https://slogix.in/how-to-encrypt-and-decrypt-data-using-aes-in-java#Source-code
 * https://www.tutorialspoint.com/java_cryptography/java_cryptography_keygenerator.htm
 */
package codigosEncriptacion;

import javax.swing.*;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Random;
import static lib.Constants.*;

class AES {

    byte[] skey = new byte[1000];
    String skeyString;
    static byte[] raw;
    String inputMessage, encryptedData, decryptedMessage;

    public AES() {
        try {
            generateSymmetricKey();// genera llave simetrica

              inputMessage = JOptionPane.showInputDialog(null, "Enter message to encrypt");// gui
              byte[] ibyte = inputMessage.getBytes(); // obtienen el dato de la gui y codifica en una secuencia de bytes 
              byte[] ebyte = encrypt(raw, ibyte);
//            String encryptedData = new String(ebyte);
//            System.out.println("Encrypted message " + encryptedData);
//            JOptionPane.showMessageDialog(null, "Encrypted Data " + "\n" + encryptedData);
//
//            byte[] dbyte = decrypt(raw, ebyte);
//            String decryptedMessage = new String(dbyte);
//            System.out.println("Decrypted message " + decryptedMessage);
//
//            JOptionPane.showMessageDialog(null, "Decrypted Data " + "\n" + decryptedMessage);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    void generateSymmetricKey() {
        try {
            Random r = new Random();// crrea un objeto de tipo random
            int num = r.nextInt(LIMIT_RANDOM);// random de 0 a 10000
            String knum = String.valueOf(num);// devuelve la representación de cadena del argumento num tostring
            byte[] knumb = knum.getBytes();// codifica knum en una secuencia de bytes
            //--
            skey = getRawKey(knumb);//codificación de caracteres del numero aleatorio y lo guarda en skey 
            // el raw esta guardado y skey tambien lo tiene  
            //--
            skeyString = new String(skey);// de bytes a strg
            System.out.println("AES Symmetric key = " + skeyString);// llave simetrica
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //resive una secuencia de bytes
    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");//clave de tipo AES
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");//número aleatorio altamente seguro
        
        sr.setSeed(seed);//Establezca la semilla del generador de números aleatorios con la secuencia de byts del aleatorio porcesado
  
        kgen.init(128, sr); // especificar el tamaño de la clave minimo de 128
        SecretKey skey = kgen.generateKey();// genera la clave
        raw = skey.getEncoded();//codificación de caracteres del numero aleatorio y lo guarda en raw
        return raw;
    }

    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }
//
//    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
//        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//        Cipher cipher = Cipher.getInstance("AES");
//        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
//        byte[] decrypted = cipher.doFinal(encrypted);
//        return decrypted;
//    }

    public static void main(String args[]) {
        AES aes = new AES();
    }
}
