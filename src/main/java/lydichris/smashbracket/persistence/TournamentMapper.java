/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import lydichris.smashbracket.enums.TournamentType;
import lydichris.smashbracket.models.Tournament;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author cgmcandrews
 */
public class TournamentMapper implements RowMapper<Tournament>{
    
    @Override
    public Tournament mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tournament tournament = new Tournament(
                rs.getString("uuid") ,
                rs.getString("name") ,
                rs.getTimestamp("start_time"),
                rs.getInt("size_limit"),
                rs.getString("location"),
                rs.getString("game"),
                rs.getString("host"),
                TournamentType.valueOf(rs.getString("format")),
                rs.getString("description"),
                rs.getInt("is_bracket_visible") == 1);
        
        return tournament;
    }
}
