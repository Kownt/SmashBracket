/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.persistence;


import javax.sql.DataSource;
import lydichris.smashbracket.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 *
 * @author cgmcandrews
 */
@Service("userPersistence")
public class UserPersistence {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public User createUser(String userName) {
        UUID uuid = UUID.randomUUID();
        String SQL = "insert into users (UserName, UserId) values (?, ?)";

        jdbcTemplateObject.update(SQL, userName, uuid.toString());
        //Replace with a logger
        System.out.println("Created Record Name = " + userName);
        return new User(uuid.toString(), userName);
    }

    public User getUser(String userId) {
        String SQL = "select * from users where UserId = ?";
        User user = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{userId}, new UserMapper());
        return user;
    }
}
