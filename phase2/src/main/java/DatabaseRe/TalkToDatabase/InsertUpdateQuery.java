package main.java.DatabaseRe.TalkToDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class InsertUpdateQuery {


    public static void run(String query) throws SQLException {
        DatabaseConnector.setConnection();
        Connection connection = DatabaseConnector.connection;
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

}
