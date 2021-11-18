package main.java.RaffleWeb;

import main.java.RaffleComponent.CompleteTaskUseCase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CompleteTaskController {

    private String raffleId; // provided by system (of the form "ptcId:raffleId")
    private String taskId;
//    private HashMap<String, ArrayList<Object>> ptcAllRaffles; // provided by db


    public CompleteTaskController(String raffleId, String taskId){
        this.raffleId = raffleId;
        this.taskId = taskId;
    }

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
    public boolean hasCompletedTask(String username) throws FileNotFoundException
    {
        CompleteTaskUseCase c = new CompleteTaskUseCase(this.raffleId,this.taskId);
        return c.hasCompletedTask(username,this.raffleId);
    }
 }
