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
        /*
        Launches default browser to display URI after extracting taskLink
         */

        TaskLookupUseCase Looker = new TaskLookupUseCase(theTaskID);
        Desktop d = Desktop.getDesktop(); // returns desktop instance of current browser context
        String link = Looker.getTaskInfo().get(2);
        link.trim(); // removes leading and trailing spaces
        d.browse(new URI(link));

    }
}
