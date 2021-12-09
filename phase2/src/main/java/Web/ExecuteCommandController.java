package main.java.Web;

import main.java.TaskComponent.ExecuteCommandUseCase;

public class ExecuteCommandController {

    private String ptcRaffleID;
    private String taskCompletedID;

    public ExecuteCommandController(String raffleID, String taskID){
        this.ptcRaffleID = raffleID;
        this.taskCompletedID = taskID;
    }

    public void runExecuteCommand() throws Exception{
        ExecuteCommandUseCase executor = new ExecuteCommandUseCase(this.ptcRaffleID, this.taskCompletedID);
        executor.RunExecuteCommand();
    }
}
