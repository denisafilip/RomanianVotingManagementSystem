package Model.database;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.*;

/**
 * Class that creates ONLY one connection to the Microsoft SQL Server Model.database (with Singleton Design Pattern)
 */

public class DatabaseConnection {

    private static final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;databaseName=VotingManagementSystem;";
    private static final  String DATABASE_USERNAME = "denisafilip";
    private static final  String DATABASE_PASSWORD = "PasswordDB";
    private static final BasicDataSource connectionPool = new BasicDataSource();

    static {
        connectionPool.setUrl(DATABASE_URL);
        connectionPool.setUsername(DATABASE_USERNAME);
        connectionPool.setPassword(DATABASE_PASSWORD);
    }

    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }
}
