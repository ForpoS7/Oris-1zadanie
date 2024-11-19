package mapper;

import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("firstname"),
                resultSet.getString("secondname"),
                resultSet.getInt("age"),
                resultSet.getDate("date_of_birth"),
                resultSet.getInt("coolness"),
                resultSet.getDouble("arm_span")
        );
    }
}
