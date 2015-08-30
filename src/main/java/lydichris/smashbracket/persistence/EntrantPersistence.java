/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.persistence;

import java.util.UUID;
import javax.sql.DataSource;
import lydichris.smashbracket.models.Entrant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


/**
 *
 * @author cgmcandrews
 */
@Service("entrantPersistence")
public class EntrantPersistence {

    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    public Entrant createEntrant(Entrant entrant) {
        UUID uuid = UUID.nameUUIDFromBytes((entrant.getTag() + entrant.getTournamentId()).getBytes());
        String SQL = "insert into entrants (uuid, tournament_uuid, user_uuid, tag, seed) values (?, ?, ?, ?, ?)";
        entrant.setId(uuid.toString());
        jdbcTemplateObject.update(SQL, entrant.getId(), entrant.getTournamentId(), entrant.getUserId(), entrant.getTag(), entrant.getSeed());
        return entrant;
    }

    public Entrant getEntrant(String uuid) {
        String SQL = "select * from entrants where uuid = ?";
        Entrant entrant = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{uuid}, new EntrantMapper());
        return entrant;
    }

    public void deleteEntrant(String uuid) {
        String SQL = "delete from entrants where uuid = ?";
        jdbcTemplateObject.update(SQL, uuid);
    }

    public Entrant editEntant(String tag, Integer seed, String entrantUuid) {
        String SQL = "update entrants set tag = COALESCE(?, tag), seed = COALESCE(CAST(? AS NUMBER), seed) where uuid = ?";  
        jdbcTemplateObject.update(SQL, tag, seed, entrantUuid);
        return getEntrant(entrantUuid);
    }
}
