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
public enum  EntrantCreationExceptionEnum {
    
        LOOKUP_FAILED("Failed to find user with specified password"),
        DUPLICATE_TAG("An entrant with that tag is already entered"),
        UNKNOWN_ERROR("Unknown Error");
        
        private final String value;

        private EntrantCreationExceptionEnum(String value) {
                this.value = value;
        }
        
        public String getValue(){
            return value;
        }
}