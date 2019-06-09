/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecriptacion;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Random;
import lib.Constants;
import lib.Logger;

class AES implements IAlgorithm, Constants {

    byte[] skey = new byte[1000];
    String skeyString;
    byte[] raw;
    String inputMessage, encryptedData, decryptedMessage;
    private byte[] AUXebyte;
    private String key1;
    private String key2;

    public String encrypt(String pdata) {
        try {
            generateSymmetricKey();// genera llave simetrica
            inputMessage = pdata;
            byte[] ibyte = inputMessage.getBytes(); // obtienen el dato de la gui y codifica en una secuencia de bytes 
            byte[] ebyte = encrypt(raw, ibyte);// dato encriptado
            this.AUXebyte = ebyte;
            String encryptedData = new String(ebyte);
            System.out.println("Encrypted message " + encryptedData);// mensaje encriptado
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
        return encryptedData;

    }

    public String decrypt(String pdata, String pkey) {
        try {
            byte[] dbyte = decrypt(raw, this.AUXebyte);// dato desencriptado
            String decryptedMessage = new String(dbyte);
            System.out.println("Decrypted message " + decryptedMessage);;// mensaje desencriptado
            return decryptedMessage;
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
        return "";
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
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
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

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }
}
