/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.exceptions;

import lydichris.smashbracket.enums.EntrantCreationExceptionEnum;

/**
 *
 * @author cgmcandrews
 */
public class EntrantCreationException extends RuntimeException {

    private static final long serialVersionUID = 4664456874499611218L;
    private EntrantCreationExceptionEnum errorEnum = EntrantCreationExceptionEnum.UNKNOWN_ERROR;

    public EntrantCreationException (EntrantCreationExceptionEnum code){
        super(code.getValue());
        errorEnum = code;
    }
            
    public String getErrorEnum(){
        return this.errorEnum.getValue();
    }

}
