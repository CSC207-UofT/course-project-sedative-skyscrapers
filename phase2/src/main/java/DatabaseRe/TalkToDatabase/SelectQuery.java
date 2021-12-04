package main.java.DatabaseRe.TalkToDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectQuery {

    private final Statement statement;

    public SelectQuery() throws SQLException {
        DatabaseConnector.setConnection();
        Connection connection = DatabaseConnector.getConnection();
        this.statement = connection.createStatement();
    }

    /**
     * @return the resultSet of the query as proposed during instantiation
     *
     * Tip: Use resultSet.getString(<column_name>) on the returned ResultSet object to get the details under
     * a while loop of resultSet.next()
     */
    public ResultSet getResultSet(String query) throws SQLException {
        return statement.executeQuery(query);
    }
}
