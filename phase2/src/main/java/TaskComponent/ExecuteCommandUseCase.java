package main.java.TaskComponent;

public class ExecuteCommandUseCase {
    private String ptcRaffleID;
    private String taskCompletedID;
    private CommandTask complete;
    private Button invoker;

    public ExecuteCommandUseCase(String raffleID, String taskID){
        this.ptcRaffleID = raffleID;
        this.taskCompletedID = taskID;
        this.complete = new CommandTask(ptcRaffleID, taskCompletedID);
        this.invoker = new Button(complete);
    }

    public void RunExecuteCommand() throws Exception{
        /*
        button is pressed to execute command and then it is executed (command design pattern)
         */
        this.invoker.press();
    }

}
