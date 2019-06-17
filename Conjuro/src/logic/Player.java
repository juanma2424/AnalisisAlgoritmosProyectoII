package logic;

public class Player {
    private String name;
    
    public Player(String pName){
        name = pName;
    }
    
    public Player(){
    }
    
    public void setName(String pName){
        name = pName;
    }
    
    public String getName(){
        return name;
    }
}