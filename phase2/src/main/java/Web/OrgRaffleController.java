package main.java.Web;

import main.java.RaffleComponent.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrgRaffleController {

    // all possible attributes and inputs:
    /*
    String orgRaffleId provided by system
    ArrayList<Object> raffleInfoSoFar provided by system
    String rulesString provided by user
    String ptcLoggingInId (username) provided by system
    String raffleName
    int numOfWinners
    LocalDate endDate
    String orgUsername
    String taskId
    */
//    private final String organizerUserId;
//    // todo: ask about this dependency: private final OrganizerRaffleEntity;
//    private ArrayList<Object> raffleInfoSoFar;


    // common between different possible controllers
    private String orgRaffleId;

    // creator
    private String raffleName;
    private int numOfWinners;
    private LocalDate endDate;
    private String orgUsername;

    // rule setter
    // private String orgRaffleId;
    private String rulesString;
    // here would go raffleInfoSoFar, but we'll now get that from the database

    // winner generator
    // just need the orgRaffleId

    // task editor
    // private final String orgRaffleId;
    private ArrayList<String> taskIds;  // specific ids to be removed/added
    private LocalDate newEndDate;

    public enum OrgRaffleAction {
        CREATE, SET_RULES, GENERATE_WINNERS, EDIT_TASKS, EDIT_ENDDATE
    }

    public OrgRaffleController(){
        // setup everything as default values for the ORC director to take over
        this.orgRaffleId = null;
        this.raffleName = null;
        this.numOfWinners = 0;
        this.endDate = null;
        this.orgUsername = null;
        this.rulesString = null;
        this.taskIds = null;
        this.newEndDate = null;
    }

    public void runRaffleController(OrgRaffleAction actionToProcess){

        switch (actionToProcess) {
            case CREATE -> this.runRaffleCreation();
            case SET_RULES -> this.runRaffleRuleSetter();
            case GENERATE_WINNERS -> this.runRaffleWinnerGenerator();
            case EDIT_TASKS -> this.runEditOrgTaskList();
            case EDIT_ENDDATE -> this.runEditEndDate();
        }

    }

    public void runRaffleCreation(){
        CreateRaffleUseCase raffleManager = new CreateRaffleUseCase(this.raffleName, this.numOfWinners,
                this.endDate, this.orgUsername);
        raffleManager.runRaffleCreation();
        this.orgRaffleId = raffleManager.getGeneratedRaffleId();
    }

    public void runRaffleRuleSetter(){
        RaffleRuleSetterUseCase raffleManager = new RaffleRuleSetterUseCase(this.orgRaffleId,
                this.rulesString); // no longer need raffleInfoSoFar
        raffleManager.updateRules(); // updates the rules in the raffleManager raffle
    }

    public void runRaffleWinnerGenerator(){
        RaffleWinnerGeneratorUseCase raffleManager3 = new RaffleWinnerGeneratorUseCase(this.orgRaffleId);
        raffleManager3.updateRaffleWinners();
    }

    public void runEditOrgTaskList(){
        OrgRaffleAddTaskUseCase raffleManager4 = new OrgRaffleAddTaskUseCase(this.orgRaffleId, this.taskIds);
        raffleManager4.updateTaskList();
    }

    public void runEditEndDate(){
        RaffleEndDateModifierUseCase raffleManager5 = new RaffleEndDateModifierUseCase(this.orgRaffleId,
                this.newEndDate);
        raffleManager5.updateEndDate();
    }

    // setters for builder pattern

    public void setRaffleName(String raffleName) {
        this.raffleName = raffleName;
    }

    public void setNumOfWinners(int numOfWinners) {
        this.numOfWinners = numOfWinners;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setOrgUsername(String orgUsername) {
        this.orgUsername = orgUsername;
    }

    public void setOrgRaffleId(String orgRaffleId) {
        this.orgRaffleId = orgRaffleId;
    }

    public void setRulesString(String rulesString) {
        this.rulesString = rulesString;
    }

    public void setTaskIdList(ArrayList<String> taskIds) {
        this.taskIds = taskIds;
    }

    public void setNewEndDate(LocalDate newEndDate) {this.newEndDate = newEndDate;}

}
