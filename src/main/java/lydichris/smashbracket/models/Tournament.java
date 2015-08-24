/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.models;

import java.sql.Timestamp;
import java.util.Date;
import lydichris.smashbracket.enums.TournamentType;

/**
 *
 * @author cgmcandrews
 */
public class Tournament {
    
    private String id;
    private String name;
    private String description;
    private int maxEntrants;
    private String game; 
    private Date startTime;
    private String hostId;
    private TournamentType tournamentType;
    private boolean matchesRevealed = false;

    public Tournament(){
        
    }
    
    public Tournament(String id, String name, Timestamp startTime, int maxEntrants, String location, String game, String hostUuid, TournamentType tournamentType, String description, boolean matchesRevealed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.maxEntrants = maxEntrants;
        this.hostId = hostUuid;
        this.game = game;
        this.startTime = startTime;
        this.tournamentType = tournamentType;
        this.matchesRevealed = matchesRevealed;
    }

    public boolean isMatchesRevealed() {
        return matchesRevealed;
    }

    public void setMatchesRevealed(boolean matchesRevealed) {
        this.matchesRevealed = matchesRevealed;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxEntrants() {
        return maxEntrants;
    }

    public void setMaxEntrants(int maxEntrants) {
        this.maxEntrants = maxEntrants;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public TournamentType getTournamentType() {
        return tournamentType;
    }

    public void setTournamentType(TournamentType tournamentType) {
        this.tournamentType = tournamentType;
    } 
}
