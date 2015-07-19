/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.services;

import lydichris.smashbracket.models.User;
import lydichris.smashbracket.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cgmcandrews
 */
@Service("userService")
public class UserService {

    @Autowired UserPersistence userPersistence;
    
    public void setUserPersistence(UserPersistence userPersistence){
        this.userPersistence = userPersistence;
    }
    
    public User maybeCreateUser(String userName){
        return userPersistence.createUser(userName);
    }

    public User getUser(String userId) {
        return userPersistence.getUser(userId);
    }
}
