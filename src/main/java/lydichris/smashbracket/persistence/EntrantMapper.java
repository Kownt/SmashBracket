/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import lydichris.smashbracket.models.Entrant;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author cgmcandrews
 */
public class EntrantMapper implements RowMapper<Entrant> {

    @Override
    public Entrant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Entrant entrant = new Entrant(
                rs.getString("uuid"),
                rs.getString("tag"),
                rs.getString("user_uuid"),
                rs.getString("tournament_uuid"),
                rs.getInt("seed"));

        return entrant;
    }
}
