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
public enum TournamentType {

    UNKNOWN("Unknown"),
    SINGLE_ELIMINATION("Single Elimination"),
    DOUBLE_ELIMINATION("Double Elimination"),
    SWISS("Swiss"),
    ROUND_ROBIN("Round Robin"),
    MULTI_STAGE("Multi Stage");
    
    private final String value;

    private TournamentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
