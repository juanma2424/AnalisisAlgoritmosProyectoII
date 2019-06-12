package encriptacion;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Random;
import lib.Constants;
import lib.Logger;

class AES implements IAlgorithm, Constants {
    private String key1;
    private String key2;
    
    public AES(){
        key1 = generateSymmetricKey();// genera llave simetrica
        key2 = generateSymmetricKey();
    }
    
    public String encrypt(String pText) {
        String result = "";
        try {
            byte[] pTextB = pText.getBytes("UTF-8"); // obtienen el dato de la gui y codifica en una secuencia de bytes 
            
            SecretKeySpec skeySpec = new SecretKeySpec(Base64.getDecoder().decode(key1), "AES");// Crea el objeto SecretKey pasa la clave y el algoritmo "AES"
            Cipher cipher = Cipher.getInstance("AES");//Crear un objeto de cifrado de tipo AES
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);//  modo de operación (cifrar o descifrar) y la clave pública
            byte[] encrypted = org.apache.commons.codec.binary.Base64.encodeBase64(cipher.doFinal(pTextB));// completa la operación de cifrado
            result = new String(encrypted);
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
        return result;

    }

    public String decrypt(String pText, String pKey) {
        String decryptedMessage = "";
        try {

            SecretKeySpec skeySpec = new SecretKeySpec(Base64.getDecoder().decode(pKey), "AES");// Crea el objeto SecretKey pasa la clave y el algoritmo "AES"
            
            Cipher cipher = Cipher.getInstance("AES");//Crear un objeto de cifrado de tipo AES
            
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);// modo de operación (cifrar o descifrar) y la clave pública
            
            byte[] decrypted = cipher.doFinal(org.apache.commons.codec.binary.Base64.decodeBase64(pText.getBytes()));
            decryptedMessage = new String(decrypted,"UTF-8");
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
        return decryptedMessage;
    }

    private String generateSymmetricKey() {
        String key = "";
        try {
            Random r = new Random();// crea un objeto de tipo random
            int num = r.nextInt(LIMIT_RANDOM);// random de 0 a 10000
            String knum = String.valueOf(num);// devuelve la representación de cadena del argumento num tostring
            byte[] knumb = Base64.getDecoder().decode(knum);// codifica knum en una secuencia de bytes
            key = Base64.getEncoder().encodeToString(getRawKey(knumb));// de bytes a strg
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
        return key;
    }

    //resive una secuencia de bytes
    private byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");//clave de tipo AES
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");//número aleatorio altamente seguro

        sr.setSeed(seed);//Establezca la semilla del generador de números aleatorios con la secuencia de byts del aleatorio porcesado

        kgen.init(128, sr); // especificar el tamaño de la clave minimo de 128
        SecretKey skey = kgen.generateKey();// genera la clave
        return skey.getEncoded();//codificación de caracteres del numero aleatorio y lo guarda en raw
    }

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    } 
    
    public void setText(String pText){
        
    }
}