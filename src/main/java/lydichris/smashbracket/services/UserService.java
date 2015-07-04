/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.services;

import lydichris.smashbracket.models.User;

/**
 *
 * @author cgmcandrews
 */
public class UserService {
    
    public User maybeCreateUser(String userName){
        //blah bah blah this should go to persistence layer and do some stuff.
        return new User(1, userName);
    }

    public User getUser(long userId) {
        //blah bah blah this should go to persistence layer and do some stuff.
        return new User(userId, "userName");
    }
}
