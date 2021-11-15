package main.java.TaskWeb;

import main.java.TaskComponent.TaskLookupUseCase;

import java.util.ArrayList;

public class TaskLookupController {
    private final String taskID;
    private final TaskLookupUseCase taskInfoManager;

    public TaskLookupController(String taskID) {
        this.taskID = taskID;
        this.taskInfoManager = new TaskLookupUseCase(this.taskID);
    }

    public ArrayList<String> runLookupTaskInfo() {
        /* todo: for getting raffle Info from a particular organizer, just use the user class' method to
     return the raffleIds they are involved in, and call runLookupPtcRaffleInfo for each of them
     */
        return this.taskInfoManager.getTaskInfo(); //todo is this correct? check with raffle lookupcase
    }
}
