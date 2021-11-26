package main.java.RaffleComponent;

import main.java.Helpers.PackageRaffleEntityInstance;
import main.java.database.AddParticipant;
import main.java.database.DataExtractor;
import main.java.database.GetTaskDetails;
import main.java.database.JoinUserToRaffle;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CompleteTaskUseCase {

    private RaffleEntity ptcRaffle;
    private final String taskId;
    private ArrayList<Object> ptcRaffleInfo;
    private ArrayList<Object> orgRaffleInfo;
    private final PackageRaffleEntityInstance dataPackager;
    private final GetTaskDetails extractor;
    private DataExtractor dataAccess;
    private AddParticipant taskWriter;

    /**
     * Constructor for the use case handling an event of a user completing a raffle task
     * @param taskId the id of the task completed by the user
     * @param raffleId the id of the raffle to which the task being completed belongs to
     */
    public CompleteTaskUseCase(String raffleId, String taskId) throws FileNotFoundException {
        this.extractor = new GetTaskDetails();
        //this.ptcRaffleInfo = extractor
        String[] IDs = raffleId.split(":");
        try {

            dataAccess = new DataExtractor();
            this.orgRaffleInfo = dataAccess.getOrgRaffleInfo(IDs[1]);
        }
        catch(IOException ioe)
        {
            ioe.getStackTrace();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            ptcRaffleInfo = dataAccess.getPtcRaffleInfo(IDs[1]);
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        this.ptcRaffle = new RaffleEntity(ptcRaffleInfo.get(0).toString(), Integer.parseInt(ptcRaffleInfo.get(1).toString()),
                LocalDate.parse(ptcRaffleInfo.get(2).toString(),dtf));
        this.ptcRaffle.setRaffleId(raffleId);
        this.ptcRaffle.setRaffleRules((String)ptcRaffleInfo.get(2));
        this.ptcRaffle.setTaskIdList((ArrayList<String>) ptcRaffleInfo.get(4));
        this.taskId = taskId;
        this.dataPackager = new PackageRaffleEntityInstance();
    }

    /**
     * Informs the program that the task under this.taskId has been completed
     * @return the arraylist containing the ids of all the raffles a participant has completed up to now
     */
    public ArrayList<String> completeTask()throws IOException{
        ArrayList<String> completedTaskIds;

        if (this.ptcRaffle.getTaskIdList().contains(this.taskId)){
            this.ptcRaffle.getTaskIdList().remove(this.taskId);  // pop tasks from the tasks to do by user
            //String[] IDs = ptcRaffle.getRaffleId().split(":");
            JoinUserToRaffle joiner = new JoinUserToRaffle();
            joiner.setCompletedTask(ptcRaffle.getRaffleId(),taskId);

            //ArrayList<Object> packagedPtcRaffle = this.dataPackager.packageParticipantRaffle(this.ptcRaffle);
        }

        // compare orgRaffleTaskIdList to ptcRaffleTaskIdList to return completed task Ids
        completedTaskIds = generateCompletedTaskIds(this.ptcRaffle.getTaskIdList(),
                (ArrayList<String>) this.orgRaffleInfo.get(4));

        // despite not currently completing a task, other tasks might have been previously solved, so we still compare
        return completedTaskIds;

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
    public ArrayList<String> generateCompletedTaskIds(ArrayList<String> ptcTaskIds,  ArrayList<String> orgTaskIds){
        ArrayList<String> completedTaskIds = new ArrayList<>();

        for (String taskId: orgTaskIds){
            if (!ptcTaskIds.contains(taskId)){  // task has been completed and popped
                completedTaskIds.add(taskId);
            }
            // else, task hasn't been completed
        }

        return completedTaskIds;
    }

    // for testing purposes
    public void setOrgRaffleInfo(ArrayList<Object> orgRaffleInfo) {
        this.orgRaffleInfo = orgRaffleInfo;
    }

    public void setPtcRaffle(RaffleEntity ptcRaffle) {
        this.ptcRaffle = ptcRaffle;
    }

    public boolean hasCompletedTask(String username,String raffleID)
    {
        return dataAccess.hasCompletedTask(raffleID.split(":")[1],username,this.taskId);
    }

}
