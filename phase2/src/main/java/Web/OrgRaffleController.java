package main.java.Web;

import main.java.RaffleComponent.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrgRaffleController {

    private String orgRaffleId;
    private String raffleName;
    private int numOfWinners;
    private LocalDate endDate;
    private String orgUsername;
    private String rulesString;
    private ArrayList<String> taskIds;
    private LocalDate newEndDate;

    // returning attributes
    private ArrayList<String> winnersGenerated;


    public enum OrgRaffleAction {
        CREATE, SET_RULES, GENERATE_WINNERS, EDIT_TASKS, EDIT_ENDDATE
    }

    /**
     * Empty constructor setting everything to null or default values to allow for ORCDirector to take over
     */
    public OrgRaffleController(){
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

    /**
     * Executes the correct Organizer raffle object use case call with the provided information
     * @param actionToProcess indicates the use case to execute
     * @return bool describing whether use case call was successful (should be checked in OSM)
     */
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

    /**
     * executes the raffle creation use case of an organizer raffle
     * @return boolean describing the success of the outcome of the use case call with the given information
     */
    private boolean runRaffleCreation(){
        CreateRaffleUseCase raffleManager = new CreateRaffleUseCase(this.raffleName, this.numOfWinners,
                this.endDate, this.orgUsername);
        boolean result = raffleManager.runRaffleCreation();
        this.orgRaffleId = raffleManager.getGeneratedRaffleId();
        return result;
    }

    /**
     * executes the raffle rule setter use case of an organizer raffle
     * @return boolean describing the success of the outcome of the use case call with the given information
     */
    private boolean runRaffleRuleSetter(){
        RaffleRuleSetterUseCase raffleManager = new RaffleRuleSetterUseCase(this.orgRaffleId,
                this.rulesString); // no longer need raffleInfoSoFar
        return raffleManager.updateRules(); // updates the rules in the raffleManager raffle
    }

    /**
     * executes the raffle winner generator use case of an organizer raffle
     * @return boolean describing the success of the outcome of the use case call with the given information
     */
    private boolean runRaffleWinnerGenerator(){
        RaffleWinnerGeneratorUseCase raffleManager3 = new RaffleWinnerGeneratorUseCase(this.orgRaffleId);
        boolean result = raffleManager3.updateRaffleWinners();
        this.winnersGenerated = raffleManager3.getOrgRaffle().getWinnerList();
        return result;
    }

    /**
     * executes the raffle taskIdList attribute expansion use case (to add tasks) of an organizer raffle
     * @return boolean describing the success of the outcome of the use case call with the given information
     */
    private boolean runEditOrgTaskList(){
        OrgRaffleAddTaskUseCase raffleManager4 = new OrgRaffleAddTaskUseCase(this.orgRaffleId, this.taskIds);
        return raffleManager4.updateTaskList();
    }

    /**
     * executes the update of a raffle's endDate through RaffleEndDateModifierUseCase
     * @return boolean describing the success of the outcome of the use case call with the given information
     */
    private boolean runEditEndDate(){
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

    public ArrayList<String> getWinnersGenerated() {
        return winnersGenerated;
    }

}
