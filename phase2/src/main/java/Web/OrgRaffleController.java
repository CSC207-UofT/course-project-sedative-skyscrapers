package main.java.Web;

import main.java.RaffleComponent.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrgRaffleController {

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

    // returning attributes
    private boolean creationResult;
    private ArrayList<String> winnersGenerated;

    public ArrayList<String> getWinnersGenerated() {
        return winnersGenerated;
    }

    private LocalDate updatedEndDate;

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

    public String getOrgRaffleId() {
        return orgRaffleId;
    }

    public boolean runRaffleController(OrgRaffleAction actionToProcess){

        switch (actionToProcess) {
            case CREATE:
                return this.runRaffleCreation();
            case SET_RULES:
                return this.runRaffleRuleSetter();
            case GENERATE_WINNERS:
                return this.runRaffleWinnerGenerator();
            case EDIT_TASKS:
                return this.runEditOrgTaskList();
            case EDIT_ENDDATE:
                return this.runEditEndDate();
            default:
                return false;
        }

    }

    public boolean runRaffleCreation(){
        CreateRaffleUseCase raffleManager = new CreateRaffleUseCase(this.raffleName, this.numOfWinners,
                this.endDate, this.orgUsername);
        boolean result = raffleManager.runRaffleCreation();
        this.orgRaffleId = raffleManager.getGeneratedRaffleId();
        return result;
    }

    public boolean runRaffleRuleSetter(){
        RaffleRuleSetterUseCase raffleManager = new RaffleRuleSetterUseCase(this.orgRaffleId,
                this.rulesString); // no longer need raffleInfoSoFar
        return raffleManager.updateRules(); // updates the rules in the raffleManager raffle
    }

    public boolean runRaffleWinnerGenerator(){
        RaffleWinnerGeneratorUseCase raffleManager3 = new RaffleWinnerGeneratorUseCase(this.orgRaffleId);
        boolean result = raffleManager3.updateRaffleWinners();
        this.winnersGenerated = raffleManager3.getOrgRaffle().getWinnerList();
        return result;
    }

    public boolean runEditOrgTaskList(){
        OrgRaffleAddTaskUseCase raffleManager4 = new OrgRaffleAddTaskUseCase(this.orgRaffleId, this.taskIds);
        return raffleManager4.updateTaskList();
    }

    public boolean runEditEndDate(){
        RaffleEndDateModifierUseCase raffleManager5 = new RaffleEndDateModifierUseCase(this.orgRaffleId,
                this.newEndDate);
        boolean result = raffleManager5.updateEndDate();
        this.newEndDate = raffleManager5.getOrgRaffle().getEndDate();
        return result;
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
