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
        Desktop d = Desktop.getDesktop(); // returns desktop instance of current browser context
        String link = Looker.getTaskInfo().get(2); // extracts the taskLink from the task info
        link.trim(); // removes leading and trialing spaces
        d.browse(new URI(link)); // launches the default browser to display URI

    }
}
