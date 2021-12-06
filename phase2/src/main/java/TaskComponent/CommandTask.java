package main.java.TaskComponent;

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

        TaskLookupUseCase Looker = new TaskLookupUseCase(theTaskID);
        Desktop d = Desktop.getDesktop();
        d.browse(new URI(Looker.getTaskInfo().get(1))); // extracts the taskLink from the task info

    }
}
