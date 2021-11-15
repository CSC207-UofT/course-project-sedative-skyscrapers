package main.java.Web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import main.java.RaffleWeb.*;
import main.java.TaskWeb.ExecuteCommandController;
import main.java.UserWeb.CheckUsernameController;
import main.java.UserWeb.UserController;
import main.java.UserWeb.UserRaffleIDController;

public class ParticipantSystemManager {
    private String username;
    private String raffleID;

    public ParticipantSystemManager(){
        username = null;
        raffleID = null;
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
    public void storePartDetails(String username, String password, String firstName, String lastName, String email,
                                 String phone, LocalDate DOB){

        UserController userCont = new UserController();
        userCont.createNewParticipant(username, password, firstName, lastName, DOB, phone, email);
    }



    public void storeRaffleInParticipantData(String raffleID, String username){

        UserRaffleIDController userRaffIDCont = new UserRaffleIDController();
        userRaffIDCont.addRaffleIDToParticipant(username, raffleID);
        String[] ptcRaffleParts = raffleID.split(":");
        String orgRaffleID = ptcRaffleParts[1];
        LoginRaffleController loginRaffCont = new LoginRaffleController(orgRaffleID, username);
        loginRaffCont.runLoginRaffle();
    }

    // TODO (Code works just clarification): Ask varun about how 1 by 1 task complete and you call completeTask is called,
    //  how that will handle for that task in controller and store..
    public void completeTask(String raffleID, String taskID) throws Exception {
        // Call taskController, save task and add it to the participant raffle object. miGHT Have to call the necessary
        // controller to do this.
        CompleteTaskController compTask = new CompleteTaskController(taskID, raffleID);
        compTask.runCompleteTask();
        ExecuteCommandController executor = new ExecuteCommandController(raffleID, taskID);
        executor.runExecuteCommand();


    }

    public ArrayList<String> getTaskIDs(String raffleID){

        RaffleLookupController raffleLookupCont = new RaffleLookupController();
        String[] ptcRaffleParts = raffleID.split(":");
        String orgRaffleID = ptcRaffleParts[1];
        return (ArrayList<String>) raffleLookupCont.runLookupOrgRaffleInfo(orgRaffleID).get(4);
    }

    public boolean checkLoginMatch(String username, String password){
        CheckUsernameController checkObj = new CheckUsernameController();
        return checkObj.participantUsernameMatchPassword(username, password);
    }

    public boolean isValidUsername(String username){
        CheckUsernameController checkName = new CheckUsernameController();
        return checkName.userNameUsed(username);

    }

    public ArrayList<String> getParticipatingList(String raffleID){
        // Return list of all users that are participating in the given raffleID
        RaffleLookupController raffleLookupCont = new RaffleLookupController();
        String[] ptcRaffleParts = raffleID.split(":");
        String orgRaffleID = ptcRaffleParts[1];
        ArrayList<Object> raffleInfo = raffleLookupCont.runLookupOrgRaffleInfo(orgRaffleID);
        return (ArrayList<String>) raffleInfo.get(5);
    }

    public ArrayList<String> getPartRaffleList(String username){
        // Return the given participant's raffle list they are participating in.
        UserRaffleIDController userRaffIDCont = new UserRaffleIDController();
        return userRaffIDCont.getParticipantRaffleID(username);
    }

    public Set<String> getAllRaffleID(){
        RaffleLookupController raffleLookupCont = new RaffleLookupController();
        // I get all raffle info as hashmap. Now I do keySets() to get set of keys.
        return raffleLookupCont.runLookupAllRaffleInfo().keySet();
    }
}



