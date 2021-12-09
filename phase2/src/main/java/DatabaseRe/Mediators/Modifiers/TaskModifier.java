package main.java.DatabaseRe.Mediators.Modifiers;


import main.java.DatabaseRe.Mediators.InsertQueries;
import main.java.DatabaseRe.TalkToDatabase.InsertUpdateQuery;
import main.java.DatabaseRe.UpdateQueries;

import java.sql.SQLException;
import java.util.ArrayList;

public class TaskModifier {
    static InsertUpdateQuery insertUpdateQuery = new InsertUpdateQuery();
    public void setStatus(String ptcRaffleId, String taskCompletedId, boolean status) throws SQLException {
        String query = UpdateQueries.taskStatus(ptcRaffleId, taskCompletedId, status);
        insertUpdateQuery.run(query);
        insertUpdateQuery.close();
    }

    public void removeTaskIds(ArrayList<String> taskIDs) {
        for (String taskID : taskIDs) {
            String query = UpdateQueries.deleteTask(taskID);
            String query2 = UpdateQueries.deleteTaskFromStatus(taskID);
            try {
                insertUpdateQuery.run(query);
                insertUpdateQuery.close();
                insertUpdateQuery.run(query2);
                insertUpdateQuery.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
