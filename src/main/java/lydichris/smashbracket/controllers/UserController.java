/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.controllers;

import lydichris.smashbracket.exceptions.UserCreationException;
import lydichris.smashbracket.services.UserService;
import lydichris.smashbracket.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author cgmcandrews
 */
@SpringBootApplication
@RestController
public class UserController {
    
    @Autowired UserService userService;
    
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    User createUser(@RequestParam String username,
            @RequestParam String password,
            @RequestParam String email) throws UserCreationException {
	return userService.maybeCreateUser(username, password, email);
    }
    
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    User getUser(@PathVariable String userId) {
	return userService.getUser(userId);
    }    
}
