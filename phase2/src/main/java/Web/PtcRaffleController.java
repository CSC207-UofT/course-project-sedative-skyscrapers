package main.java.Web;

import main.java.RaffleComponent.CompleteTaskUseCase;
import main.java.RaffleComponent.LoginRaffleUseCase;
import main.java.RaffleComponent.ParticipantRaffleEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class PtcRaffleController {

    private final String participantUserId;
    private final String orgRaffleId;
    private String ptcRaffleId;
    private final String taskId;

    private ArrayList<String> ptcCompletedTasks;

    public enum PtcRaffleAction{
        LOGIN, COMPLETE_TASK
    }

    public PtcRaffleController(String ptcUserId, String orgRaffleId, String ptcRaffleId, String taskId){
        this.participantUserId = ptcUserId;
        this.orgRaffleId = orgRaffleId;
        this.ptcRaffleId = ptcRaffleId;
        this.taskId = taskId;

    }

    // each of orgRaffleId or taskId can be introduced as null if actionToProcess is not related to them
    public boolean runRaffleController(PtcRaffleAction actionToProcess){

        switch (actionToProcess){
            case LOGIN:
                if (this.orgRaffleId != null || this.ptcRaffleId == null) {
                    // a raffle must be joined before giving this.ptcRaffleId a non-null value
                    return this.runLogin();
                }
                // else case is handled by the system, technically there should never be such error
                break;

            case COMPLETE_TASK:
                if (taskId != null || this.ptcRaffleId != null){
                    return this.runCompleteTask();
                }
                // else case is handled by the system, technically there should never be such error
        }

        // if neither of the two cases are to be executed return false by default
        return false;
    }

    public boolean runLogin(){
        // if raffleId is valid, then it is passed onto the use case, otherwise, use case takes care of null input
        LoginRaffleUseCase loginRaffleManager = new LoginRaffleUseCase(this.orgRaffleId, this.participantUserId);

        boolean result = loginRaffleManager.runRaffleLogin();
        this.ptcRaffleId = loginRaffleManager.getPtcRaffle().getRaffleId();
        return result;
    }

    // ptcRaffles can only complete a task to pop it, but cannot add one on their own without it happening
    // through the subscription system to an orgRaffle
    public boolean runCompleteTask(){

        CompleteTaskUseCase raffleManager = new CompleteTaskUseCase(this.ptcRaffleId, this.taskId);
        boolean result = raffleManager.completeTask();
        this.ptcCompletedTasks = raffleManager.getPtcCompletedTasks();
        return result;
    }

}
