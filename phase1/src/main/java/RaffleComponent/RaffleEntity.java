package main.java.RaffleComponent;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class representing a Raffle object, which can be joined or created by Participants and Organizers respectively.
 */
public class RaffleEntity {

    private String raffleName;
    private String raffleId;
    private int numberOfWinners;
    private String raffleRules;
    private LocalDate endDate;
    private ArrayList<String> taskIdList;

    // constructor for participant (this entity is going to be used as the participant raffle, ORaffle is an extension)

    // constructor for both organizer and participant (they differ in the setup of raffleId)
    public RaffleEntity(String raffleName, int numberOfWinners, LocalDate endDate){
        this.raffleName = raffleName;
        this.numberOfWinners = numberOfWinners;
        this.endDate = endDate;
        this.taskIdList = new ArrayList<String>();

//        this.raffleID = tempId;  TO BE SET THROUGH USE CASE
    }

    public boolean checkIdsMatch(String id){
        return this.raffleId.equals(id);
    }


    public String getRaffleId() {
        return raffleId;
    }

    public void setRaffleId(String raffleId) {
        this.raffleId = raffleId;
    }


    public String getRaffleName() {
        return raffleName;
    }

    public int getNumberOfWinners() {
        return numberOfWinners;
    }

    public LocalDate getEndDate() {
        return endDate;
    }


    public String getRaffleRules() {
        return raffleRules;
    }

    public void setRaffleRules(String raffleRules) {
        this.raffleRules = raffleRules;
    }


    public ArrayList<String> getTaskIdList() {
        return taskIdList;
    }

    public void setTaskIdList(ArrayList<String> taskIdList) {
        this.taskIdList = taskIdList;
    }
}
