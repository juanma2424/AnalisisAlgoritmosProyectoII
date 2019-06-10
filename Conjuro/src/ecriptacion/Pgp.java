/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecriptacion;

import java.util.Random;
import lib.Logger;


public class Pgp implements IAlgorithm {
    private String key1;
    private String key2;

    public String encrypt(String pText) {
        PGPFileProcessor pgpFile = new PGPFileProcessor();
        try {
            pgpFile.setKeyFile("");
            pgpFile.setInputFile("C:\\Users\\adri-\\Downloads\\Nueva carpeta (2)\\certificado-llave\\texto-prueba.txt");
            pgpFile.setOutputFile("C:\\Users\\adri-\\Downloads\\Nueva carpeta (2)\\certificado-llave\\final.gpg");
            pgpFile.encrypt();
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
        return pText;
    }

    public String decrypt(String pText, String pKey) {
        try {
            PGPFileProcessor pgpFile = new PGPFileProcessor();
            pgpFile.setKeyFile(pKey);
            pgpFile.setPassphrase("123456");
            pgpFile.setInputFile("C:\\Users\\adri-\\Downloads\\Nueva carpeta (2)\\certificado-llave\\final.gpg");
            pgpFile.setOutputFile("C:\\Users\\adri-\\Downloads\\Nueva carpeta (2)\\certificado-llave\\salida.txt");
            pgpFile.decrypt();
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
        return pText;
    }
    
    private void generateKey(String pKey){
        RSA rsa = new RSA(1024);
        key1 = rsa.getKey2();
        key2= rsa.encrypt(pKey);
    }
    
    public String encrypt2(String pText) {
        TresDes encryption = new TresDes();
        generateKey(encryption.getKey1());
        return encryption.encrypt(pText);
    }
    
    public String decrypt2(String pText, String pKey) {
        try {
            PGPFileProcessor pgpFile = new PGPFileProcessor();
            pgpFile.setKeyFile(pKey);
            pgpFile.setPassphrase("123456");
            pgpFile.setInputFile("C:\\Users\\adri-\\Downloads\\Nueva carpeta (2)\\certificado-llave\\final.gpg");
            pgpFile.setOutputFile("C:\\Users\\adri-\\Downloads\\Nueva carpeta (2)\\certificado-llave\\salida.txt");
            pgpFile.decrypt();
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
        return pText;
    }

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }
}
