package main.java.Web;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;


import main.java.TaskWeb.TaskLookupController;
import main.java.UserWeb.UserController;
import main.java.Helpers.SendEmail;


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


    /**
     * Checkes if the entered login username and password mathch from the databse or not.
     *
     * @param  username  string representing username
     * @param  password string representing password
     * @return boolean returns if the login details match or not
     */

    public boolean checkLoginMatch(String username, String password){

        UserController userCont = new UserController();
        return userCont.organizerUsernameMatchPassword(username, password);
    }

    /**
     * Method to return the organizer raffleIDs from raffle Name
     *
     * @param  raffleName  string representing raffleName
     * @return ArrayList<String> returns ArrayList<String> of raffleIDs for the given name.
     */

    public ArrayList<String> getRaffleIDsFromName(String raffleName){

        RaffleDataHelper raffleData = new RaffleDataHelper();
        return raffleData.RaffleIDsFromName(raffleName);
    }

    /**
     * Method to return the organizer raffleIDs from organizer name.
     *
     * @param  orgName  string representing raffleName
     * @return ArrayList<String> returns ArrayList<String> of raffleIDs for the organimer name.
     */
    public ArrayList<String> getOrgIDsByOrgName(String orgName){

        UserController userCont = new UserController();
        return userCont.getOrganizerIdByOrgName(orgName);
    }

    /**
     * Method to create the raffle and store in the databse based on input parameters.
     *
     * @param  raffleName  string representing raffleName
     * @param rules string representing raffle rules
     * @param numWinners int representing number of winners
     * @param endDate Date object representing endDate of raffle
     * @param orgUsername string representing organizer username
     * @param allTaskInfo 2D array of string representing all task info.

     */
    public void raffleCreator(String raffleName, String rules , int numWinners, LocalDate endDate, String orgUsername,
                              String[][] allTaskInfo) throws Exception {


        ORCDirector makeRaff = new ORCDirector();
        makeRaff.ORCBuildCreator(raffleName, numWinners, endDate, orgUsername);
        OrgRaffleController orgRaffleObj = makeRaff.getORCProduct();
        orgRaffleObj.runRaffleController(OrgRaffleController.OrgRaffleAction.CREATE);
        setRaffleID(orgRaffleObj.getOrgRaffleId());

        makeRaff.ORCBuildRuleSetter(raffleID, rules);
        orgRaffleObj.runRaffleController(OrgRaffleController.OrgRaffleAction.SET_RULES);

        ArrayList<String> taskIDs= new ArrayList<>();
        int i;
        for (i = 0; i < allTaskInfo.length; i ++){

            TaskDirector taskDir = new TaskDirector();
            taskDir.taskBuildCreator(raffleID, allTaskInfo[i][0], allTaskInfo[i][1], allTaskInfo[i][2]);
            TaskController taskCont = taskDir.getTaskContr();

            taskCont.runTaskController(TaskController.taskAction.CREATE);
            String taskID = taskCont.getTaskID();
            //System.out.println(taskID);
            taskIDs.add(taskID);
        }
        makeRaff.ORCBuildTaskEditor(raffleID, taskIDs);
        orgRaffleObj.runRaffleController(OrgRaffleController.OrgRaffleAction.EDIT_TASKS);

    }

    /**
     * Method to return wheter username is valid or not.
     *
     * @param  username  string representing username

     * @return boolean representing if username is valid or not from the database.
     */
    public boolean isValidUsername(String username){

        UserController checkName = new UserController();
        return checkName.userNameUsed(username);


    }


    /**
     * Method to return organizer raffleIDs by username.
     *
     * @param  username  string representing username

     * @return String[] representing the reutrned organizer raffleIDs.
     */
    public String[] getOrgRaffleID(String username){


        UserController UserCont = new UserController();
        return UserCont.getOrganizerRaffleID(username);

    }


    /**
     * Method to return Arraylist<Object> of raffle details from raffleID.
     *
     * @param  raffleID  string representing raffleID

     * @return ArrayList<Object> representing the given raffleID details
     */
    public ArrayList<Object> getRaffleDetails(String raffleID){

        RaffleDataHelper raffData = new RaffleDataHelper();
        return raffData.getOrgRaffleInfo(raffleID);


    }

    /**
     * Method to return Arraylist<String> of taskinfo from raffleID and taskID.
     *
     * @param  raffleID  string representing raffleID
     * @param taskID string representing taskID

     * @return ArrayList<String> representing the task info given raffleID and taskID details
     */
    public ArrayList<String> getTaskInfo(String raffleID, String taskID) throws Exception {


        TaskController taskCont = new TaskController();
        taskCont.setRaffleID(raffleID);
        taskCont.setTaskID(taskID);
        taskCont.runTaskController(TaskController.taskAction.LOOKUP);
        return taskCont.getTaskInfo();


    }

    /**
     * Method to return Set<String> of all raffleiDs.

     * @return Set<String> representing all raffleIDs.
     */
    public Set<String> getAllRaffleID(){

        RaffleDataHelper raffData = new RaffleDataHelper();
        return raffData.getAllOrgRaffleInfo().keySet();

    }

    /**
     * Method to generate winners and store in the database.
     *
     */
    public void generateWinnersList(){


        ORCDirector raffleObj = new ORCDirector();
        raffleObj.ORCBuildWinnerGenerator(raffleID);
        OrgRaffleController orgRaffle = raffleObj.getORCProduct();


        orgRaffle.runRaffleController(OrgRaffleController.OrgRaffleAction.GENERATE_WINNERS);
        ArrayList<String> winners = orgRaffle.getWinnersGenerated();

        RaffleDataHelper raffData = new RaffleDataHelper();

        for (int i = 0; i < winners.size(); i++){
            String emailId = raffData.getEmailFromParticipantId(winners.get(i));
            String username = raffData.getUsernameFromUserId(winners.get(i), false);
            String raffleName = raffData.getOrgRaffleInfo(raffleID).get(0).toString();
            SendEmail emailObj = new SendEmail(username, emailId, raffleName);
            emailObj.send();

        }

    }

    /**
     * Method to get winners list for the given raffleiD.
     *
     * @param  raffleID  string representing raffleID

     * @return ArrayList<String> representing the given raffleID winners list.
     */
    public ArrayList<String> getWinnersList(String raffleID){

        RaffleDataHelper raffleData = new RaffleDataHelper();

        return raffleData.getWinnersList(raffleID);

    }

    /**
     * Method to store organizer details in the database,
     *
     * @param username  string representing username of organizer
     * @param password string representing password
     * @param orgName string representing organizer name
     * @param email string representing email of organizer
     * @param phone string representing organizer phone

     */
    public void storeOrgDetails(String username, String password, String orgName, String email, String phone){


        UserController userCont = new UserController();
        userCont.createNewOrganizer(username, password, orgName, phone, email);


    }


}



