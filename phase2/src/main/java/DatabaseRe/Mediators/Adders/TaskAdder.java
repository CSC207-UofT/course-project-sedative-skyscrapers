package main.java.DatabaseRe.Mediators.Adders;

import main.java.DatabaseRe.Mediators.Getters.TaskGetter;
import main.java.DatabaseRe.Mediators.InsertQueries;
import main.java.DatabaseRe.TalkToDatabase.InsertUpdateQuery;

import java.sql.SQLException;
import java.util.ArrayList;

public class TaskAdder {
    static TaskGetter taskGetter;

    static {
        try {
            taskGetter = new TaskGetter();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void assignParticipantsTaskStatus(String raffleID, ArrayList<String> participantIDs) throws SQLException {
        ArrayList<String> tasks = taskGetter.getTaskIDsInRaffle(raffleID);
        for (String participant : participantIDs) {
            System.out.print(participant);
            for (String task : tasks) {
                System.out.print(task);
                String query = InsertQueries.assignTaskStatusToParticipant(participant, task, false);
                InsertUpdateQuery.run(query);
            }
        }
    }

    public void addTaskIds(String orgRaffleId, ArrayList<String> taskIDs) {
        for (String taskID : taskIDs) {
            String query = InsertQueries.addTaskIdToRaffle(orgRaffleId, taskID);
            try {
                InsertUpdateQuery.run(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addDetails(String taskId, ArrayList<String> taskInfo) {

    }
}
