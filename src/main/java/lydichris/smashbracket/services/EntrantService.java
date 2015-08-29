/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.services;

import lydichris.smashbracket.enums.EntrantCreationExceptionEnum;
import lydichris.smashbracket.exceptions.EntrantCreationException;
import lydichris.smashbracket.models.Entrant;
import lydichris.smashbracket.persistence.EntrantPersistence;
import lydichris.smashbracket.persistence.TournamentPersistence;
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
    @Autowired TournamentPersistence tournamentPersistence;
    
    public Entrant getEntrant(String uuid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Entrant createEntrant(String tag, String username, String password, String tournamentUuid) {
        Entrant entrant = new Entrant();
        entrant.setTag(tag);
        entrant.setTournamentId(tournamentUuid);
        if(StringUtils.hasText(username) && userService.checkUsernamePasswordHashExists(username, password)) {
            entrant.setUser(userService.getUserByUserName(username).getId());
        } else {
            throw new EntrantCreationException( EntrantCreationExceptionEnum.LOOKUP_FAILED);
        }
        //Throw exception if creating entrant exceeds limit
        return entrantPersistence.createEntrant(entrant);
    }

    public Entrant deleteEntrant(String uuid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Entrant editEntrant(String tag, String entrantUuid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
