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
    int winsEntrantOne;
    int winsEntrantTwo;
    Entrant winner;
    Entrant entrantOne;
    Entrant entrantTwo;

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

    public Entrant getWinner() {
        return winner;
    }

    public void setWinner(Entrant winner) {
        this.winner = winner;
    }

    public Entrant getEntrantOne() {
        return entrantOne;
    }

    public void setEntrantOne(Entrant entrantOne) {
        this.entrantOne = entrantOne;
    }

    public Entrant getEntrantTwo() {
        return entrantTwo;
    }

    public void setEntrantTwo(Entrant entrantTwo) {
        this.entrantTwo = entrantTwo;
    }
}
