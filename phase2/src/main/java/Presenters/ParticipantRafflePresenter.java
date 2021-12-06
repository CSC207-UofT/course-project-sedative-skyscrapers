package main.java.Presenters;

import main.java.Web.OrganizerSystemManager;
import main.java.Web.ParticipantSystemManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ParticipantRafflePresenter {
    private String username;
    private String raffleID;
    private OrganizerSystemManager o;
    private ParticipantSystemManager p;

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

    public String getBasicRaffleDetails()
    {
        ArrayList<Object> details = o.getRaffleDetails(raffleID);
        if(!isWinner())
            return "Raffle Name: "+details.get(0)+"\nRaffle Rules: "+details.get(2)+"\nEnd Date: "+(details.get(3)).toString()+"\nOrganizer: "+details.get(7);
        else
            return "Raffle Name: "+details.get(0)+"\nRaffle Rules: "+details.get(2)+"\nEnd Date: "+(details.get(3)).toString()+"\nOrganizer: "+details.get(7)+"\n\nYou are a winner of this raffle!!!";
    }
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
    public String[] formatTaskDetails(String taskID)throws Exception
    {
        String[] taskDetails = new String[2];
        taskDetails[0] = "Task Name: "+o.getTaskInfo(raffleID,taskID).get(0);
        taskDetails[1] = "Description: "+o.getTaskInfo(raffleID,taskID).get(2)+"\nLink: "+o.getTaskInfo(raffleID,taskID).get(1);
        return taskDetails;
    }
    public boolean isWinner()
    {
        ArrayList<String> pIDs = (ArrayList<String>) o.getRaffleDetails(raffleID).get(5);
        return pIDs.contains(username+":"+raffleID);
    }
    public boolean isEnrolled()
    {
        ArrayList<String> pIDs = (ArrayList<String>) o.getRaffleDetails(raffleID).get(5);
        return pIDs.contains(username+":"+raffleID);
    }
    public boolean hasCompletedTask(String taskID)throws IOException
    {
        return p.hasCompletedTasks(taskID);
    }
}
