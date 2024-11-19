package tools;

import mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class JdbcTemplate<T> {

    private final DataSource dataSource;
    private final RowMapper<T> rowMapper;

    public JdbcTemplate (DataSource dataSource, RowMapper<T> rowMapper) {
        this.dataSource = dataSource;
        this.rowMapper = rowMapper;
    }

    public List<T> execute(String sql, Object ... objects) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            IntStream.rangeClosed(1, objects.length)
                    .forEach(i -> {
                        try {
                            preparedStatement.setObject(i,objects[i-1]);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });

            preparedStatement.execute();

            List<T> list = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    list.add(rowMapper.mapRow(resultSet));
                }
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean submit(String sql, Object ... objects) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            IntStream.rangeClosed(1, objects.length)
                    .forEach(i -> {
                        try {
                            preparedStatement.setObject(i,objects[i-1]);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}