/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author adri-
 */
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
