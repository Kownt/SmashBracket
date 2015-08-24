/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.services;

import java.util.Date;
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

    public Tournament createTournament(Tournament tournament, boolean seed) {
        

        
        return tournamentPersistence.createTournament(tournament);
    }

    public Tournament editTournament(Tournament tournament, boolean seed) {
        return tournamentPersistence.editTournament(tournament);
    }

    public Tournament getTournament(String uuid) {
       return tournamentPersistence.getTournament(uuid);
    }
    
}
