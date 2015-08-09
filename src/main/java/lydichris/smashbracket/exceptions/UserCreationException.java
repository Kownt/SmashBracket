/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.exceptions;

import lydichris.smashbracket.enums.UserCreationExceptionEnum;

/**
 *
 * @author cgmcandrews
 */
public class UserCreationException  extends Exception {

    private static final long serialVersionUID = 4664456874499611218L;
    private UserCreationExceptionEnum errorEnum = UserCreationExceptionEnum.UNKNOWN_ERROR;

    public UserCreationException (UserCreationExceptionEnum code){
        super(code.getValue());
        errorEnum = code;
    }
            
    public String getErrorEnum(){
        return this.errorEnum.getValue();
    }

}
