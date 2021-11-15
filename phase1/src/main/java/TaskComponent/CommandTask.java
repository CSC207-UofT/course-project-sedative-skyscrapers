package main.java.TaskComponent;

import main.java.RaffleComponent.CompleteTaskUseCase;
import main.java.TaskComponent.Task;

import java.awt.*;
import java.net.URI;

public class CommandTask implements Command {

    String theRaffleID;
    String theTaskID;

    public CommandTask(String raffleID, String taskID){

        theRaffleID = raffleID;
        theTaskID = taskID;

    }

    @Override
    public void execute() throws Exception{

        CompleteTaskUseCase Completer = new CompleteTaskUseCase(theTaskID, theRaffleID);
        Completer.completeTask(); //todo: ensure system manager doesn't repeat this
        TaskLookupUseCase Looker = new TaskLookupUseCase(theTaskID);
        Desktop d = Desktop.getDesktop();
        d.browse(new URI(Looker.getTaskInfo().get(2)));

    }
}
