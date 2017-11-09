package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Handles the connection to the local SQLite database
 */
public class DBConnection {

    private static final String CONN = "jdbc:sqlite:management.sqlite";

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(CONN);
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}