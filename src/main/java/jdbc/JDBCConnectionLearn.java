package jdbc;

import java.sql.*;

public class JDBCConnectionLearn {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String username = "root";
        String password = "admin";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            System.out.println("Успешный коннект к БД - jdbc\n");


            statement.execute("drop table IF EXISTS Users;");
            statement.executeUpdate("create table Users(id MEDIUMINT not null auto_increment, name char(30) not null," +
                    "password char(30) not null, primary key(id))");
            statement.executeUpdate("insert into Users (name, password) values('max', '123')");
            statement.executeUpdate("insert into Users set name = 'otherGuy', password = '321'");

//            String userId = "1; delete from db;";
//            String userId = "1' or 1 = '1";
//            ResultSet resultSet = statement.executeQuery("select * from Users where id = '" + userId +"'");
//            while (resultSet.next()) {
//                System.out.println("userName: " + resultSet.getString("name"));
//                System.out.println("userPassword: " + resultSet.getString("password"));
//            }
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from Users where id = ?");
            PreparedStatement preparedStatement = connection.prepareStatement("select name from Users");
//            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("userId: " + resultSet.getString("name"));
//                System.out.println("userPassword: " + resultSet.getString("password"));
            }
//            resultSet.close();
//            statement.close();
//            connection.close();
        }
    }
}
