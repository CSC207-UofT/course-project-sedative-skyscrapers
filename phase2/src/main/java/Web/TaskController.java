package main.java.Web;

import main.java.TaskComponent.CreateTaskUseCase;
import main.java.TaskComponent.ExecuteCommandUseCase;
import main.java.TaskComponent.TaskLookupUseCase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskController {
    private String raffleID;
    private String taskName;
    private String description;
    private String taskLink;
    private String taskID;
    private ArrayList<String> taskInfo;

    public enum taskAction{
        CREATE, EXECUTE, LOOKUP
    }

    public TaskController(){
        // they are all set to null for the Task Director to initialize
        this.taskID = null;
        this.taskLink = null;
        this.taskName = null;
        this.description = null;
        this.raffleID = null;
        this.taskInfo = null;
    }

    public void runTaskController(taskAction actionToProcess) throws Exception {
        switch(actionToProcess){
            case CREATE:
                if((taskName != null) && (taskLink != null) && (description != null)){
                    // a task can be created only if tas name, link and description is given
                    this.runCreate();
                }
                break;

            case EXECUTE:
                if ((raffleID != null) && (taskID != null)){
                    // task is executed when raffleID and taskID are given
                    this.runExecute();
                }
                break;

            case LOOKUP:
                if((taskID != null) && (raffleID != null)){
                    // when only taskID is provided
                    this.runLookup();
                }


        }
    }

public void runCreate() throws SQLException {
        CreateTaskUseCase createTaskManager = new CreateTaskUseCase(this.raffleID, this.taskName, this.description, this.taskLink);
        this.taskID = createTaskManager.runTaskCreation();
}

public void runExecute() throws Exception {
        ExecuteCommandUseCase executeManager = new ExecuteCommandUseCase(this.raffleID, this.taskID);
        executeManager.RunExecuteCommand();
}

public void runLookup() throws FileNotFoundException, SQLException {
        TaskLookupUseCase lookupManager = new TaskLookupUseCase(this.taskID);
        this.taskInfo = lookupManager.getTaskInfo();
}

// setters

public void setRaffleID(String raffleID){
        this.raffleID = raffleID;
}

public void setTaskID(String taskID){
        this.taskID = taskID;
}

public void setDescription(String description){
        this.description = description;
}

public void setTaskLink(String taskLink){
        this.taskLink = taskLink;
}

public void setTaskName(String taskName){
        this.taskName = taskName;
}
// getters

public String getTaskID(){
        return this.taskID;
}

public ArrayList<String> getTaskInfo(){
        return this.taskInfo;
}
}