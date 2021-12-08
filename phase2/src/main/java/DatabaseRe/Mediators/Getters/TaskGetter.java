package main.java.DatabaseRe.Mediators.Getters;

import main.java.DatabaseRe.Mediators.SelectQueries;
import main.java.DatabaseRe.Mediators.DataTools;
import main.java.DatabaseRe.TalkToDatabase.SelectQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskGetter {

    SelectQuery selectQuery = new SelectQuery();
    DataTools dataTools = new DataTools();

    public TaskGetter() throws SQLException {
    }

    public ArrayList<String> getTaskIDsInRaffle(String orgRaffleId) throws SQLException {
        String query = SelectQueries.getTasksInRafflesQuery(orgRaffleId);
        ResultSet results = selectQuery.getResultSet(query);
        ArrayList<String> taskID = dataTools.getStrings(results, "taskID");
        results.close();
        selectQuery.close();
        return taskID;
    }

    public boolean getTaskStatus(String participantID, String taskId) throws SQLException {
        String query = SelectQueries.getTaskStatusQuery(participantID, taskId);
        ResultSet results = selectQuery.getResultSet(query);
        results.next();
        boolean taskStatus = results.getBoolean("taskStatus");
        results.close();
        selectQuery.close();
        return taskStatus;
    }

    public ArrayList<String> getUsedTaskIDs() throws SQLException {
        String query = SelectQueries.usedTaskIDs;
        ResultSet results = selectQuery.getResultSet(query);
        ArrayList<String> taskID = dataTools.getStrings(results, "taskID");
        results.close();
        selectQuery.close();
        return taskID;
    }
}
