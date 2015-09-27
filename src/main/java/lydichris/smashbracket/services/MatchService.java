/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.services;

import java.util.List;
import lydichris.smashbracket.models.Match;
import lydichris.smashbracket.persistence.MatchPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cgmcandrews
 */
@Service("matchService")
public class MatchService {

    @Autowired MatchPersistence matchPersistence;
    public Match getMatch(String matchUuid) {
        return matchPersistence.getMatch(matchUuid);
    }

    public List<Match> getMatchesInTournament(String tournamentUuid) {
        return matchPersistence.getMatchesInTournament(tournamentUuid);
    }
    
    
}
