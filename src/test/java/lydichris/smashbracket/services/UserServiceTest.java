/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.services;

import lydichris.smashbracket.enums.UserCreationExceptionEnum;
import lydichris.smashbracket.exceptions.UserCreationException;
import lydichris.smashbracket.models.User;
import lydichris.smashbracket.persistence.UserPersistence;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author cgmcandrews
 */
public class UserServiceTest {
    
    UserService service = new UserService();
    
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        service.userPersistence = mock(UserPersistence.class);
    }
    
    @Test
    public void testCreateUserValid() throws UserCreationException {
        String username = "username";
        String password = "password1!";
        String email = "email@test.com";
        User mockUser = new User(username, email);
        
        when(service.userPersistence.createUser(eq(username), any(byte[].class), eq(email))).thenReturn(mockUser);
        when(service.userPersistence.checkEmailExists(email)).thenReturn(Boolean.FALSE);
        when(service.userPersistence.checkUsernameExists(username)).thenReturn(Boolean.FALSE);
        User user = service.maybeCreateUser(username, password, email);
        assertEquals(username, user.getUserName());
        assertEquals(email, user.getEmail());
    }
    
    @Test
    public void testCreateUserPasswordValidationTooShort() throws UserCreationException {
        String username = "username";
        String password = "p1!";
        String email = "email@test.com";
        User mockUser = new User(username, email);
        
        when(service.userPersistence.createUser(eq(username), any(byte[].class), eq(email))).thenReturn(mockUser);
        when(service.userPersistence.checkEmailExists(email)).thenReturn(Boolean.FALSE);
        when(service.userPersistence.checkUsernameExists(username)).thenReturn(Boolean.FALSE);
        exception.expect(UserCreationException.class);
        exception.expectMessage(containsString(UserCreationExceptionEnum.PASSWORD_TOO_SHORT.getValue()));
        service.maybeCreateUser(username, password, email);
        fail();
    }
    
    
    @Test
    public void testCreateUserPasswordValidationMissingNumber() throws UserCreationException {
        String username = "username";
        String password = "password!";
        String email = "email@test.com";
        User mockUser = new User(username, email);
        
        when(service.userPersistence.createUser(eq(username), any(byte[].class), eq(email))).thenReturn(mockUser);
        when(service.userPersistence.checkEmailExists(email)).thenReturn(Boolean.FALSE);
        when(service.userPersistence.checkUsernameExists(username)).thenReturn(Boolean.FALSE);
        exception.expect(UserCreationException.class);
        exception.expectMessage(containsString(UserCreationExceptionEnum.PASSWORD_MISSING_NUMBER.getValue()));
        service.maybeCreateUser(username, password, email);
        fail();
    }
    
    @Test
    public void testCreateUserPasswordValidationMissingSpecial() throws UserCreationException {
        String username = "username";
        String password = "password1";
        String email = "email@test.com";
        User mockUser = new User(username, email);
        
        when(service.userPersistence.createUser(eq(username), any(byte[].class), eq(email))).thenReturn(mockUser);
        when(service.userPersistence.checkEmailExists(email)).thenReturn(Boolean.FALSE);
        when(service.userPersistence.checkUsernameExists(username)).thenReturn(Boolean.FALSE);
        exception.expect(UserCreationException.class);
        exception.expectMessage(containsString(UserCreationExceptionEnum.PASSWORD_MISSING_SPECIAL.getValue()));
        service.maybeCreateUser(username, password, email);
        fail();
    }
    
    @Test
    public void testCreateUserPasswordValidationIncludesAND() throws UserCreationException {
        String username = "username";
        String password = "passANDword1!";
        String email = "email@test.com";
        User mockUser = new User(username, email);
        
        when(service.userPersistence.createUser(eq(username), any(byte[].class), eq(email))).thenReturn(mockUser);
        when(service.userPersistence.checkEmailExists(email)).thenReturn(Boolean.FALSE);
        when(service.userPersistence.checkUsernameExists(username)).thenReturn(Boolean.FALSE);
        exception.expect(UserCreationException.class);
        exception.expectMessage(containsString(UserCreationExceptionEnum.PASSWORD_IS_BAD.getValue()));
        service.maybeCreateUser(username, password, email);
        fail();
    }
    
    @Test
    public void testCreateUserEmailValidation() throws UserCreationException {
        String username = "username";
        String password = "password1!";
        String email = "email";
        User mockUser = new User(username, email);
        
        when(service.userPersistence.createUser(eq(username), any(byte[].class), eq(email))).thenReturn(mockUser);
        when(service.userPersistence.checkEmailExists(email)).thenReturn(Boolean.FALSE);
        when(service.userPersistence.checkUsernameExists(username)).thenReturn(Boolean.FALSE);
        exception.expect(UserCreationException.class);
        exception.expectMessage(containsString(UserCreationExceptionEnum.BAD_EMAIL.getValue()));
        service.maybeCreateUser(username, password, email);
        fail();
    }
    
    @Test
    public void testCreateUserEmailValidationEmailExists() throws UserCreationException {
        String username = "username";
        String password = "password1!";
        String email = "email@test.com";
        User mockUser = new User(username, email);
        
        when(service.userPersistence.createUser(eq(username), any(byte[].class), eq(email))).thenReturn(mockUser);
        when(service.userPersistence.checkEmailExists(email)).thenReturn(Boolean.TRUE);
        when(service.userPersistence.checkUsernameExists(username)).thenReturn(Boolean.FALSE);
        exception.expect(UserCreationException.class);
        exception.expectMessage(containsString(UserCreationExceptionEnum.EMAIL_EXISTS.getValue()));
        service.maybeCreateUser(username, password, email);
        fail();
    }
    
    @Test
    public void testCreateUserUsernameValidationUsernameExists() throws UserCreationException {
        String username = "username";
        String password = "password1!";
        String email = "email@test.com";
        User mockUser = new User(username, email);
        
        when(service.userPersistence.createUser(eq(username), any(byte[].class), eq(email))).thenReturn(mockUser);
        when(service.userPersistence.checkEmailExists(email)).thenReturn(Boolean.FALSE);
        when(service.userPersistence.checkUsernameExists(username)).thenReturn(Boolean.TRUE);
        exception.expect(UserCreationException.class);
        exception.expectMessage(containsString(UserCreationExceptionEnum.USER_EXISTS.getValue()));
        service.maybeCreateUser(username, password, email);
        fail();
    }
    
    @Test
    public void testCreateUserUsernameValidationUsernameTooLong() throws UserCreationException {
        String username = "usernameusernameusernameusernameusernameusernameusernameusername";
        String password = "password1!";
        String email = "email@test.com";
        User mockUser = new User(username, email);
        
        when(service.userPersistence.createUser(eq(username), any(byte[].class), eq(email))).thenReturn(mockUser);
        when(service.userPersistence.checkEmailExists(email)).thenReturn(Boolean.FALSE);
        when(service.userPersistence.checkUsernameExists(username)).thenReturn(Boolean.FALSE);
        exception.expect(UserCreationException.class);
        exception.expectMessage(containsString(UserCreationExceptionEnum.USERNAME_IS_TOO_LONG.getValue()));
        service.maybeCreateUser(username, password, email);
        fail();
    }
    
    @Test
    public void testCreateUserUsernameValidationUsernameValidOnly() throws UserCreationException {
        String username = "username OR";
        String password = "password1!";
        String email = "email@test.com";
        User mockUser = new User(username, email);
        
        when(service.userPersistence.createUser(eq(username), any(byte[].class), eq(email))).thenReturn(mockUser);
        when(service.userPersistence.checkEmailExists(email)).thenReturn(Boolean.FALSE);
        when(service.userPersistence.checkUsernameExists(username)).thenReturn(Boolean.FALSE);
        exception.expect(UserCreationException.class);
        exception.expectMessage(containsString(UserCreationExceptionEnum.USERNAME_IS_BAD.getValue()));
        service.maybeCreateUser(username, password, email);
        fail();
    }
}
