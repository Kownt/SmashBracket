/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.services;

import java.util.Date;
import java.util.List;
import lydichris.smashbracket.enums.TournamentType;
import lydichris.smashbracket.models.Tournament;
import lydichris.smashbracket.persistence.TournamentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cgmcandrews
 */
@Service("tournamentService")
public class TournamentService {

    //@Autowired BracketService bracketService;
    @Autowired TournamentPersistence tournamentPersistence;

    public Tournament createTournament(String name, String description, int maxEntrants, String game, TournamentType tournamentType, Date startTime, String hostId) {
        
        Tournament tournament = new Tournament();
        tournament.setName(name);
        tournament.setDescription(description);
        tournament.setMaxEntrants(maxEntrants);
        tournament.setGame(game);
        tournament.setTournamentType(tournamentType);
        tournament.setStartTime(startTime);
        tournament.setHostId(hostId);
        
        return tournamentPersistence.createTournament(tournament);
    }

    public Tournament editTournament(String tournamentUuid, String name, String description, int maxEntrants, String game, TournamentType tournamentType, Date startTime, String hostId, boolean seed) {
        
        Tournament tournament = new Tournament();
        tournament.setName(name);
        tournament.setDescription(description);
        tournament.setMaxEntrants(maxEntrants);
        tournament.setGame(game);
        tournament.setTournamentType(tournamentType);
        tournament.setStartTime(startTime);
        tournament.setHostId(hostId);
        tournament.setId(tournamentUuid);
        
        return tournamentPersistence.editTournament(tournament);
    }
    
    public Tournament editTournament(Tournament tournament, boolean seed) {
        return tournamentPersistence.editTournament(tournament);
    }

    public Tournament getTournament(String uuid) {
       return tournamentPersistence.getTournament(uuid);
    }

    public void deleteTournament(String uuid) {
        //TODO more logic to only delete a tournament if it has not started.
        tournamentPersistence.deleteTournament(uuid);
    }

    boolean checkTournamentExists(String tournamentUuid) {
         return !(getTournament(tournamentUuid) == null);
    }

    public List<Tournament> getTournament(int offset, int size, String query) {
        int MAX_QUERY_SIZE = 25;
        
        //TODO implement query...
        if(size > MAX_QUERY_SIZE){
            throw new RuntimeException("Query size exceeded limits please limit your request to " + MAX_QUERY_SIZE);
        }
        return tournamentPersistence.getTournaments(offset, size, query);
        
    }
    
    
}
