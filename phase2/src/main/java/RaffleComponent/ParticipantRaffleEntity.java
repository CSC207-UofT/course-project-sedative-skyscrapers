package main.java.RaffleComponent;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class representing a ParticipantRaffle object, which can be joined/created by Participants only.
 */
public class ParticipantRaffleEntity {

    private final String raffleName;
    private String raffleId;
    private final int numberOfWinners;
    private String raffleRules;
    private LocalDate endDate;
    private ArrayList<String> taskIdList;

    /**
     * Constructor initializing a new RaffleEntity, parent to OrganizerRaffleEntity
     * @param raffleName the name of this raffle
     * @param numberOfWinners the number of participants to be able to win this raffle
     * @param endDate the day this raffle ends
     */
    public ParticipantRaffleEntity(String raffleName, int numberOfWinners, LocalDate endDate){
        this.raffleName = raffleName;
        this.numberOfWinners = numberOfWinners;
        this.endDate = endDate;
        this.taskIdList = new ArrayList<>();

//        this.raffleID = tempId;  TO BE SET THROUGH USE CASE
    }

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

    // getters and setters

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

//    // observer pattern update method
//    public ArrayList<String> updateTaskIdList(boolean editType, ArrayList<String> taskIds){
//        // true means add tasks, false means remove tasks
//        if (editType){
//            this.getTaskIdList().addAll(taskIds);
//        } else {
//            this.getTaskIdList().removeAll(taskIds);
//        }
//        return this.getTaskIdList();
//    }

    // observer pattern update method
    public LocalDate updateEndDate(LocalDate newEndDate){
        // true means add tasks, false means remove tasks
        this.endDate = newEndDate;
        return this.endDate;
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
