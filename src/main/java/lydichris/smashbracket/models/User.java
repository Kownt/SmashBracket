/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.models;

import java.util.UUID;

/**
 *
 * @author cgmcandrews
 */
public class User {
    private final String id;
    private final String userName;
    
    public User(String id, String userName){
        this.id = id;
        this.userName = userName;
    }
    
    public String getId(){
        return id;
    }
    
    public String getUserName(){
        return userName;
    }
}
