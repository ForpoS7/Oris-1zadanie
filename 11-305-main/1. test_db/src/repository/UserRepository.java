package repository;

import entity.User;

import java.sql.Date;
import java.util.List;

public interface UserRepository extends CrudRepository<User> {
    List<User> findAllByAge(Integer age);
    boolean saveManyUsers(List<User> list);
    List<Integer> availableOldMans();
    List<Date> availableYoungPeoples();
    List<Double> availableBasketballPlayers();
}
