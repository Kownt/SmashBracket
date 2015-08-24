/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.services;

import lydichris.smashbracket.models.Tournament;
import org.springframework.stereotype.Service;

/**
 *
 * @author cgmcandrews
 */
@Service("entrantService")
public class EntrantService {

    public Tournament getEntrant(String uuid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Tournament maybeCreateEntrant(String tag, String username, String password, String tournamentUuid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
