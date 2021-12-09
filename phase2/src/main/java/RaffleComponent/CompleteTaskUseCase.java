package main.java.RaffleComponent;

import main.java.DatabaseRe.AccessData;
import main.java.DatabaseRe.ProvideData;
import main.java.Helpers.UseCaseDateFormatter;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CompleteTaskUseCase {

    private ParticipantRaffleEntity ptcRaffle;
    private final String taskId;
    private DataAccessPoint dataAccess;
    private DataProviderPoint dataUploader;
    private ArrayList<String> ptcCompletedTasks;

    /**
     * Constructor for the use case handling an event of a user completing a raffle task
     * @param taskId the id of the task completed by the participant
     * @param raffleId the id of the participant raffle to which the task being completed belongs to
     */
    public CompleteTaskUseCase(String raffleId, String taskId){
        String[] IDs = raffleId.split(":");

        try {
            dataAccess = new AccessData();
            try {
                ArrayList<Object> orgRaffleInfo = dataAccess.getOrganizerRaffleById(IDs[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        catch(SQLException ioe)
        {
            ioe.getStackTrace();
        }

        try {
            dataUploader = new ProvideData();
        }
        catch(SQLException ioe)
        {
            ioe.getStackTrace();
        }

        ArrayList<Object> ptcRaffleInfo = null;
        try {
            ptcRaffleInfo = dataAccess.getParticipantRaffleById(raffleId);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        if (ptcRaffleInfo != null) {
            String date = ptcRaffleInfo.get(5).toString();
            int[] dateData = UseCaseDateFormatter.formatDateIntoStrings(date);
            this.ptcRaffle = new ParticipantRaffleEntity((String) ptcRaffleInfo.get(2),
                    Integer.parseInt(ptcRaffleInfo.get(1).toString()),
                    LocalDate.of(dateData[0], dateData[1], dateData[2]));
            this.ptcRaffle.setRaffleId(raffleId);
            this.ptcRaffle.setRaffleRules((String) ptcRaffleInfo.get(3));
            this.ptcRaffle.setTaskIdList((ArrayList<String>) ptcRaffleInfo.get(6));
        }
        this.taskId = taskId;

    }

    /**
     * Informs the program that the task under this.taskId has been completed
     * @return  whether the task was successfully completed and update this.ptcCompletedTasks
     */
    public boolean completeTask(){
        ArrayList<String> completedTaskIds = new ArrayList<>();

        if (this.taskId != null) {
            this.ptcRaffle.getTaskIdList().remove(this.taskId);  // pop tasks from the tasks to do by user

            // compare orgRaffleTaskIdList to ptcRaffleTaskIdList to return completed task Ids
            try {
                completedTaskIds = generateCompletedTaskIds();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String username = this.ptcRaffle.getRaffleId().split(":")[0];
            String ptcUserID = null;
            try {
                ptcUserID = this.dataAccess.getUserIDFromUsername(username, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.dataUploader.setTaskStatus(ptcUserID, this.taskId, true);

            this.ptcCompletedTasks = completedTaskIds;
            return true;
        }
        // taskId not provided
        return false;

    }

    /**
     * Contrast the tasks to be done by a user to the original set of tasks to see which tasks have been completed
     * @return the difference between the taskIdList of a participant of a raffle and the reference organizer raffle
     */
    private ArrayList<String> generateCompletedTaskIds() throws SQLException {

        ArrayList<String> completedTaskIds = new ArrayList<>();

        String username = this.ptcRaffle.getRaffleId().split(":")[0];
        String ptcUserID = null;
        try {
            ptcUserID = this.dataAccess.getUserIDFromUsername(username, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String taskId: this.ptcRaffle.getTaskIdList()){
            if (this.dataAccess.hasCompletedTask(ptcUserID, taskId)){  // task has been completed and popped
                completedTaskIds.add(taskId);
            }
            // else, task hasn't been completed
        }

        return completedTaskIds;
    }

    public ArrayList<String> getPtcCompletedTasks() {
        return ptcCompletedTasks;
    }


}
