package main.java.Web;

import main.java.RaffleComponent.CompleteTaskUseCase;
import main.java.RaffleComponent.LoginRaffleUseCase;
import java.util.ArrayList;

public class PtcRaffleController {

    private final String participantUserId;
    private final String orgRaffleId;
    private String ptcRaffleId;
    private final String taskId;
    private ArrayList<String> completedTaskIds;

    public enum PtcRaffleAction{
        LOGIN, COMPLETE_TASK
    }

    /**
     * Constructor of the participant raffle controller,
     * @param ptcUserId the id of a participant logging into a raffle
     * @param orgRaffleId the id of the organizer raffle being joined / for which a task is being completed
     * @param ptcRaffleId the id of the ptc raffle undergoing task completion
     * @param taskId the id of the task to be completed
     */
    public PtcRaffleController(String ptcUserId, String orgRaffleId, String ptcRaffleId, String taskId){
        this.participantUserId = ptcUserId;
        this.orgRaffleId = orgRaffleId;
        this.ptcRaffleId = ptcRaffleId;
        this.taskId = taskId;
        this.completedTaskIds = null;

    }

    /**
     * Executes the correct Participant raffle object use case call with the provided information
     * @param actionToProcess indicates the use case to execute
     * @return bool describing whether use case call was successful (should be checked in PSM)
     */
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

    /**
     *  executes the raffle login use case of a participant raffle
     * @return boolean describing the success of the outcome of the use case call with the given information
     */
    private boolean runLogin(){
        // if raffleId is valid, then it is passed onto the use case, otherwise, use case takes care of null input
        LoginRaffleUseCase loginRaffleManager = new LoginRaffleUseCase(this.orgRaffleId, this.participantUserId);
        boolean result = loginRaffleManager.runRaffleLogin();
        this.ptcRaffleId = loginRaffleManager.getPtcRaffle().getRaffleId();
        return result;
    }

    /**
     *  executes the raffle complete task use case of a participant raffle
     * @return boolean describing the success of the outcome of the use case call with the given information
     */
    private boolean runCompleteTask(){

        CompleteTaskUseCase raffleManager = new CompleteTaskUseCase(this.ptcRaffleId, this.taskId);
        boolean result = raffleManager.completeTask();
        this.completedTaskIds = raffleManager.getPtcCompletedTasks();
        return result;
    }

}
