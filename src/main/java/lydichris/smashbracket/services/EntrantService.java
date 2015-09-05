/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.services;

import java.util.List;
import lydichris.smashbracket.enums.EntrantCreationExceptionEnum;
import lydichris.smashbracket.exceptions.EntrantCreationException;
import lydichris.smashbracket.models.Entrant;
import lydichris.smashbracket.persistence.EntrantPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author cgmcandrews
 */
@Service("entrantService")
public class EntrantService {

    @Autowired UserService userService;
    @Autowired EntrantPersistence entrantPersistence;
    @Autowired TournamentService tournamentService;
    
    public Entrant getEntrant(String uuid) {
        return entrantPersistence.getEntrant(uuid);
    }

    public Entrant createEntrant(String tag, String username, String password, String tournamentUuid) {
        Entrant entrant = new Entrant();
        entrant.setTag(tag);
        entrant.setTournamentUuid(tournamentUuid);
        
        if(!tournamentService.checkTournamentExists(tournamentUuid)){
            throw new EntrantCreationException( EntrantCreationExceptionEnum.TOURNAMENT_LOOKUP_FAILED);
        }
        
        if(!StringUtils.isEmpty(username)){
            if(userService.checkUsernamePasswordHashExists(username, password)) {
                entrant.setUserUuid(userService.getUserByUserName(username).getId());
            } else {
                throw new EntrantCreationException( EntrantCreationExceptionEnum.LOOKUP_FAILED);
            }
        }
        //Throw exception if creating entrant exceeds limit
        return entrantPersistence.createEntrant(entrant);
    }

    public void deleteEntrant(String uuid) {
        entrantPersistence.deleteEntrant(uuid);
    }


    public Entrant editEntrant(String tag, Integer seed, String entrantUuid) {
        return entrantPersistence.editEntant(tag, seed, entrantUuid);
    }

    List<Entrant> getEntrantsInTournament(String id) {
        return entrantPersistence.getEntrantsInTournament(id);
    }
    
}
