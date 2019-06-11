package encriptacion;

public class Pgp implements IAlgorithm {
    private String key1;
    private String key2;
    
    private void generateKey(String pKey){
        RSA rsa = new RSA();
        key1 = rsa.getKey2();
        rsa.encrypt(pKey);
        key2 = "fdsfsd";
    }
    
    public String encrypt(String pText) {
        TresDes encryption = new TresDes();
        generateKey(encryption.getKey1());
        return encryption.encrypt(pText);
    }
    
    public String decrypt(String pText, String pKey) {
        return "";
    }

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }
}