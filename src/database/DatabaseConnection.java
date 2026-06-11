package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection getConnection() {

        try {

            return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/library_management",
                "postgres",
                "postgres"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}