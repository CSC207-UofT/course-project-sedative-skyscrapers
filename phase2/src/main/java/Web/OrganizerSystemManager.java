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


    public boolean checkLoginMatch(String username, String password){
        UserController userCont = new UserController();
        return userCont.organizerUsernameMatchPassword(username, password);
    }
    public ArrayList<String> getRaffleIDsFromName(String raffleName){
        RaffleDataHelper raffleData = new RaffleDataHelper();
        return raffleData.RaffleIDsFromName(raffleName);
    }

    public ArrayList<String> getOrgIDsByOrgName(String orgName){
        UserController userCont = new UserController();
        return userCont.getOrganizerIdByOrgName(orgName);
    }

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

//        CreateRaffleController raffleCont = new CreateRaffleController(orgUsername, raffleName,
//                numWinners, endDate);
//        String newRaffleID = raffleCont.runCreateRaffle().get(4).toString();
//        System.out.println(newRaffleID);// Creates the new raffle and returns the generated raffleID
//        setRaffleID(newRaffleID);
//        RaffleRuleSetterController rulesSetter = new RaffleRuleSetterController(raffleID, rules,
//                raffleCont.raffleInfoSoFar);
//        rulesSetter.runRaffleRuleSetter();
//
//        //RaffleTaskController taskCont = new RaffleTaskController(raffleID, raffleCont.raffleInfoSoFar);
//
//        ArrayList<String> taskIDs= new ArrayList<>();
//        int i;
//        for (i = 0; i < allTaskInfo.length; i ++){
//
//            CreateTaskController createTsk = new CreateTaskController(newRaffleID, allTaskInfo[i][0], allTaskInfo[i][1], allTaskInfo[i][2]);
//            String taskID = createTsk.runCreateTask();
//            //System.out.println(taskID);
//            taskIDs.add(taskID);
//        }
//
//        //taskCont.runEditOrgTaskList(OrgRaffleEditTaskUseCase.TaskEditTypes.ORGANIZER_ADD, taskIDs);
//        // Adds or removes the task in list..
    }

    public boolean isValidUsername(String username){
        UserController checkName = new UserController();
        return checkName.userNameUsed(username);

        // OLD CODE BELOW
//        CheckUsernameController checkName = new CheckUsernameController();
//        return checkName.userNameUsed(username);

    }


    public String[] getOrgRaffleID(String username){

        UserController UserCont = new UserController();
        return UserCont.getOrganizerRaffleID(username);
        // OLD CODE
//        UserRaffleIDController userIDCont = new UserRaffleIDController();
//        return userIDCont.getOrganizerRaffleID(username);
    }


    public ArrayList<Object> getRaffleDetails(String raffleID){
        RaffleDataHelper raffData = new RaffleDataHelper();
        return raffData.getOrgRaffleInfo(raffleID);

//        RaffleLookupController raffleLookCont = new RaffleLookupController();
//        return raffleLookCont.runLookupOrgRaffleInfo(raffleID);

    }

    public ArrayList<String> getTaskInfo(String raffleID, String taskID) throws Exception {
        TaskController taskCont = new TaskController();
        taskCont.setRaffleID(raffleID);
        taskCont.setTaskID(taskID);
        taskCont.runTaskController(TaskController.taskAction.LOOKUP);
        return taskCont.getTaskInfo();

        // old code:
//        TaskLookupController task = new TaskLookupController(taskID);
//        return task.runLookupTaskInfo();
    }

    // Pass the searched raffleID as get that raffle details.
    public Set<String> getAllRaffleID(){
        RaffleDataHelper raffData = new RaffleDataHelper();
        return raffData.getAllOrgRaffleInfo().keySet();
//        RaffleLookupController raffleLookupCont = new RaffleLookupController();
//        // I get all raffle info as hashmap. Now I do keySets() to get set of keys.
//        return raffleLookupCont.runLookupAllRaffleInfo().keySet();

    }

    public void generateWinnersList(ArrayList<String> emailIDs, String raffleID){
        // This method returns the list of generated winners and contacts them via email.
        ORCDirector raffleObj = new ORCDirector();
        raffleObj.ORCBuildWinnerGenerator(raffleID);
        OrgRaffleController orgRaffle = raffleObj.getORCProduct();


        orgRaffle.runRaffleController(OrgRaffleController.OrgRaffleAction.GENERATE_WINNERS);
        ArrayList<String> winners = orgRaffle.getWinnersGenerated();
        for(int i = 0; i < winners.size(); i++){
            SendEmail emailObj = new SendEmail(winners.get(i), emailIDs.get(i), raffleID);
            emailObj.send();

        }
        // OLD CODE:
//        RaffleWinnerGeneratorController raffleWinCont = new RaffleWinnerGeneratorController(raffleID);
//        return raffleWinCont.runRaffleWinnerGenerator();
    }

    public ArrayList<String> getWinnersList(String raffleID){
        RaffleDataHelper raffleData = new RaffleDataHelper();

        return raffleData.getWinnersList(raffleID);

    }

    public void storeOrgDetails(String username, String password, String orgName, String email, String phone){

        UserController userCont = new UserController();
        userCont.createNewOrganizer(username, password, orgName, phone, email);


    }




}



