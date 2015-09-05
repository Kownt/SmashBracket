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
    
    private String uuid;
    private String tag;
    private String userUuid;
    private String tournamentUuid;
    private int seed = -1;
    
    public Entrant(){
        
    }
    
    public Entrant(String id, String tag, String userId, String tournamentId, int seed){
        this.uuid = id;
        this.tag = tag;
        this.userUuid = userId;
        this.tournamentUuid = tournamentId;
        this.seed = seed;
    }
    
    public String getUuid() {
        return uuid;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public String getTournamentUuid() {
        return tournamentUuid;
    }

    public void setTournamentUuid(String tournamentId) {
        this.tournamentUuid = tournamentId;
    }

    public void setUuid(String id) {
        this.uuid = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userId) {
        this.userUuid = userId;
    }
}
