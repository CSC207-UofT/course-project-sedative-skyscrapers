package main.java.DatabaseRe.TalkToDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectQuery {

    private Statement statement;
    private Connection connection;

    public SelectQuery() throws SQLException {
        this.connection = connection;
        this.statement = statement;
    }

    /**
     * @return the resultSet of the query as proposed during instantiation
     *
     * Tip: Use resultSet.getString(<column_name>) on the returned ResultSet object to get the details under
     * a while loop of resultSet.next()
     */
    public ResultSet getResultSet(String query) throws SQLException {
        DatabaseConnector.setConnection();
        this.connection = DatabaseConnector.getConnection();
        this.statement = this.connection.createStatement();
        return statement.executeQuery(query);
    }

    public void close() throws SQLException {
        this.connection.close();
        this.statement.close();
    }
}
