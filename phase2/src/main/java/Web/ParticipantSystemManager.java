package main.java.Web;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import main.java.TaskWeb.ExecuteCommandController;

import main.java.UserWeb.UserController;

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

    /**
     * Method to store participant details in the database.
     *
     * @param username  string representing username
     * @param password string representing password
     * @param firstName string representing first name
     * @param lastName string representing last name
     * @param email string representing email
     * @param phone string representing participant phone
     * @param DOB local date depresenting date of birth

     */
    public void storePartDetails(String username, String password, String firstName, String lastName, String email,
                                 String phone, LocalDate DOB){


        UserController userCont = new UserController();
        userCont.createNewParticipant(username, password, firstName, lastName, DOB, phone, email);
    }

    /**
     * Method to store raffle info in the participant's data.
     *
     * @param raffleID  string representing raffleID
     * @param username string representing participant username

     */
    public void storeRaffleInParticipantData(String raffleID, String username) throws Exception {


        RaffleDataHelper dh = new RaffleDataHelper();
        ArrayList<String> orgRafflePtcs = (ArrayList<String>) dh.getOrgRaffleInfo(raffleID).get(5);
        if (!orgRafflePtcs.contains(dh.getPtcUserIdFromUsername(username))){
            UserController userCont = new UserController();
            userCont.addRaffleIDToParticipant(username, raffleID);
            String ptcUserID = userCont.getUserUserId(username, "P");
            String ptcRaffleID = username + ":" + raffleID;
            System.out.println("ptcSM line 54, should be ptcId: " + ptcRaffleID);

            PtcRaffleController ptcRaff = new PtcRaffleController(ptcUserID, raffleID, ptcRaffleID, null);
            ptcRaff.runRaffleController(PtcRaffleController.PtcRaffleAction.LOGIN);
        } else {
            throw new Exception("orgRafflePtcs already contains this ptc");
        }

    }


    /**
     * Method to complete the given taskID for the given raffleID for the current participant..
     *
     * @param raffleID  string representing raffleID
     * @param taskID string representing participant taskID

     */
    public void completeTask(String raffleID, String taskID) throws Exception {


        ExecuteCommandController executor = new ExecuteCommandController(raffleID, taskID);
        executor.runExecuteCommand();
        UserController userCont = new UserController();
        String ptcUserID = userCont.getUserUserId(username, "P");
        String ptcRaffleID = username + ":" + raffleID;

        PtcRaffleController ptcRaff = new PtcRaffleController(ptcUserID, raffleID, ptcRaffleID, taskID);
        ptcRaff.runRaffleController(PtcRaffleController.PtcRaffleAction.COMPLETE_TASK);

    }

    /**
     * Method to get taskIDs for the given raffleID
     *
     * @param  raffleID  string representing raffleID

     * @return ArrayList<String> representing the taskIDs.
     */
    public ArrayList<String> getTaskIDs(String raffleID){


        RaffleDataHelper raffleData = new RaffleDataHelper();
        String[] ptcRaffleParts = raffleID.split(":");
        String orgRaffleID = ptcRaffleParts[1];
        return (ArrayList<String>) raffleData.getOrgRaffleInfo(orgRaffleID).get(4);
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
        return userCont.participantUsernameMatchPassword(username, password);

    }

    /**
     * Method to return the email of the participant.
     *
     * @param  username  string representing username

     * @return String representing email of the participant
     */
    public String generateEmail(String username){

        UserController userCont = new UserController();
        return userCont.getExistedParticipantInfo(username).get(4);
    }

    /**
     * Method to return wheter username is valid or not.
     *
     * @param  username  string representing username

     * @return boolean representing if username is valid or not from the database.
     */
    public boolean isValidUsername(String username){

        UserController userCont = new UserController();
        return userCont.userNameUsed(username);


    }

    /**
     * Method to return all participants of that raffle
     *
     * @param  raffleID string representing raffleID

     * @return ArrayList<String> representing all participants of that raffle.
     */
    public ArrayList<String> getParticipatingList(String raffleID){


        RaffleDataHelper raffleData = new RaffleDataHelper();
        String[] ptcRaffleParts = raffleID.split(":");
        String orgRaffleID = ptcRaffleParts[1];
        ArrayList<Object> raffleInfo = raffleData.getOrgRaffleInfo(orgRaffleID);
        return (ArrayList<String>) raffleInfo.get(5);
    }

    /**
     * Method to return all raffles the current participant is a part of
     *
     * @param  username string username

     * @return ArrayList<String> representing all raffles the current participant is a part of.
     */
    public ArrayList<String> getPartRaffleList(String username){


        UserController userCont = new UserController();
        return userCont.getParticipantRaffleID(username);
    }

    /**
     * Method to return all raffleIDs

     * @return Set<String> return all raffleIDs.
     */
    public Set<String> getAllRaffleID(){

        RaffleDataHelper raffleData = new RaffleDataHelper();

        return raffleData.getAllOrgRaffleInfo().keySet();
    }

    /**
     * Method to return wheter given task has been completed or not.
     *
     * @param taskID  string representing taskID

     * @return boolean representing if task has been completed or not.
     */
    public boolean hasCompletedTasks(String taskID)throws FileNotFoundException
    {


        RaffleDataHelper dh = new RaffleDataHelper();
        return dh.isTaskCompleted(raffleID , taskID);

    }
}



