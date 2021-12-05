package main.java.DatabaseRe.Mediators.Modifiers;


import main.java.DatabaseRe.TalkToDatabase.InsertUpdateQuery;
import main.java.DatabaseRe.UpdateQueries;

import java.sql.SQLException;

public class TaskModifier {

    public void setStatus(String ptcRaffleId, String taskCompletedId, boolean status) throws SQLException {
        String query = UpdateQueries.taskStatus(ptcRaffleId, taskCompletedId, status);
        InsertUpdateQuery.run(query);
    }
}
