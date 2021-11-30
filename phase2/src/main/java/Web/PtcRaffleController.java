package main.java.Web;

import main.java.RaffleComponent.CompleteTaskUseCase;
import main.java.RaffleComponent.LoginRaffleUseCase;
import main.java.RaffleComponent.ParticipantRaffleEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class PtcRaffleController {


    // todo: ask about this dependency: private final OrganizerRaffleEntity;
    // private ArrayList<Object> raffleInfoSoFar;

    private final String participantUserId;
    private final String orgRaffleId;
    private String ptcRaffleId;
    private final String taskId;

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
    public void runRaffleController(PtcRaffleAction actionToProcess){

        switch (actionToProcess){
            case LOGIN:
                if (this.orgRaffleId != null || this.ptcRaffleId == null) {
                    // a raffle must be joined before giving this.ptcRaffleId a non-null value
                    this.ptcRaffleId = this.runLogin();
                }
                // else case is handled by the system, technically there should never be such error
                break;

            case COMPLETE_TASK:
                if (taskId != null || this.ptcRaffleId != null){
                    try {
                        this.runCompleteTask();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                // else case is handled by the system, technically there should never be such error
                break;
        }
    }

    public String runLogin(){
        // if raffleId is valid, then it is passed onto the use case, otherwise, use case takes care of null input
        LoginRaffleUseCase loginRaffleManager = new LoginRaffleUseCase(this.orgRaffleId, this.participantUserId);
        return loginRaffleManager.runRaffleLogin();
    }

    // ptcRaffles can only complete a task to pop it, but cannot add one on their own without it happening
    // through the subscription system to an orgRaffle
    public void runCompleteTask() throws FileNotFoundException {

        CompleteTaskUseCase raffleManager = new CompleteTaskUseCase(this.ptcRaffleId, this.taskId);
        try {
            raffleManager.completeTask();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

}
