/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.persistence;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import javax.sql.DataSource;
import lydichris.smashbracket.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.UUID;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 *
 * @author cgmcandrews
 */
@Service("userPersistence")
public class UserPersistence {


    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public User getUser(String userId) {
        String SQL = "select * from users where UserId = ?";
        User user = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{userId}, new UserMapper());
        return user;
    }

    public User createUser(String username, byte[] passwordHash, String email) {
        UUID uuid = UUID.randomUUID();
        String SQL = "insert into users (username, Userid, passwordhash, email) values (?, ?, ?, ?)";

        jdbcTemplateObject.update(SQL, username, uuid.toString(), passwordHash, email);
        return new User(uuid.toString(), username, email);
    }

    public boolean checkUsernameExists(String username) {
        String SQL = "select count(username) from users where username = ?";
        SqlRowSet result = jdbcTemplateObject.queryForRowSet(SQL, username);
        if (result.next()) {
            return result.getInt("count(username)") > 0;
        } else {
            return false;
        }
    }

    public boolean checkEmailExists(String email) {
        String SQL = "select count(username) from users where email = ?";
        SqlRowSet result = jdbcTemplateObject.queryForRowSet(SQL, email);
        if (result.next()) {
            return result.getInt("count(username)") > 0;
        } else {
            return false;
        }
    }

    public boolean checkUsernamePasswordHashExists(String username, byte[] password) throws SQLException {
        String SQL = "select count(username) from users where username = ? AND passwordHash = ?";
        Object[] params = new Object[]{ 
        username,
        password
        };
        
        int result = jdbcTemplateObject.queryForObject(SQL, params, Integer.class);
        return result > 0;
       
    }

    public User getUserByUserName(String username) {
       String SQL = "select * from users where username = ?";
        SqlRowSet result = jdbcTemplateObject.queryForRowSet(SQL, username);
        if (result.next()) {
            User user = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{username}, new UserMapper());
            return user;
        } else {
            return null;
        }
    }
}
