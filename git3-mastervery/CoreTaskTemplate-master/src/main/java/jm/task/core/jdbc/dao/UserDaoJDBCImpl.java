package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection сonnection;
    public UserDaoJDBCImpl()  {
        сonnection = Util.getConnection();
    }

    public void createUsersTable() {
        try {
            String sqlCommand = "CREATE TABLE Users (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20), LastName VARCHAR(20), Age TINYINT)";
            try (Statement statement = сonnection.createStatement()) {
                statement.executeUpdate(sqlCommand);
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }

    }

    public void dropUsersTable() {
        try {
            String sqlCommand = "DROP TABLE Users";
            try (Statement statement = сonnection.createStatement()) {
                statement.executeUpdate(sqlCommand);
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            try (PreparedStatement preparedStatement = сonnection.prepareStatement("INSERT  Users (Name, LastName, Age) VALUES (?, ?, ?)")) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }

    }

    public void removeUserById(long id) {
        try {
            try (PreparedStatement preparedStatement = сonnection.prepareStatement("DELETE FROM Users WHERE Id = ?")) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }

    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        try {
            try ( Statement statement = сonnection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
                while (resultSet.next()) {
                    String name = resultSet.getString(2);
                    String lastname = resultSet.getString(3);
                    Byte age = (byte) resultSet.getInt(4);
                    listUsers.add(new User(name, lastname, age));
                }
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
        return listUsers;
    }

    public void cleanUsersTable() {
        try {
            try (Statement statement = сonnection.createStatement()) {
                statement.executeUpdate("delete from Users");
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
}
