/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.persistence;

import java.util.UUID;
import lydichris.smashbracket.models.Entrant;


/**
 *
 * @author cgmcandrews
 */
public class EntrantPersistence {

    public Entrant createEntrant(Entrant entrant) {
        UUID uuid = UUID.fromString(entrant.getTag() + entrant.getTournamentId());
        String SQL = "insert into entrants (username, Userid, passwordhash, email) values (?, ?, ?, ?)";

        //jdbcTemplateObject.update(SQL, username, uuid.toString(), passwordHash, email);
        return null;
    }
}
