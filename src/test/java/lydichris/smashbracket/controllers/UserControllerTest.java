/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.controllers;

import lydichris.smashbracket.exceptions.UserCreationException;
import lydichris.smashbracket.models.User;
import lydichris.smashbracket.services.UserService;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author cgmcandrews
 */
public class UserControllerTest {
    
    UserController controller = new UserController();
    
    String username = "username";
    String password = "password";
    String email = "email@test.com";
    User mockUser = new User(username, email);
        
    @Before
    public void setUp() {
        controller.userService = mock(UserService.class);
    }
    
    @Test
    public void testCreateUserDelegatesToServiceLayer() throws UserCreationException {
        when(controller.userService.maybeCreateUser(username, password, email)).thenReturn(mockUser);
        controller.createUser(username, password, email);
        verify(controller.userService).maybeCreateUser(username, password, email); 
    }
    
    @Test
    public void testGetUserDelegatesToServiceLayer() throws Exception {
        String uuid = "testing";
        when(controller.userService.getUser(uuid)).thenReturn(mockUser);
        controller.getUser(uuid);
        verify(controller.userService).getUser(uuid); 
    }
    
}
