package main.java.Web;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import main.java.TaskComponent.Task;
import main.java.TaskWeb.ExecuteCommandController;

import main.java.UserWeb.UserController;
import main.java.Web.TaskController;
import main.java.Web.TaskDirector;

import javax.rmi.ssl.SslRMIClientSocketFactory;

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
    // todo: Have varun call org sys man method for getting raffleInfo and accessing the taskID index and pass this to this method.
    public void storeRaffleInParticipantData(String raffleID, String username) throws Exception {
//        String[] Ids =  raffleID.split(":");
        RaffleDataHelper dh = new RaffleDataHelper();
        ArrayList<String> orgRafflePtcs = (ArrayList<String>) dh.getOrgRaffleInfo(raffleID).get(5);

        if (!orgRafflePtcs.contains(username)){
            System.out.println("It never reaches this if block");
            UserController userCont = new UserController();
            userCont.addRaffleIDToParticipant(username, raffleID);
            String ptcUserID = userCont.getUserUserId(username, "P");
            //String[] ptcRaffleParts = raffleID.split(":");
            String ptcRaffleID = username + ":" + raffleID;

            PtcRaffleController ptcRaff = new PtcRaffleController(ptcUserID, raffleID, ptcRaffleID, null);
            ptcRaff.runRaffleController(PtcRaffleController.PtcRaffleAction.LOGIN);
        } else {
            throw new Exception("orgRafflePtcs already contains this ptc");
        }

        // old code:
//        LoginRaffleController loginRaffCont = new LoginRaffleController(orgRaffleID, username);
//        loginRaffCont.runLoginRaffle();
    }


    public void completeTask(String raffleID, String taskID) throws Exception {
        // Call taskController, save task and add it to the participant raffle object. miGHT Have to call the necessary
        // controller to do this.

        ExecuteCommandController executor = new ExecuteCommandController(raffleID, taskID);
        executor.runExecuteCommand();
        UserController userCont = new UserController();
        String ptcUserID = userCont.getUserUserId(username, "P");
        String ptcRaffleID = username + ":" + raffleID;

        PtcRaffleController ptcRaff = new PtcRaffleController(ptcUserID, raffleID, ptcRaffleID, taskID);
        ptcRaff.runRaffleController(PtcRaffleController.PtcRaffleAction.COMPLETE_TASK);

        //old code:
//        CompleteTaskController compTask = new CompleteTaskController(raffleID, taskID);
//        compTask.runCompleteTask();



    }

    public ArrayList<String> getTaskIDs(String raffleID){

        RaffleDataHelper raffleData = new RaffleDataHelper();
        String[] ptcRaffleParts = raffleID.split(":");
        String orgRaffleID = ptcRaffleParts[1];
        return (ArrayList<String>) raffleData.getOrgRaffleInfo(orgRaffleID).get(4);
    }

    public boolean checkLoginMatch(String username, String password){
        UserController userCont = new UserController();
        return userCont.participantUsernameMatchPassword(username, password);

        //OLD CODE
//        CheckUsernameController checkObj = new CheckUsernameController();
//        return checkObj.participantUsernameMatchPassword(username, password);
    }
    // todo: Let varun loop thorugh and make arraylist of emails and pass that array list to generateWinenrs func.
    public String generateEmail(String username){
        UserController userCont = new UserController();
        return userCont.getExistedParticipantInfo(username).get(4);
    }

    public boolean isValidUsername(String username){
        UserController userCont = new UserController();
        return userCont.userNameUsed(username);

        // Old code
//        CheckUsernameController checkName = new CheckUsernameController();
//        return checkName.userNameUsed(username);

    }

    public ArrayList<String> getParticipatingList(String raffleID){
        // Return list of all users that are participating in the given raffleID
        RaffleDataHelper raffleData = new RaffleDataHelper();
        String[] ptcRaffleParts = raffleID.split(":");
        String orgRaffleID = ptcRaffleParts[1];
        ArrayList<Object> raffleInfo = raffleData.getOrgRaffleInfo(orgRaffleID);
        return (ArrayList<String>) raffleInfo.get(5);
    }

    public ArrayList<String> getPartRaffleList(String username){
        // Return the given participant's raffle list they are participating in.
        UserController userCont = new UserController();
        return userCont.getParticipantRaffleID(username);
    }

    public Set<String> getAllRaffleID(){
        RaffleDataHelper raffleData = new RaffleDataHelper();
        // I get all raffle info as hashmap. Now I do keySets() to get set of keys.
        return raffleData.getAllOrgRaffleInfo().keySet();
    }

    public boolean hasCompletedTasks(String taskID)throws FileNotFoundException
    {
//        String[] ptcRaffleParts = raffleID.split(":");
//        String orgRaffleID = ptcRaffleParts[1];
//
//        PtcRaffleController ptcRaff = new PtcRaffleController(null, orgRaffleID, raffleID, taskID);
//
//        return ptcRaff.runRaffleController(PtcRaffleController.PtcRaffleAction.COMPLETE_TASK);
        RaffleDataHelper dh = new RaffleDataHelper();
        return dh.isTaskCompleted(raffleID , taskID);


        // old code:
//        CompleteTaskController c= new CompleteTaskController(this.raffleID,taskID);
//        return c.hasCompletedTask(this.username);
    }
}



