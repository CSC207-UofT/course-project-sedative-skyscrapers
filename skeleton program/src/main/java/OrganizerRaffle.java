package main.java;

import java.time.LocalDate;
import java.util.*;

public class OrganizerRaffle extends Raffle {
    private ArrayList<User> validParticipants;
    private ArrayList<User> winnerList;

    /**
     * Initializer in charge of creating an OrganizerRaffle object, calling Raffle's first
     * initializer, and adding the needed extras of the subclass. Its main purpose is to add a
     * User create a new Raffle object for participant User to join.
     * @param raffleOrg User object to join Raffle as an organizer
     * @param raffleName The String representing the name given to this Raffle object
     * @param numOfWinners The int describing the amount of participant Users who will be able to win a prize
     * @param endDate The LocalDate describing the date when participant Users can no longer enter the Raffle
     *               or complete Tasks
     */
    public OrganizerRaffle(User raffleOrg, String raffleName, int numOfWinners, LocalDate endDate){
        super(raffleOrg, raffleName, numOfWinners, endDate);
        this.validParticipants = new ArrayList<>();
        this.winnerList = new ArrayList<>();
    }

    // public void setRules(File rules){super.setRules(rules);}

    @Override
    public void setTaskList(ArrayList<Task> taskList) {
        super.setTaskList(taskList);
    }

    @Override
    public void addNewTask(Task newTask){
        super.addNewTask(newTask);
    }

    public void addValidParticipant(User participant) {this.validParticipants.add(participant);};

    @Override
    public String toString(){
        String generalInfo = "Raffle Name: " + getRaffleName() + "\nRaffle ID: " + getRaffleID() + "\nRaffle Creator: "
                + this.getOrganizer().getUsername() + "\nEnding Date: " + this.getEndDate() + "\nNumber of Winners: "
                + this.getNumberOfWinners();

        StringBuilder taskListStr = new StringBuilder();
        int i;
        for (i = 0; i < this.getTaskList().size(); i++){
            taskListStr.append("[").append(i).append("]").append(getTaskList().get(i)).append("\n");
        }

        StringBuilder ptcListStr = new StringBuilder();
        int j;
        for (j = 0; j < this.getTaskList().size(); j++){
            ptcListStr.append("[").append(j).append("]").append(getParticipantList().get(j)).append("\n");
        }
        return generalInfo + "\nTasks:\n" + taskListStr + "\nParticipants:\n" + ptcListStr;

    }




}