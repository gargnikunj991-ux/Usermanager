package database;

import java.sql.*;
import model.User;

public class UserDAO {
        private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

        public boolean addUser(User user) {
        String sql
                = "INSERT INTO users(username,age,email,password) VALUES(?,?,?,?)";

        try {
            PreparedStatement statement
                    = connection.prepareStatement(sql);

            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

        }

        return false;
    }
    
    public void searchUser(String username) {

        String sql
                = "SELECT * FROM users WHERE username = ?";

        try {

            PreparedStatement statement
                    = connection.prepareStatement(sql);

            statement.setString(1, username);

            ResultSet result
                    = statement.executeQuery();

            if (result.next()) {

                System.out.println(
                        "Name: "
                        + result.getString("username")
                );

                System.out.println(
                        "Age: "
                        + result.getInt("age")
                );

                System.out.println(
                        "Email: "
                        + result.getString("email")
                );

                
            } else {

                System.out.println(
                        "User not found"
                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public boolean hasUser() {
        String sql = "SELECT * FROM users";

        try {
            Statement statement
                    = connection.createStatement();

            ResultSet result
                    = statement.executeQuery(sql);

            return result.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
     public void displayUser() {

        String sql = "SELECT * FROM  users";

        try {

            Statement statement
                    = connection.createStatement();

            ResultSet result
                    = statement.executeQuery(sql);

            while (result.next()) {

                System.out.println(
                        "Name: "
                        + result.getString("username")
                );
                System.out.println(
                        "Age: "
                        + result.getInt("age")
                );

                System.out.println(
                        "Email: "
                        + result.getString("email")
                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
       public boolean existUser(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try {
            PreparedStatement statement =
        connection.prepareStatement(sql);

        statement.setString(1, username);

        ResultSet result =
        statement.executeQuery();

        return result.next();   
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
    public User loginUser(String username, String password) {

    String sql =
    "SELECT * FROM users WHERE username = ? AND password = ?";

    try {

        PreparedStatement statement =
        connection.prepareStatement(sql);

        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet result =
        statement.executeQuery();

        if(result.next()) {

            return new User(
                result.getString("username"),
                result.getInt("age"),
                result.getString("email"),
                result.getString("password")
            );
        }

    } catch(SQLException e) {
        e.printStackTrace();
    }

    return null;
}

}
