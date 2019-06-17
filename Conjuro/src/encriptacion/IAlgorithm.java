package encriptacion;

public interface IAlgorithm {
    
    public String encrypt(String pText);
    
    public String decrypt(String pText, String pKey);

    public String getKey1();
    
    public String getKey2();
    
    public void setText(String pText);
    
    public void setKey(String pKey);  
}