/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.models;

/**
 *
 * @author cgmcandrews
 */
public class User {
    private final long id;
    private final String userName;
    
    public User(long id, String userName){
        this.id = id;
        this.userName = userName;
    }
    
    public long getId(){
        return id;
    }
    
    public String getUserName(){
        return userName;
    }
}
