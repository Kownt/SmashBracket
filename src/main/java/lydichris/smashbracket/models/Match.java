/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.models;

/**
 *
 * @author cgmcandrews
 */
public class Match {
    
    private String id;
    private String nextMatchUuidWinner;
    private String firstPreviousMatch;
    private String secondPreviousMatch;
    private int winsEntrantOne;
    private int winsEntrantTwo;
    private int roundNumber;
    private String winner;
    private String entrantOne;
    private String entrantTwo;

    public Match() {
       
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWinsEntrantOne() {
        return winsEntrantOne;
    }

    public void setWinsEntrantOne(int winsEntrantOne) {
        this.winsEntrantOne = winsEntrantOne;
    }

    public int getWinsEntrantTwo() {
        return winsEntrantTwo;
    }

    public void setWinsEntrantTwo(int winsEntrantTwo) {
        this.winsEntrantTwo = winsEntrantTwo;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getEntrantOne() {
        return entrantOne;
    }

    public void setEntrantOne(String entrantOne) {
        this.entrantOne = entrantOne;
    }

    public String getEntrantTwo() {
        return entrantTwo;
    }

    public void setEntrantTwo(String entrantTwo) {
        this.entrantTwo = entrantTwo;
    }
    
    public String getNextMatchUuidWinner() {
        return nextMatchUuidWinner;
    }

    public void setNextMatchUuidWinner(String nextMatchUuidWinner) {
        this.nextMatchUuidWinner = nextMatchUuidWinner;
    }

    public String getFirstPreviousMatch() {
        return firstPreviousMatch;
    }

    public void setFirstPreviousMatch(String firstPreviousMatch) {
        this.firstPreviousMatch = firstPreviousMatch;
    }

    public String getSecondPreviousMatch() {
        return secondPreviousMatch;
    }

    public void setSecondPreviousMatch(String secondPreviousMatch) {
        this.secondPreviousMatch = secondPreviousMatch;
    }
    
    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }
}
