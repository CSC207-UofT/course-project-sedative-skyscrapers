package main.java;

import java.time.LocalDate;
import java.util.*;

// Raffle class could be made abstract, exporting shared things to ParticipantRaffle and OrganizerRaffle
// while letting them add the methods that are independent to them.

/**
 * Class representing a Raffle object, which can be joined or created by Participants and Organizers respectively.
 */
public class Raffle {

    // to be replaced once database implementation is done
    public static HashMap<Integer, Raffle> allRaffles = new HashMap<>(); //hashmap

    private String raffleName;
    private int raffleID;
    private int numberOfWinners;
    // private File rules;  // not intended as part of phase0
    private LocalDate endDate;
    private User organizer;  // the user who organizes the Raffle

    private ArrayList<User> participantList;
    private ArrayList<Task> taskList;  // all tasks stay with state false


    // to properly separate cases we could extend project by having Participant and Organizer be separate classes

    /**
     * Initializer for a Raffle object for OrganizerRaffle to call when using super(...), establishing all
     * the information the Raffle needs from a creator's standpoint.
     * @param raffleUser The User object that is creating this raffle
     * @param raffleName The name to be given to the raffle for ease of identifying
     * @param numOfWinners The number of participants that might be able to win a prize
     * @param endDate The time after which participant Users can no longer join or complete a Task in a Raffle
     */
    public Raffle(User raffleUser, String raffleName, int numOfWinners, LocalDate endDate){
        this.raffleName = raffleName;
        this.numberOfWinners = numOfWinners;
        this.endDate = endDate;
        this.participantList = new ArrayList<>();
        this.taskList = new ArrayList<>();


        // generate a raffleID

        this.raffleID = (int)(900 * Math.random() + 100);

        for (Integer raffID: allRaffles.keySet()){  // check for organizer raffle ID duplication
            if (this.raffleID == raffID){
                this.raffleID = (int)(900 * Math.random() + 100);
            }
        }

        this.organizer = raffleUser;
    }

    // as of now, the different args are the only thing separating the organizer from the participants,
    // despite it not being used

    /**
     * Initializer for a Raffle object for ParticipantRaffle to call when using super(...), establishing all
     * the information that Raffle needs to join a participant User to an existing Raffle
     * @param raffleUser The User object that is joining the already created Raffle (creating a new instance
     *                  of Raffle based on the one made by the organizer User)
     * @param raffleID The randomly assigned ID of the Raffle that raffleUser wants to join
     */

    public Raffle(User raffleUser, int raffleID) {
        this.participantList = new ArrayList<>();
        // this.taskList = new ArrayList<>();
        this.participantList.add(raffleUser);

        // important note: the ParticipantRaffle object is not to be added to allRaffles
        this.raffleID = raffleID;  // ID of Raffle object is set to ID specified by user
        for (Integer raffID: allRaffles.keySet()){
            if (this.raffleID == raffID){

                // find the corresponding organizer raffle, copy the variables that Participant Raffle
                // may need to fulfill its responsibilities
                this.taskList = allRaffles.get(raffID).taskList;
                this.raffleName = allRaffles.get(raffID).getRaffleName();
                this.organizer = allRaffles.get(raffID).getOrganizer();
                this.endDate = allRaffles.get(raffID).getEndDate();

            }
        }
    }

    // Just getters and setters for some attributes other classes/subclasses need to interact with Raffle

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

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ArrayList<User> getParticipantList() {
        return participantList;
    }

    public void addParticipantToList(User participant) {
        this.participantList.add(participant);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public User getOrganizer(){ return this.organizer;}


    /**
     * Method in charge of adding a new Task object to the ArrayList this.taskList, according to how
     * the organizer object controlling the Raffle create suh Task object. This method is also in charge of
     * reflecting these changes made by the organizer User on the Raffle onto the participant User Raffle.
     * @param newTask The Task object being added to this.taskList
     */
    public void addNewTask(Task newTask) {
        this.taskList.add(newTask);
        for (User ptc: this.participantList){
            for (ParticipantRaffle ptcRaff: ptc.getPtcRaffles().values()){
                // each User should have a raffleListPTC for participation and raffleListOrg for organization
                if (ptcRaff.getRaffleID() == this.raffleID){
                    ptcRaff.setTasksReq(taskList);  // updating the tasks
                }
            }
        }
    }

    /**
     * The method in charge of modifying OrganizerRaffle.validParticipants once a participant User object
     * has completed at least one Task specified in this.taskList, therefore making them valid participants
     * to be entered into the draw for the Raffle object's prize.
     * @param participant The participant User object that has completed at least one Task in this.taskList
     */
    public void addValidParticipant(User participant){
        for(Raffle orgRaff: this.organizer.getOrgRaffles().values()){
            // each User should have a raffleListPTC for participation and raffleListOrg for organization
            if (orgRaff.getRaffleID() == this.raffleID){
                orgRaff.addValidParticipant(participant);
            }
        }
    }

}
