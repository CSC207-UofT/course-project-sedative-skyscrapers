package main.java.Web;

import main.java.TaskComponent.TaskLookupUseCase;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TaskLookupController {
    private final String taskID;
    private final TaskLookupUseCase taskInfoManager;

    public TaskLookupController(String taskID) throws FileNotFoundException {
        this.taskID = taskID;
        this.taskInfoManager = new TaskLookupUseCase(this.taskID);
    }

    public ArrayList<String> runLookupTaskInfo() throws FileNotFoundException{
        return this.taskInfoManager.getTaskInfo();
    }
}
