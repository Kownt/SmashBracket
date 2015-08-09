/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.enums;

/**
 *
 * @author cgmcandrews
 */
public enum  UserCreationExceptionEnum {
    
        PASSWORD_TOO_SHORT("Password is under the minimum length"),
        PASSWORD_MISSING_NUMBER("Password needs at least one number"),
        PASSWORD_MISSING_SPECIAL("Password needs at least one special character"),
        PASSWORD_IS_BAD("Password is bad"),
        USER_EXISTS("Username Already Exists"),
        USERNAME_IS_BAD("Username contains illegal characters"),
        USERNAME_IS_TOO_LONG("Username is too long"),
        BAD_EMAIL("Email is a bad format"),
        EMAIL_EXISTS("Email Already Exists"),
        UNKNOWN_ERROR("Unknown Error");
        
        private final String value;

        private UserCreationExceptionEnum(String value) {
                this.value = value;
        }
        
        public String getValue(){
            return value;
        }
}
