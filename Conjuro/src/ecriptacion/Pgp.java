/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecriptacion;

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

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }
}
