/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * https://slogix.in/how-to-encrypt-and-decrypt-data-using-aes-in-java#Source-code
 * https://www.tutorialspoint.com/java_cryptography/java_cryptography_keygenerator.htm
 */
package ecriptacion;

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
    byte[] raw;
    String inputMessage, encryptedData, decryptedMessage;
    private byte[] AUXebyte;

//    public AES() {
//        try {
//            generateSymmetricKey();// genera llave simetrica
//
//            inputMessage = JOptionPane.showInputDialog(null, "Enter message to encrypt");// gui
//            byte[] ibyte = inputMessage.getBytes(); // obtienen el dato de la gui y codifica en una secuencia de bytes 
//            byte[] ebyte = encrypt(raw, ibyte);// dato encriptado
//            String encryptedData = new String(ebyte);
//            System.out.println("Encrypted message " + encryptedData);// mensaje encriptado
//            JOptionPane.showMessageDialog(null, "Encrypted Data " + "\n" + encryptedData);// mensaje encriptado en GUI
//
//            byte[] dbyte = decrypt(raw, ebyte);// dato desencriptado
//            String decryptedMessage = new String(dbyte);
//            System.out.println("Decrypted message " + decryptedMessage);;// mensaje desencriptado
//            JOptionPane.showMessageDialog(null, "Decrypted Data " + "\n" + decryptedMessage);// mensaje desencriptado en GUI
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//    }
    public void encrypt(String pdata, String pkey) {
        try {
            generateSymmetricKey();// genera llave simetrica
            inputMessage = pdata;
            byte[] ibyte = inputMessage.getBytes(); // obtienen el dato de la gui y codifica en una secuencia de bytes 
            byte[] ebyte = encrypt(raw, ibyte);// dato encriptado
            this.AUXebyte = ebyte;
            String encryptedData = new String(ebyte);
            System.out.println("Encrypted message " + encryptedData);// mensaje encriptado
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void decrypt(String pdata, String pkey) {
        try {
            byte[] dbyte = decrypt(raw, this.AUXebyte);// dato desencriptado
            String decryptedMessage = new String(dbyte);
            System.out.println("Decrypted message " + decryptedMessage);;// mensaje desencriptado
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    void generateSymmetricKey() {
        try {
            Random r = new Random();// crrea un objeto de tipo random
            int num = r.nextInt(LIMIT_RANDOM);// random de 0 a 10000
            String knum = String.valueOf(num);// devuelve la representación de cadena del argumento num tostring
            System.out.println("k " + knum);
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
    private byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");//clave de tipo AES
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");//número aleatorio altamente seguro

        sr.setSeed(seed);//Establezca la semilla del generador de números aleatorios con la secuencia de byts del aleatorio porcesado

        kgen.init(128, sr); // especificar el tamaño de la clave minimo de 128
        SecretKey skey = kgen.generateKey();// genera la clave
        raw = skey.getEncoded();//codificación de caracteres del numero aleatorio y lo guarda en raw
        return raw;
    }

    // resive la llave simetrica y el dato codificado (input) en secuencia de bytes  
    private byte[] encrypt(byte[] raw, byte[] clear) throws Exception {

        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");// Crea el objeto SecretKey pasa la clave y el algoritmo "AES"
        Cipher cipher = Cipher.getInstance("AES");//Crear un objeto de cifrado de tipo AES
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);//  modo de operación (cifrar o descifrar) y la clave pública
        byte[] encrypted = cipher.doFinal(clear);// completa la operación de cifrado
        return encrypted;//dato encriptado

    }

    // resive la llave simetrica y el dato codificado (input) en secuencia de bytes  
    private byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");// Crea el objeto SecretKey pasa la clave y el algoritmo "AES"
        Cipher cipher = Cipher.getInstance("AES");//Crear un objeto de cifrado de tipo AES
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);// modo de operación (cifrar o descifrar) y la clave pública
        byte[] decrypted = cipher.doFinal(encrypted);// completa la operación de descifrado
        return decrypted;
    }

    public static void main(String args[]) {
        AES aes = new AES();
        aes.encrypt("hola", "0");
        aes.decrypt("0", "0");

    }

}
