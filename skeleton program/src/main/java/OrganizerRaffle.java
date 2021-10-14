package main.java;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrganizerRaffle extends Raffle {
    private List<User> validParticipant = new ArrayList<>();
    private List<User> winnerList = new ArrayList<>();

    public OrganizerRaffle(String raffleName, int numOfWinners, Date endDate)
    {
        super.setRaffleName(raffleName);
        super.setNumberOfWinners(numOfWinners);
        super.setRaffleID(nextRaffleId);
        nextRaffleId += 1;  // since its static, a new instance of the class should get a new raffleID (not repeated)
        super.setEndDate(endDate);
    }

    public void setRules(String rules){
        super.setRules(rules);
    }

    public void setTaskList(List<Task> taskList) {
        super.setTaskList(taskList);
    }

    public void addNewTask(Task newTask){
        super.addTask(newTask);
    }
    // TODO: make sure main.java.User doesnt implement these methods




}
