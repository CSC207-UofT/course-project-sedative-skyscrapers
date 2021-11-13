package main.java.Web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import main.java.RaffleWeb.CreateRaffleController;
import main.java.RaffleWeb.RaffleRuleSetterController;
import main.java.RaffleWeb.RaffleTaskController;
import main.java.RaffleWeb.CompleteTaskController;

public class organizerSystemManager {

    private String username;
    private String raffleID;

    public organizerSystemManager(){
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

    public void raffleCreator(String raffleName, int numWinners, LocalDate endDate){
        // Get parameters from GUI
        // Change parameter to not pass empty ArrayList after michele handles from DB
        //todo: Fix parameters ordering.
        CreateRaffleController raffleCont = new CreateRaffleController(new ArrayList<String>(), raffleName,
                numWinners, endDate);
        String newRaffleID = raffleCont.runCreateRaffle(); // Creates the new raffle and returns the generated raffleID
        setRaffleID(newraffleID);
    }

    public void setRaffleRules(String rules){
        // Change parameter to not pass empty hashmap after michele handles from DB
        RaffleRuleSetterController rulesSetter = new RaffleRuleSetterController(raffleID, rules,
                new HashMap<String, ArrayList<Object>>());
        rulesSetter.runRaffleRuleSetter();

    }
    // Method to add tasks to the current raffle.
    public void taskCompleter(RaffleTaskController.TaskEditTypes buttonChoice, String taskID){
        // buttonChoice corresponds to the enum that is assigned to the add/remove task button and
        // accordingly completing the task. HAVE VARUN HAVE SAME ENUMS AS RaffleTaskController Class.
        // Change parameter to not pass empty hashmap after michele handles from DB
        RaffleTaskController taskCont = new RaffleTaskController(raffleID, new HashMap<String, ArrayList<Object>>(),
                new HashMap<String, ArrayList<Object>>());

        taskCont.runEditOrgTaskList(buttonChoice, taskID); // Adds or removes the task in list.
        // We still need to save the answers and questions in some place for each taskID.
        // todo: Find a way to connect created raffleID with the tasks(s) like hashmap maybe.
        // todo: Check with garvteam to save this or with michele if he is doing it somewhere. Also add this to database.

        //todo: For participant, you should have button method to check if ans is correct and then call completeTaskController
        // todo: so we know that task can be removed from list.
        //todo: Write method for selecting winners from raffle
        //todo: Is all the diff user info with what raffles they part of being saved in database or is it all waste??
    }
    // From shih-hua
    public ArrayList<Object> getOrgRaffleList(String username){
        // create object that will have method to call.
        // return the object list of raffleIDS made by "username" org.
        return ...;
    }
    // From shih-hua
    public ArrayList<Object> getPartRaffleList(String username){
        // create object that will have method to call.
        // return the object list of raffleIDS made by "username" user.
        return ...;
    }

    public ArrayList<String> getRaffleDetails(String raffleID){
        // Make object
        return obj.raffleDetails(raffleID);

    }
    // todo: Make method for getting winners from winners controller and return Varun the participantIDs.
}

