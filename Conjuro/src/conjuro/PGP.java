/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuro;

/**
 *
 * @author adri-
 */
public class PGP {
    
    public PGP(){
        
    }
    
    public void encriptar(){
        PGPFileProcessor p = new PGPFileProcessor();
        try {
            p.setKeyFile("C:\\Users\\adri-\\Downloads\\Nueva carpeta (2)\\certificado-llave\\CertificadoPublico.asc");
            p.setInputFile("C:\\Users\\adri-\\Downloads\\Nueva carpeta (2)\\certificado-llave\\texto-prueba.txt");
            p.setOutputFile("C:\\Users\\adri-\\Downloads\\Nueva carpeta (2)\\certificado-llave\\final.gpg");
            p.encrypt();
        } catch (Exception e) {
        }
    }
    
    public void desencriptar(){
        try {
            PGPFileProcessor p = new PGPFileProcessor();
            p.setKeyFile("C:\\Users\\adri-\\Downloads\\Nueva carpeta (2)\\certificado-llave\\llavePrivada.gpg");
            p.setPassphrase("123456");
            p.setInputFile("C:\\Users\\adri-\\Downloads\\Nueva carpeta (2)\\certificado-llave\\final.gpg");
            p.setOutputFile("C:\\Users\\adri-\\Downloads\\Nueva carpeta (2)\\certificado-llave\\salida.txt");
            p.decrypt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
