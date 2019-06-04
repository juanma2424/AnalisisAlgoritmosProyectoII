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
public class Pgp implements IAlgorithm{
    
    public String encrypt(String pText, String pKey){
        PGPFileProcessor pgpFile = new PGPFileProcessor();
        try {
            pgpFile.setKeyFile(pKey);
            pgpFile.setInputFile("C:\\Users\\adri-\\Downloads\\Nueva carpeta (2)\\certificado-llave\\texto-prueba.txt");
            pgpFile.setOutputFile("C:\\Users\\adri-\\Downloads\\Nueva carpeta (2)\\certificado-llave\\final.gpg");
            pgpFile.encrypt();
        } catch (Exception e) {
        }
        return pText;
    }
    
    public String decrypt(String pText, String pKey){
        try {
            PGPFileProcessor pgpFile = new PGPFileProcessor();
            pgpFile.setKeyFile(pKey);
            pgpFile.setPassphrase("123456");
            pgpFile.setInputFile("C:\\Users\\adri-\\Downloads\\Nueva carpeta (2)\\certificado-llave\\final.gpg");
            pgpFile.setOutputFile("C:\\Users\\adri-\\Downloads\\Nueva carpeta (2)\\certificado-llave\\salida.txt");
            pgpFile.decrypt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pText;
    }
}
