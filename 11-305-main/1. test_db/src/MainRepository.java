import entity.User;
import mapper.RowMapper;
import mapper.UserRowMapper;
import repository.impl.UsersRepositoryJdbcImpl;
import tools.DataSource;

import java.sql.Date;

public class MainRepository {

    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        RowMapper<User> userRowMapper = new UserRowMapper();

        UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl(dataSource, userRowMapper);

//        List<User> userList = usersRepositoryJdbc.findAll();
//        userList.forEach(System.out::println);

//        System.out.println(usersRepositoryJdbc.findById(1L));

//        User user = new User("Иван", "Иванов", 30, Date.valueOf("1993-07-15"), 90, 1.80);
//        System.out.println(usersRepositoryJdbc.save(new User(
//                "Алексей", "Сидоров", 42, Date.valueOf("1981-11-05"), 75, 2.20
//        )));

//        List<User> userList = List.of(user, new User("Елена", "Петрова", 25, Date.valueOf("1998-03-20"), 95, 1.75));
//        usersRepositoryJdbc.saveManyUsers(userList);

//        usersRepositoryJdbc.remove(user);

//        usersRepositoryJdbc.findAllByAge(337).forEach(System.out::println);

//        User user = new User(13L,"Елена", "Марковна", 25, Date.valueOf("1998-03-20"), 95, 1.75);
//        usersRepositoryJdbc.update(user);

        usersRepositoryJdbc.availableOldMans().forEach(System.out::println);
        usersRepositoryJdbc.availableYoungPeoples().forEach(System.out::println);
        usersRepositoryJdbc.availableBasketballPlayers().forEach(System.out::println);
    }

}
