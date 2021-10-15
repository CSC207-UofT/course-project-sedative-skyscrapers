package main.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Raffle class could be made abstract, exporting shared things to ParticipantRaffle and OrganizerRaffle
// while letting them add the methods that are independent to them

public class Raffle {

    // to be replaced once database implementation is done
    public static int nextRaffleId = 0;
    public static ArrayList<Raffle> allRaffles = new ArrayList<>();

    private String raffleName;
    private int raffleID;
    private int numberOfWinners;
    // private File rules;  // not intended as part of phase0
    private Date endDate;
    private User currentParticipant;  // the participant to make use of Raffle class
    private User organizer;  // the user who organizes the Raffle

    private ArrayList<User> participantList = new ArrayList<>();
    private List<Task> taskList = new ArrayList<>();  // all tasks stay with state false


    // to properly separate cases we could extend project by having Participant and Organizer be separate classes

    public Raffle(User raffleUser, String raffleName, int numOfWinners, Date endDate){
        this.raffleName = raffleName;
        this. numberOfWinners = numOfWinners;
        this.endDate = endDate;
        this.raffleID = Raffle.nextRaffleId;
        nextRaffleId += 1;  // since its static, a new instance of the class should get a new raffleID (not repeated)
        this.organizer = raffleUser;
    }

    // as of now, the different args are the only thing separating the organizer from the participants,
    // despite it not being used
    public Raffle(User raffleUser, int RaffleID) {
        this.currentParticipant = raffleUser;
        this.participantList.add(raffleUser);
    }


    public String getRaffleName() {
        return raffleName;
    }

    public void setRaffleName(String raffleName) {
        this.raffleName = raffleName;
    }

    public int getRaffleID() {
        return raffleID;
    }

    public void setRaffleID(int raffleID) {
        this.raffleID = raffleID;
    }

    public int getNumberOfWinners() {
        return numberOfWinners;
    }

    public void setNumberOfWinners(int numberOfWinners) {
        this.numberOfWinners = numberOfWinners;
    }

    // public String getRules() {...}

    // public void setRules(String rules) {...}

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ArrayList<User> getParticipantList() {
        return participantList;
    }

    public void addParticipantToList(User participant) {
        this.participantList.add(participant);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task newTask) {
        this.taskList.add(newTask);
        for (User ptc: this.participantList){
            for (ParticipantRaffle ptcRaff: ptc.raffleListPTC){
                // each User should have a raffleListPTC for participation and raffleListOrg for organization
                if (ptcRaff.getRaffleID() == this.raffleID){
                    ptcRaff.setTasksToComplete(taskList);  // updating the tasks
                }
            }
        }
    }

    public void addValidParticipant(User participant){
        for(OrganizerRaffle orgRaff: this.organizer.raffleListOrg){
            // each User should have a raffleListPTC for participation and raffleListOrg for organization
            if (orgRaff.getRaffleID() == this.raffleID){
                orgRaff.addValidParticipant(participant);
            }
        }
    }

}
