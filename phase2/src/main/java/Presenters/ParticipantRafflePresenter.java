package main.java.Presenters;

import main.java.SystemManagers.OrganizerSystemManager;
import main.java.SystemManagers.ParticipantSystemManager;

import java.io.IOException;
import java.util.ArrayList;
import main.java.DatabaseRe.RaffleDataHelper;

public class ParticipantRafflePresenter {
    private String username;
    private String raffleID;
    private OrganizerSystemManager o;
    private ParticipantSystemManager p;

    /**
     *
     * @param username - username of the participant
     * @param raffleID - Raffle ID of the raffle
     */

    public ParticipantRafflePresenter(String username, String raffleID)
    {
        this.username = username;
        this.raffleID = raffleID;
        o = new OrganizerSystemManager();
        p = new ParticipantSystemManager();
        o.setRaffleID(raffleID);
        o.setUsername(username);
        p.setUsername(username);
        p.setRaffleID(username+":"+raffleID);
    }

    /**
     * Returns basic raffle details as a String
     * @return String
     */

    public String getBasicRaffleDetails()
    {
        ArrayList<Object> details = o.getRaffleDetails(raffleID);
        if(!isWinner())
            return "Raffle Name: "+details.get(0)+"\nRaffle Rules: "+details.get(2)+"\nEnd Date: "+(details.get(3)).toString()+"\nOrganizer: "+details.get(7);
        else
            return "Raffle Name: "+details.get(0)+"\nRaffle Rules: "+details.get(2)+"\nEnd Date: "+(details.get(3)).toString()+"\nOrganizer: "+details.get(7)+"\n\nYou are a winner of this raffle!!!";
    }

    /**
     * Returns task IDs of the raffle
     * @return String[]
     * @throws IOException
     */
    public String[] getTaskIDs() throws IOException
    {
        ArrayList<String> taskIDs = (ArrayList<String>) o.getRaffleDetails(raffleID).get(4);
        String[] taskIDArray = new String[taskIDs.size()];
        for(int i = 0;i<taskIDs.size();i++)
        {
            taskIDArray[i] = taskIDs.get(i);
        }
        return taskIDArray;
    }

    /**
     * Formats task details
     * @param taskID - task ID
     * @return String[]
     * @throws Exception
     */
    public String[] formatTaskDetails(String taskID)throws Exception
    {
        String[] taskDetails = new String[2];
        taskDetails[0] = "Task Name: "+o.getTaskInfo(raffleID,taskID).get(1);
        taskDetails[1] = "Description: "+o.getTaskInfo(raffleID,taskID).get(3)+"\nLink: "+o.getTaskInfo(raffleID,taskID).get(2);
        return taskDetails;
    }

    /**
     * Returns whether the participant is a winner
     * @return boolean
     */
    public boolean isWinner()
    {
        ArrayList<String> pIDs = (ArrayList<String>) o.getRaffleDetails(raffleID).get(6);
        RaffleDataHelper dh = new RaffleDataHelper();
        return pIDs.contains(dh.getPtcUserIdFromUsername(username));
    }

    /**
     * Returns whether the participant is enrolled
     * @return boolean
     */
    public boolean isEnrolled()
    {
        ArrayList<String> pIDs = (ArrayList<String>) o.getRaffleDetails(raffleID).get(5);
        RaffleDataHelper dh = new RaffleDataHelper();
        return pIDs.contains(dh.getPtcUserIdFromUsername(username));
    }

    /**
     * Returns whether the participant has completed the task
     * @param taskID - task ID
     * @return boolean
     * @throws IOException
     */
    public boolean hasCompletedTask(String taskID)throws IOException
    {
        return p.hasCompletedTasks(taskID);
    }
}
