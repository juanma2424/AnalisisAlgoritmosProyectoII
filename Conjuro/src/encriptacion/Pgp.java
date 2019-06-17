package encriptacion;

public class Pgp implements IAlgorithm {
    private String key1;
    private String key2;
    
    // 1. Genero pub y priv con rsa
    // 2. Encrypto el texto con llave privDES 
    // 3. Encrypto privDes con pub 
    // 4. Retorno key1, priv y key2 key un malo
    
    // 5. Cuando quiero probar la carta
    // 6. Trato de decifrar la llave con key1 o key2
    // 7. Cuando ya obtengo el privDes valido, entonces hago decrypt del texto y si lo logre entonces es PGP
    private void generateKey(String pKey){
        RSA rsa = new RSA();
        key1 = rsa.getKey2();
        key2 = rsa.encrypt(pKey);
    }
    
    public String encrypt(String pText) {
        TresDes encryption = new TresDes();
        generateKey(encryption.getKey1());
        return encryption.encrypt(pText);
    }
    
    public String decrypt(String pText, String pKey2) {
        TresDes encryption = new TresDes();
        RSA rsa = new RSA();
        String resultKey = rsa.decrypt(pKey2, key1);
        return encryption.decrypt(pText, resultKey);
    }

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }
    
    public void setText(String pText){
        
    }
    
    public void setKey(String pKey){
        key1 = pKey;
    }
}