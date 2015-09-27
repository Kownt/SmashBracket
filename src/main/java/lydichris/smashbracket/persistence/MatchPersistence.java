/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.persistence;

import java.util.List;
import javax.sql.DataSource;
import lydichris.smashbracket.models.Entrant;
import lydichris.smashbracket.models.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author cgmcandrews
 */
@Service("matchPersistence")
public class MatchPersistence {

    //blah blah blah all classes should implement something and extend an abstract class that has this template
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    //ToDo make this transactional whole thing should rollback in failure
    public List<Match> persistMatches(List<Match> rounds) {
        for (Match match : rounds) {
            String SQL = "insert into matches (uuid, nextMatchUuidWinner, nextMatchUuidLoser,"
                    + " firstPreviousMatch, secondPreviousMatch, winsEntrantOne, winsEntrantTwo,"
                    + "roundNumber, winner, entrantOne, entrantTwo, tournamentUuid) values (?, ?, ?, ?, ?, ? ,? , ? ,? ,? ,?, ?)";
            jdbcTemplateObject.update(SQL, match.getId(), match.getNextMatchUuidWinner(), null,
                    match.getFirstPreviousMatch(), match.getSecondPreviousMatch(), match.getWinsEntrantOne(), match.getWinsEntrantTwo(),
                    match.getRoundNumber(), match.getWinner(), match.getEntrantOne(), match.getEntrantTwo(), match.getTournamentUuid());
        }
        return rounds;
    }

    public Match editMatch(Match match) {
        String SQL = "update matches set winsEntrantOne = ?, winsEtrantTwo = ?, winner = ?, entrantOne = ?, entrantTwo = ? where uuid = ?";
        jdbcTemplateObject.update(SQL,
                match.getWinsEntrantOne(), match.getWinsEntrantTwo(),
                match.getWinner(), match.getEntrantOne(), match.getEntrantTwo(), match.getId());
        return match;
    }

    public void removeMatches(String tournamentUuid) {
        String SQL = "delete from matches where tournamentUuid = ?";
        jdbcTemplateObject.update(SQL, tournamentUuid);
    }

    public List<Match> getMatchesInTournament(String tournamentUuid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Match getMatch(String matchUuid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
