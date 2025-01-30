package dish_diaries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Method to establish connection with the database
    public static Connection getConnection() throws SQLException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection URL, username, and password
            String url = "jdbc:mysql://localhost:3306/dish_diaries";  // Replace with your database name
            String username = "root";  // Replace with your database username
            String password = "";  // Replace with your database password

            // Return the connection object
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL JDBC driver not found.");
        }
    }
}
