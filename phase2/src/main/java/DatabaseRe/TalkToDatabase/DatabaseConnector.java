package main.java.DatabaseRe.TalkToDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseConnector {

    private static final String databaseURL = ConfigConstants.getDB_URL();;
    private static final String user = ConfigConstants.getUSER();
    private static final String password = ConfigConstants.getPASSWORD();
    public static Connection connection;

    public static void setConnection() throws SQLException {
        connection =  DriverManager.getConnection(databaseURL, user, password);;
    }

    public static Connection getConnection() throws SQLException {
        return connection;
    }
}
