package main.java.RaffleComponent;

import main.java.DatabaseRe.AccessData;
import main.java.DatabaseRe.ProvideData;
import main.java.Helpers.PackageRaffleEntityInstance;
import main.java.database.AddParticipant;
import main.java.database.DataExtractor;
import main.java.database.GetTaskDetails;
import main.java.database.JoinUserToRaffle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CompleteTaskUseCase {

    private ParticipantRaffleEntity ptcRaffle;
    private final String taskId;
    private ArrayList<Object> ptcRaffleInfo;
    private ArrayList<Object> orgRaffleInfo;
//    private final PackageRaffleEntityInstance dataPackager;
//    private final GetTaskDetails extractor;
    private DataAccessPoint dataAccess;
    private DataProviderPoint dataUploader;
    private ArrayList<String> ptcCompletedTasks;
//    private AddParticipant taskWriter;

    /**
     * Constructor for the use case handling an event of a user completing a raffle task
     * @param taskId the id of the task completed by the user
     * @param raffleId the id of the raffle to which the task being completed belongs to
     */
    public CompleteTaskUseCase(String raffleId, String taskId){
        String[] IDs = raffleId.split(":");
        System.out.println(raffleId + ",,," + IDs.length);
        try {

            dataAccess = new AccessData();
            try {
                this.orgRaffleInfo = dataAccess.getOrganizerRaffleById(IDs[1]);
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

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.ptcRaffleInfo = null;
        try {
            this.ptcRaffleInfo = dataAccess.getParticipantRaffleById(raffleId);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        if (this.ptcRaffleInfo != null) {
            String date = this.ptcRaffleInfo.get(5).toString();  // todo check for errors
            int day = Integer.parseInt(date.substring(8, 10));
            int month = convertMonthToInt(date.substring(4, 7));
            int year = Integer.parseInt(date.substring(24, 28));
            this.ptcRaffle = new ParticipantRaffleEntity((String)this.ptcRaffleInfo.get(2),
                    Integer.parseInt(this.ptcRaffleInfo.get(1).toString()),
                    LocalDate.of(year, month, day));
            this.ptcRaffle.setRaffleId(raffleId);
            this.ptcRaffle.setRaffleRules((String) ptcRaffleInfo.get(3));
            this.ptcRaffle.setTaskIdList((ArrayList<String>) ptcRaffleInfo.get(6));
        }
        this.taskId = taskId;

    }

    public int convertMonthToInt(String month){
        switch(month){
            case "Jan":
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "May":
                return 5;
            case "Jun":
                return 6;
            case "Jul":
                return 7;
            case "Aug":
                return 8;
            case "Sep":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            case "Dec":
                return 12;
            default:
                return 12;
        }
    }

    /**
     * Informs the program that the task under this.taskId has been completed
     * @return the arraylist containing the ids of all the raffles a participant has completed up to now
     */
    public boolean completeTask(){
        ArrayList<String> completedTaskIds = new ArrayList<>();

        //String[] IDs = ptcRaffle.getRaffleId().split(":");
        //            JoinUserToRaffle joiner = new JoinUserToRaffle();
        //            joiner.setCompletedTask(ptcRaffle.getRaffleId(),taskId);
        //ArrayList<Object> packagedPtcRaffle = this.dataPackager.packageParticipantRaffle(this.ptcRaffle);
        if (this.taskId != null) {
            this.ptcRaffle.getTaskIdList().remove(this.taskId);  // pop tasks from the tasks to do by user
            // compare orgRaffleTaskIdList to ptcRaffleTaskIdList to return completed task Ids
            try {
                completedTaskIds = generateCompletedTaskIds(this.ptcRaffle.getTaskIdList(),
                        (ArrayList<String>) this.orgRaffleInfo.get(4));
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            this.dataUploader.deleteTask(this.ptcRaffle.getRaffleId(), this.ptcRaffle.getTaskIdList());
//            this.dataUploader.deleteTask(this.ptcRaffle.getTaskIdList());
            String username = this.ptcRaffle.getRaffleId().split(":")[0];
            String ptcUserID = null;
            try {
                ptcUserID = this.dataAccess.getUserIDFromUsername(username, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.dataUploader.setTaskStatus(ptcUserID, this.taskId, true);
            // despite not currently completing a task, other tasks might have been previously solved, so we still compare
            this.ptcCompletedTasks = completedTaskIds;
            return true;
        }
        // taskId not provided
        return false;

    }

    /**
     * Extracts the organizer raffle's id from the participant raffleId
     * @param ptcRaffleId participant raffle id containing the organizer raffle id
     * @return the resulting organizer raffle id
     */
    public String orgIdFromPtcId(String ptcRaffleId){
        String[] ptcRaffleIdParts = ptcRaffleId.split(":");
        return ptcRaffleIdParts[1];
    }

    /**
     * Contrast the tasks to be done by a user to the original set of tasks to see which tasks have been completed
     * @param ptcTaskIds arraylist of all the taskIds that have been completed by a ptc of this raffle
     * @param orgTaskIds arraylist of all the taskIds that are to be completed by ptcs of this raffle
     * @return the difference between these two lists of ids
     */
    public ArrayList<String> generateCompletedTaskIds(ArrayList<String> ptcTaskIds,  ArrayList<String> orgTaskIds) throws SQLException {

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

    // for testing purposes
    public void setOrgRaffleInfo(ArrayList<Object> orgRaffleInfo) {
        this.orgRaffleInfo = orgRaffleInfo;
    }

    public void setPtcRaffle(ParticipantRaffleEntity ptcRaffle) {
        this.ptcRaffle = ptcRaffle;
    }

//    public boolean hasCompletedTask(String username,String raffleID)
//    {
//        return dataAccess.hasCompletedTask(raffleID.split(":")[1],username,this.taskId);
//    }

}
