package repository.impl;

import entity.User;
import mapper.RowMapper;
import repository.UserRepository;
import tools.DataSource;
import tools.JdbcTemplate;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsersRepositoryJdbcImpl implements UserRepository {

    private final JdbcTemplate<User> jdbcTemplate;

    private static final String SQL_SELECT_FROM_DRIVER = "select * from driver";
    private static final String SQL_FIND_BY_ID = "select * from driver " +
            "where id = ?";
    private static final String SQL_SAVE = "insert into driver (firstname, secondname, age, date_of_birth, coolness, arm_span)" +
            "values (?, ?, ?, ?, ?, ?)";
    private static final String SQL_REMOVE_BY_ID = "delete from driver " +
            "where id = ?";
    private static final String SQL_REMOVE = "delete from driver " +
            "where firstname = ? and secondname = ? and age = ? and date_of_birth = ? and coolness = ? and arm_span = ?";
    private static final String SQL_FIND_BY_AGE = "select * from driver " +
            "where age = ?";
    private static final String SQL_UPDATE = "update driver " +
            "set firstname = ?, secondname = ?, age = ?, date_of_birth = ?, coolness = ?, arm_span = ? " +
            "where id = ?";

    public UsersRepositoryJdbcImpl (DataSource dataSource, RowMapper<User> rowMapper) {
        this.jdbcTemplate = new JdbcTemplate<>(dataSource, rowMapper);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.execute(SQL_SELECT_FROM_DRIVER);
    }

    @Override
    public Optional<User> findById(Long id) {
        List<User> userList = jdbcTemplate.execute(SQL_FIND_BY_ID, id);
        if (userList.isEmpty())
            return Optional.empty();
        return Optional.of(userList.getFirst());
    }

    @Override
    public boolean save(User entity) {
        return jdbcTemplate.submit(SQL_SAVE,
                entity.getFirstName(),
                entity.getLastName(),
                entity.getAge(),
                entity.getDateOfBirth(),
                entity.getCoolness(),
                entity.getArmSpan());
    }

    @Override
    public boolean saveManyUsers (List<User> list) {
        for (User user: list) {
            if (!jdbcTemplate.submit(SQL_SAVE,
                    user.getFirstName(),
                    user.getLastName(),
                    user.getAge(),
                    user.getDateOfBirth(),
                    user.getCoolness(),
                    user.getArmSpan()))
                return false;
        }
        return true;
    }

    @Override
    public boolean update(User entity) {
        return jdbcTemplate.submit(SQL_UPDATE,
                entity.getFirstName(),
                entity.getLastName(),
                entity.getAge(),
                entity.getDateOfBirth(),
                entity.getCoolness(),
                entity.getArmSpan(),
                entity.getId());
    }

    @Override
    public boolean remove(User entity) {
        return jdbcTemplate.submit(SQL_REMOVE,
                entity.getFirstName(),
                entity.getLastName(),
                entity.getAge(),
                entity.getDateOfBirth(),
                entity.getCoolness(),
                entity.getArmSpan());
    }

    @Override
    public boolean removeById(Long id) {
        return jdbcTemplate.submit(SQL_REMOVE_BY_ID, id);
    }

    @Override
    public List<User> findAllByAge(Integer age) {
        return jdbcTemplate.execute(SQL_FIND_BY_AGE, age);
    }

    @Override
    public List<Integer> availableOldMans() {
        return jdbcTemplate.execute("select * from driver where age > 100").stream()
                .map(User::getAge)
                .toList();
    }

    @Override
    public List<Date> availableYoungPeoples() {
        return jdbcTemplate.execute("select * from driver where 2024 - EXTRACT(YEAR FROM date_of_birth) < 30").stream()
                .map(User::getDateOfBirth)
                .toList();
    }

    @Override
    public List<Double> availableBasketballPlayers() {
        return jdbcTemplate.execute("select * from driver where arm_span > 2.00").stream()
                .map(User::getArmSpan)
                .toList();
    }
}
