package lydichris.smashbracket.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import lydichris.smashbracket.models.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author cgmcandrews
 */
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(rs.getString("UserId") , rs.getString("UserName") ,  rs.getString("Email"));
        return user;
    }
}
