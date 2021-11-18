package main.java.RaffleWeb;

import main.java.RaffleComponent.CompleteTaskUseCase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CompleteTaskController {

    private final String raffleId; // provided by system (of the form "ptcId:raffleId")
    private final String taskId;
//    private HashMap<String, ArrayList<Object>> ptcAllRaffles; // provided by db

    /**
     * Constructor of the controller handling the CompleteTaskUseCase class
     * @param raffleId the raffleId of the raffle whose task is being completed
     * @param taskId the id of the task being completed
     */
    public CompleteTaskController(String raffleId, String taskId){
        this.raffleId = raffleId;
        this.taskId = taskId;
    }

    /**
     * Manages the flow of control of the CompleteTaskUseCase
     */
    public void runCompleteTask() throws FileNotFoundException {
        CompleteTaskUseCase raffleManager = new CompleteTaskUseCase(this.raffleId, this.taskId);
        try {

            raffleManager.completeTask();

        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        // update db with raffleManager.getRaffle details
    }

    /**
     * Checks whether a participant has completed the task given by this.taskId
     * @param username the username of the participant
     * @return boolean expressing whether user has completed task
     * @throws FileNotFoundException
     */
    public boolean hasCompletedTask(String username) throws FileNotFoundException
    {
        CompleteTaskUseCase c = new CompleteTaskUseCase(this.raffleId,this.taskId);
        return c.hasCompletedTask(username,this.raffleId);
    }
 }
