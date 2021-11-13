package main.java.RaffleComponent;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class representing a Raffle object, which can be joined or created by Participants and Organizers respectively.
 */
public class RaffleEntity {

    private final String raffleName;
    private String raffleId;
    private final int numberOfWinners;
    private String raffleRules;
    private final LocalDate endDate;
    private ArrayList<String> taskIdList;

    // constructor for participant (this entity is going to be used as the participant raffle, ORaffle is an extension)

    // constructor for both organizer and participant (they differ in the setup of raffleId)
    public RaffleEntity(String raffleName, int numberOfWinners, LocalDate endDate){
        this.raffleName = raffleName;
        this.numberOfWinners = numberOfWinners;
        this.endDate = endDate;
        this.taskIdList = new ArrayList<>();

//        this.raffleID = tempId;  TO BE SET THROUGH USE CASE
    }

//    public boolean checkIdsMatch(String id){
//        return this.raffleId.equals(id);
//    }

    @Override
    public String toString(){
        // only to be called when all information is available
        String generalInfo = "Raffle Name: " + this.raffleName + "\nRaffle ID: " + this.raffleId +
                "\nEnding Date: " + this.getFormattedEndDate() + "\nNumber of Winners: "
                + this.numberOfWinners;

        StringBuilder taskListStr = new StringBuilder();
        int i;
        for (i = 0; i < this.taskIdList.size(); i++){
            taskListStr.append("[").append(i).append("]").append(this.taskIdList.get(i)).append("\n");
        }

        return generalInfo + "\nTasks:\n" + taskListStr;

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

    public String getFormattedEndDate(){
        int year = this.endDate.getYear();
        int month = this.endDate.getMonthValue();
        int day = this.endDate.getDayOfMonth();

        return year + "/" +  month + "/" + day;
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
