package main.java.Web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import main.java.RaffleWeb.CreateRaffleController;
import main.java.RaffleWeb.RaffleRuleSetterController;
import main.java.RaffleWeb.RaffleTaskController;
import main.java.RaffleWeb.CompleteTaskController;

public class participantSystemManager {
    private String username;
    private String raffleID;

    public participantSystemManager(){
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

    public void completeTask(String raffleID, string desc, String link, String taskName){
        // Call taskController, save task and add it to the participant raffle object. miGHT Have to call the necessary
        // controller to do this.
    }
}
