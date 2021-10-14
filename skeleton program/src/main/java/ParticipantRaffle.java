package main.java;

import main.java.Raffle;

import java.util.ArrayList;
import java.util.List;

public class ParticipantRaffle extends Raffle {

    private boolean validParticipant;
    private List<Task> tasksToComplete;
    private List<Task> tasksCompleted;


    // joins raffleUser to a raffle
    public ParticipantRaffle(User rafflePtc, int raffleID) {
        super(rafflePtc, raffleID);
        this.validParticipant = false;
        this.tasksToComplete = super.getTaskList();
        /* for now these attributes are equal, but the idea is to have tasksToComplete be a deep
            copy of the Raffle's taskList since that way the participant can manipulate the state of
            individual tasks
        */
        this.tasksCompleted = new ArrayList<>();
    }

        // TODO: discuss deep copy of taskList in Raffle to allow for changing of taskState -> making .copy
        //  method for task
        //  OR participants are not yet allowed to complete tasks for phase0, they can just view them

   //  public gainEntry(){...}


    public void setValidParticipant(){
        if (!this.tasksCompleted.isEmpty()){
            this.validParticipant = true;
        }
    }

    public List<Task> getTasksToComplete() {
        return tasksToComplete;
    }

    public void setTasksToComplete(List<Task> tasksToComplete) {
        this.tasksToComplete = tasksToComplete;
    }

}
