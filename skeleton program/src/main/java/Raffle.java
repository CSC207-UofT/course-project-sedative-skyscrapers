package main.java;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// raffle works very weirdly, Im not sure if it should really even be a class at this point, it makes more sense
// to have the two current subclasses on their own being joined together through some sort of raffleManager class


public class Raffle {
    // to be replaced once database implementation is done
    public static int nextRaffleId = 0;
    public static ArrayList<Raffle> allRaffles = new ArrayList<>();
    private String raffleName;
    private List<Task> taskList = new ArrayList<>();
    private int raffleID;
    private int numberOfWinners;
    private String rules;  // for now any string will suffice
    private Date endDate;
    private ArrayList<User> participantList = new ArrayList<>();


    public Raffle() {
    }


    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task newTask) {
        this.taskList.add(newTask);
    }

    public int getRaffleID() {
        return raffleID;
    }

    public void setRaffleID(int raffleID) {
        this.raffleID = raffleID;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRaffleName() {
        return raffleName;
    }

    public void setRaffleName(String raffleName) {
        this.raffleName = raffleName;
    }

    public int getNumberOfWinners() {
        return numberOfWinners;
    }

    public void setNumberOfWinners(int numberOfWinners) {
        this.numberOfWinners = numberOfWinners;
    }

    public ArrayList<User> getParticipantList() {
        return participantList;
    }

    public void addParticipantToList(User participant) {
        this.participantList.add(participant);
    }
}
