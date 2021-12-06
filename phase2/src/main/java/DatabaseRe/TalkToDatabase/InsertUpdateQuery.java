package main.java.DatabaseRe.TalkToDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class InsertUpdateQuery {

    private Statement statement;
    private Connection connection;

    public void run(String query) throws SQLException {
        DatabaseConnector.setConnection();
        this.connection = DatabaseConnector.connection;
        this.statement = this.connection.createStatement();
        this.statement.executeUpdate(query);
    }

    public void close() throws SQLException {
        this.statement.close();
        this.connection.close();
    }

}
