package main.java.DatabaseRe.Mediators.Modifiers;


import main.java.DatabaseRe.Mediators.InsertQueries;
import main.java.DatabaseRe.TalkToDatabase.InsertUpdateQuery;
import main.java.DatabaseRe.UpdateQueries;

import java.sql.SQLException;
import java.util.ArrayList;

public class TaskModifier {

    public void setStatus(String ptcRaffleId, String taskCompletedId, boolean status) throws SQLException {
        String query = UpdateQueries.taskStatus(ptcRaffleId, taskCompletedId, status);
        InsertUpdateQuery.run(query);
    }

    public void removeTaskIds(ArrayList<String> taskIDs) {
        for (String taskID : taskIDs) {
            System.out.print(taskID);
            String query = UpdateQueries.deleteTask(taskID);
            String query2 = UpdateQueries.deleteTaskFromStatus(taskID);
            try {
                InsertUpdateQuery.run(query);
                InsertUpdateQuery.run(query2);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
