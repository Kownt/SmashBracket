/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lydichris.smashbracket.enums.UserCreationExceptionEnum;
import lydichris.smashbracket.exceptions.UserCreationException;
import lydichris.smashbracket.models.User;
import lydichris.smashbracket.persistence.UserPersistence;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cgmcandrews
 */
@Service("userService")
public class UserService {

    @Autowired
    UserPersistence userPersistence;
    int minPasswordLength = 8;
    int maxUserNameLength = 32;

    public void setUserPersistence(UserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }

    public User getUser(String userId) {
        return userPersistence.getUser(userId);
    }

    public User maybeCreateUser(String username, String password, String email) {
        validatePassword(password);
        byte[] passwordHash;
        try {
            passwordHash = generatePasswordHash(password);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, "Password hashing failed", ex);
            throw new UserCreationException(UserCreationExceptionEnum.PASSWORD_IS_BAD);
        }
        validateEmail(email);
        validateUsername(username);
        return userPersistence.createUser(username, passwordHash, email);
    }

    private void validatePassword(String password) {
        boolean hasAppropriateLength = password.length() >= minPasswordLength;
        boolean hasNumber = password.matches(".*\\d.*");  // "a digit with anything before or after"
        boolean hasSpecial = password.matches(".*[!@#$%^&*].*");
        boolean hasNOT = password.matches(".*NOT.*");
        boolean hasAND = password.matches(".*AND.*");

        if (!hasAppropriateLength) {
            throw new UserCreationException(UserCreationExceptionEnum.PASSWORD_TOO_SHORT);
        } else if (!hasNumber) {
            throw new UserCreationException(UserCreationExceptionEnum.PASSWORD_MISSING_NUMBER);
        } else if (!hasSpecial) {
            throw new UserCreationException(UserCreationExceptionEnum.PASSWORD_MISSING_SPECIAL);
        } else if (hasNOT || hasAND) {
            throw new UserCreationException(UserCreationExceptionEnum.PASSWORD_IS_BAD);
        }
    }

    private void validateEmail(String email) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        if (!emailValidator.isValid(email)) {
            throw new UserCreationException(UserCreationExceptionEnum.BAD_EMAIL);
        } else {
            if (userPersistence.checkEmailExists(email)) {
                throw new UserCreationException(UserCreationExceptionEnum.EMAIL_EXISTS);
            }
        }
    }

    private void validateUsername(String username) {
        boolean hasAppropriateLength = username.length() <= maxUserNameLength;
        boolean hasOnlyNumbersAndLetters = username.matches("^[a-zA-Z0-9]*$");

        if (!hasAppropriateLength) {
            throw new UserCreationException(UserCreationExceptionEnum.USERNAME_IS_TOO_LONG);
        } else if (!hasOnlyNumbersAndLetters) {
            throw new UserCreationException(UserCreationExceptionEnum.USERNAME_IS_BAD);
        } else {
            if (userPersistence.checkUsernameExists(username)) {
                throw new UserCreationException(UserCreationExceptionEnum.USER_EXISTS);
            }
        }
    }

    private byte[] generatePasswordHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        digest.reset();
        return digest.digest(password.getBytes("UTF-8"));
    }
}
