package main.java;

import main.java.Raffle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParticipantRaffle extends Raffle {

    private boolean validParticipant;
    private ArrayList<Task> tasksToComplete;
    private ArrayList<Task> tasksCompleted;
    private User currentParticipant;  // the participant to make use of Raffle class


    /**
     * Initializer in charge of creating a ParticipantRaffle object, calling Raffle's second
     * initializer, and adding the needed extras of the subclass. Its main purpose is to add a
     * User participant to the Raffle object.
     * @param rafflePtc User object to join the Raffle as a participant
     * @param raffleID int unique to the Raffle the User wants to join
     */
    public ParticipantRaffle(User rafflePtc, int raffleID) {
        super(rafflePtc, raffleID);
        this.validParticipant = false;
        this.tasksToComplete = super.getTaskList();
        /* for now these attributes are equal, but the idea is to have tasksToComplete be a deep
            copy of the Raffle's taskList since that way the participant can manipulate the state of
            individual tasks
        */
        this.tasksCompleted = new ArrayList<>();
        this.currentParticipant = rafflePtc;
    }

    //  public gainEntry(){...}

    /**
     * Modified setter, checking for the completion of at least one Task to have the User of this
     * ParticipantRaffle object be considered eligible for the draw for the prize.
     *
     */
    public void setValidParticipant(){
        if (!this.tasksCompleted.isEmpty()){
            this.validParticipant = true;
        }
    }

    public ArrayList<Task> getTasksToComplete() {
        return tasksToComplete;
    }

    public void setTasksToComplete(ArrayList<Task> tasksToComplete) {
        this.tasksToComplete = tasksToComplete;
    }

    @Override
    public String toString(){
        String generalInfo = "Raffle Name: " + getRaffleName() + "\nRaffle ID: " + getRaffleID() + "\nRaffle Creator: "
                + this.getOrganizer().getUsername() + "\nEnding Date: " + this.getEndDate();

        // String of taskList should let user see the tasks numbered for them to choose to complete a Task
        StringBuilder taskListStr = new StringBuilder();
        int i;
        for (i = 0; i < this.getTasksToComplete().size(); i++){
            taskListStr.append("[").append(i).append("]").append(getTasksToComplete().get(i)).append("\n");
        }

        return generalInfo + "\nTasks:\n" + taskListStr;

    }

}
