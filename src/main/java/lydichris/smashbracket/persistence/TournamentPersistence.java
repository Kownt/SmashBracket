/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.persistence;

import java.util.ArrayList;
import java.util.List;
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

    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    public Tournament createTournament(Tournament tournament) {
        UUID uuid = UUID.randomUUID();
        String SQL = "insert into tournaments (uuid, name, start_time, size_limit, location, game, host, format, description, is_bracket_visible) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
       
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
        
        String SQL = "update tournaments set name = ?, start_time = ?, size_limit = ?, location = ?, game = ?, host = ?, format = ?, description = ?, is_bracket_visible= ? where uuid = ?";
       
        jdbcTemplateObject.update(SQL,
                tournament.getName(),
                tournament.getStartTime(),
                tournament.getMaxEntrants(),
                null,
                tournament.getGame(),
                tournament.getHostId(),
                tournament.getTournamentType().getValue(), 
                tournament.getDescription(),
                tournament.isMatchesRevealed(),
                tournament.getId());
        
        //Throw on failure to update
        return tournament;
    }

    public Tournament getTournament(String uuid) {
        String SQL = "select * from tournaments where uuid = ?";
        //Should throw exception for not finding
        Tournament tournament = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{uuid}, new TournamentMapper());
        return tournament;
    }

    public void deleteTournament(String uuid) {
        String SQL = "delete from tournaments where uuid = ?";
        jdbcTemplateObject.update(SQL, uuid);
    }

    public List<Tournament> getTournaments(int offset, int size, String query) {
        String SQL = "select uuid from tournaments where rownum > " + offset + " and rownum <= " + (offset + size);
        List<String> tournamentUuids = (List<String>) jdbcTemplateObject.queryForList(SQL, String.class);
        List<Tournament> tournaments = new ArrayList<>();
        for(String tournamentUuid : tournamentUuids){
            tournaments.add(getTournament(tournamentUuid));
        }
        return tournaments;
    }
    
}
