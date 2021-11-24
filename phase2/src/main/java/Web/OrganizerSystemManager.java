package main.java.Web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

//import main.java.RaffleComponent.OrgRaffleEditTaskUseCase;
import main.java.RaffleWeb.*;
import main.java.RaffleWeb.RaffleLookupController;
import main.java.TaskWeb.CreateTaskController;
import main.java.TaskWeb.TaskLookupController;
import main.java.UserWeb.UserController;
import main.java.UserWeb.UserRaffleIDController;
import main.java.UserWeb.CheckUsernameController;

public class OrganizerSystemManager {

    private String username;
    private String orgName;
    private String raffleID;


    public OrganizerSystemManager(){
        username = null;
        raffleID = null;
        orgName = null;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRaffleID() {
        return raffleID;
    }

    public void setRaffleID(String raffleID) {
        this.raffleID = raffleID;
    }

    public boolean checkLoginMatch(String username, String password){
        CheckUsernameController checkObj = new CheckUsernameController();
        return checkObj.organizerUsernameMatchPassword(username, password);
    }


    public void raffleCreator(String raffleName, String rules , int numWinners, LocalDate endDate, String orgUsername,
                              String[][] allTaskInfo) throws IOException {

        CreateRaffleController raffleCont = new CreateRaffleController(orgUsername, raffleName,
                numWinners, endDate);
        String newRaffleID = raffleCont.runCreateRaffle().get(4).toString();
        System.out.println(newRaffleID);// Creates the new raffle and returns the generated raffleID
        setRaffleID(newRaffleID);
        RaffleRuleSetterController rulesSetter = new RaffleRuleSetterController(raffleID, rules,
                raffleCont.raffleInfoSoFar);
        rulesSetter.runRaffleRuleSetter();

        //RaffleTaskController taskCont = new RaffleTaskController(raffleID, raffleCont.raffleInfoSoFar);

        ArrayList<String> taskIDs= new ArrayList<>();
        int i;
        for (i = 0; i < allTaskInfo.length; i ++){

            CreateTaskController createTsk = new CreateTaskController(newRaffleID, allTaskInfo[i][0], allTaskInfo[i][1], allTaskInfo[i][2]);
            String taskID = createTsk.runCreateTask();
            //System.out.println(taskID);
            taskIDs.add(taskID);
        }

        //taskCont.runEditOrgTaskList(OrgRaffleEditTaskUseCase.TaskEditTypes.ORGANIZER_ADD, taskIDs);
        // Adds or removes the task in list..
    }

    public boolean isValidUsername(String username){
        CheckUsernameController checkName = new CheckUsernameController();
        return checkName.userNameUsed(username);

    }


    public String[] getOrgRaffleID(String username){
        // Phase 1 we assume each organizer makes atmost 1 raffle.

        UserRaffleIDController userIDCont = new UserRaffleIDController();
        return userIDCont.getOrganizerRaffleID(username);
    }


    public ArrayList<Object> getRaffleDetails(String raffleID){
        RaffleLookupController raffleLookCont = new RaffleLookupController();
        return raffleLookCont.runLookupOrgRaffleInfo(raffleID);

    }

    public ArrayList<String> getTaskInfo(String taskID) throws FileNotFoundException {
        TaskLookupController task = new TaskLookupController(taskID);
        return task.runLookupTaskInfo();
    }

    // Pass the searched raffleID as get that raffle details.
    public Set<String> getAllRaffleID(){
        RaffleLookupController raffleLookupCont = new RaffleLookupController();
        // I get all raffle info as hashmap. Now I do keySets() to get set of keys.
        return raffleLookupCont.runLookupAllRaffleInfo().keySet();

    }

    public ArrayList<String> generateWinnersList(String raffleID){
        // This method returns the list of generated winners.
        RaffleWinnerGeneratorController raffleWinCont = new RaffleWinnerGeneratorController(raffleID);
        return raffleWinCont.runRaffleWinnerGenerator();
    }

    // From shih-hua user controller
    public void storeOrgDetails(String username, String password, String orgName, String email, String phone){

        UserController userCont = new UserController();
        userCont.createNewOrganizer(username, password, orgName, phone, email);


    }




}



