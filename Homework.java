import java.sql.*;
import java.util.Scanner;

public class Homework {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "123";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/test_oris";

    public static void main(String[] args) throws SQLException {
        zadanie1(2);
        zadanie2();
    }

    private static void zadanie1 (int numberOfUsers) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from driver");

        while(result.next()){
            System.out.println(result.getInt("id") + " " + result.getString("firstname"));
        }

        String sqlInsertUser = "insert into driver(firstname, secondname, age) values" +
                "(?,?,?)";

        Scanner scanner = new Scanner(System.in);
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);

        for (int i = 1; i <= numberOfUsers; i++) {
            System.out.println("Задайте имя для " + i + " пользователя");
            String firstName = scanner.nextLine();
            System.out.println("Задайте фамилию для " + i + " пользователя");
            String secondName = scanner.nextLine();
            System.out.println("Задайте возраст для " + i + " пользователя");
            int age = scanner.nextInt();
            scanner.nextLine();

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, secondName);
            preparedStatement.setInt(3, age);
            preparedStatement.addBatch();

            System.out.println(sqlInsertUser + " " + firstName + " " + secondName + " " + age);
        }

        int[] affectedRows = preparedStatement.executeBatch();

        System.out.println("Было добавлено " + affectedRows.length + " строк");
    }

    private static void zadanie2 () throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from driver");

        while (result.next()) {
            System.out.println(result.getString("secondname") + " " + result.getInt("age"));
        }

        String sqlUserSample = "select * from driver " +
                "where secondname = ? and age > ?;";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlUserSample);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите фамилию пользователя: ");
        String secondname = scanner.nextLine();
        System.out.println("Введите ограничение для возраста: ");
        int age = scanner.nextInt();

        preparedStatement.setString(1, secondname);
        preparedStatement.setInt(2, age);

        result = preparedStatement.executeQuery();

        int counterRows = 0;
        while (result.next()) {
            counterRows++;
            System.out.println(result.getString("firstname") + " " + result.getString("secondname") + " " + result.getInt("age"));
        }

        System.out.println("Было выбрано " + counterRows + " строк");
    }
}
