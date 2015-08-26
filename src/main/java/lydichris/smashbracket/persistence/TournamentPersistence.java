/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.persistence;

import java.util.UUID;
import javax.sql.DataSource;
import lydichris.smashbracket.models.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author cgmcandrews
 */
@Service("tournamentPersistence")
public class TournamentPersistence {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    public Tournament createTournament(Tournament tournament) {
        UUID uuid = UUID.randomUUID();
        String SQL = "insert into tournament (uuid, name, start_date, size_limit, location, game, host, format, description, is_bracket_visible) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
       
        jdbcTemplateObject.update(SQL,
                uuid.toString(),
                tournament.getName(),
                tournament.getStartTime(),
                tournament.getMaxEntrants(),
                null,
                tournament.getGame(),
                tournament.getHostId(),
                tournament.getTournamentType().getValue(), 
                tournament.getDescription(),
                tournament.isMatchesRevealed());
        
        tournament.setId(uuid.toString());
        return tournament;
    }

    public Tournament editTournament(Tournament tournament) {
        
        //TODO actually write this correctly its all wrong
        String SQL = "update tournament set (uuid, name, start_date, size_limit, location, game, host, format, description, is_bracket_visible) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
       
        jdbcTemplateObject.update(SQL,
                tournament.getId(),
                tournament.getName(),
                tournament.getStartTime(),
                tournament.getMaxEntrants(),
                null,
                tournament.getGame(),
                tournament.getHostId(),
                tournament.getTournamentType().getValue(), 
                tournament.getDescription(),
                tournament.isMatchesRevealed());
        
        return tournament;
    }

    public Tournament getTournament(String uuid) {
        String SQL = "select * from tournaments where uuid = ?";
        Tournament tournament = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{uuid}, new TournamentMapper());
        return tournament;
    }
    
}
