package main.java.RaffleWeb;

import main.java.RaffleComponent.CompleteTaskUseCase;

import java.util.ArrayList;
import java.util.HashMap;

public class CompleteTaskController {

    private String raffleId; // provided by system (of the form "userId:raffleId")
    private String taskId;
//    private HashMap<String, ArrayList<Object>> ptcAllRaffles; // provided by db


    public CompleteTaskController(String raffleId, String taskId){
        this.raffleId = raffleId;
        this.taskId = taskId;
    }

    public void runCompleteTask(){
        CompleteTaskUseCase raffleManager = new CompleteTaskUseCase(this.taskId, this.raffleId);
        raffleManager.completeTask();
        // update db with raffleManager.getRaffle details
    }
}
