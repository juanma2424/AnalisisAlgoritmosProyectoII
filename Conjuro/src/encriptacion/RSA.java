/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encriptacion;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import lib.Constants;
import lib.Logger;

public class RSA implements IAlgorithm, Constants {

    private KeyPairGenerator keyGen;
    private String privateKey;
    private String publicKey;
    private Cipher cipher;

    public RSA() {
        try {
            this.keyGen = KeyPairGenerator.getInstance("RSA");
            this.keyGen.initialize(RSA_LENGTH_KEY);
            this.cipher = Cipher.getInstance("RSA");
            createKeys();
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
    }

    public String encrypt(String pMsg) {
        String result = "";
        try {
            byte[] keyBytes = Base64.getDecoder().decode(getKey1());
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            this.cipher.init(Cipher.ENCRYPT_MODE, kf.generatePublic(spec));
            result = new String(org.apache.commons.codec.binary.Base64.encodeBase64(cipher.doFinal(pMsg.getBytes("UTF-8"))));
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
        return result;
    }

    public String decrypt(String pMsg, String pKey) {
        String result = "";
        try {
            byte[] keyBytes = Base64.getDecoder().decode(pKey);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            this.cipher.init(Cipher.DECRYPT_MODE, kf.generatePrivate(spec));
            result = new String(cipher.doFinal(org.apache.commons.codec.binary.Base64.decodeBase64(pMsg.getBytes())), "UTF-8");
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
        return result;
    }

    private void createKeys() {
        KeyPair pair = this.keyGen.generateKeyPair();
        this.privateKey = Base64.getEncoder().encodeToString(pair.getPrivate().getEncoded());
        this.publicKey = Base64.getEncoder().encodeToString(pair.getPublic().getEncoded());
    }

    public String getKey2() {
        return this.privateKey;
    }

    public String getKey1() {
        return this.publicKey;
    }
}
