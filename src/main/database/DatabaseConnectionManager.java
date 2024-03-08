package main.database;
import main.utils.PropertiesHelper;

import java.sql.*;
import java.util.Properties;


public class DatabaseConnectionManager {
    //todo connection pooling for resource management
    public static Connection getDatabaseConnection() throws SQLException {
        Properties properties = PropertiesHelper.getProperties();
        String dbUrl = properties.getProperty("dburl");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(dbUrl, user, password);
    }

    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
