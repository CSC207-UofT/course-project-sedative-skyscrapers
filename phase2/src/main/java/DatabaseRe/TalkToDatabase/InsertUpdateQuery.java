package main.java.DatabaseRe.TalkToDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class InsertUpdateQuery {

    public InsertUpdateQuery(String query) throws SQLException {
        Connection connection = DatabaseConnector.connection;
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

}
