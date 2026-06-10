package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static void main(String[] args) {

        try {

            Connection connection =
                DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/library_management",
                    "postgres",
                    "postgres"
                );

            System.out.println("Connected Successfully");

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}