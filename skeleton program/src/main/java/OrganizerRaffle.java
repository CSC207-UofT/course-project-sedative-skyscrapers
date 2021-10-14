package main.java;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrganizerRaffle extends Raffle {
    private List<User> validParticipants;
    private List<User> winnerList;

    public OrganizerRaffle(User raffleOrg, String raffleName, int numOfWinners, Date endDate){
        super(raffleOrg, raffleName, numOfWinners, endDate);
        this.validParticipants = new ArrayList<>();
        this.winnerList = new ArrayList<>();

    }

    // public void setRules(File rules){super.setRules(rules);}

    public void setTaskList(List<Task> taskList) {
        super.setTaskList(taskList);
    }

    public void addNewTask(Task newTask){
        super.addTask(newTask);
    }

    public void addValidParticipant(User participant) {this.validParticipants.add(participant);};





}
