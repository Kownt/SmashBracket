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
public class Entrant {
    
    private String id;
    private String tag;
    private String userId;
    private String tournamentId;
    private int seed = -1;
    
    public Entrant(){
        
    }
    
    public Entrant(String id, String tag, String userId, String tournamentId, int seed){
        this.id = id;
        this.tag = tag;
        this.userId = userId;
        this.tournamentId = tournamentId;
        this.seed = seed;
    }
    
    public String getId() {
        return id;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUser(String userId) {
        this.userId = userId;
    }
}
