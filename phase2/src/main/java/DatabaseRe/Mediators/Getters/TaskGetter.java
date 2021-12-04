package main.java.DatabaseRe.Mediators.Getters;

import main.java.DatabaseRe.Mediators.Query;
import main.java.DatabaseRe.Mediators.QueryTools;
import main.java.DatabaseRe.TalkToDatabase.SelectQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskGetter {

    SelectQuery selectQuery = new SelectQuery();
    QueryTools queryTools = new QueryTools();

    public TaskGetter() throws SQLException {
    }

    public ArrayList<String> getTaskIDsInRaffle(String orgRaffleId) throws SQLException {
        String query = Query.getTasksInRafflesQuery(orgRaffleId);
        ResultSet results = selectQuery.getResultSet(query);
        return queryTools.getStrings(results, "taskID");
    }

    public boolean getTaskStatus(String ptcRaffleId, String taskId) throws SQLException {
        String query = Query.getTaskStatusQuery(ptcRaffleId, taskId);
        ResultSet results = selectQuery.getResultSet(query);
        results.next();
        return results.getBoolean("taskStatus");
    }
}
