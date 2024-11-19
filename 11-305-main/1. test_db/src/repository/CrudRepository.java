package repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    List<T> findAll() throws SQLException;

    Optional<T> findById(Long id);

    boolean save(T entity);

    boolean update(T entity);

    boolean remove(T entity);

    boolean removeById(Long id);
}
