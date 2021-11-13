package main.java.TaskComponent;

import main.java.ParticipantRaffle;
import main.java.Task;

import java.awt.*;
import java.net.URI;

public class CommandTask implements Command {

    ParticipantRaffle theRaffle;
    main.java.Task TaskA;

    public CommandTask(ParticipantRaffle newRaffle, main.java.Task theTask){

        theRaffle = newRaffle;
        TaskA = theTask;

    }

    @Override
    public void execute() throws Exception{

        for (Task t: theRaffle.getTasksReq()){

            if (t.getTaskID() == TaskA.getTaskID() && !theRaffle.getTasksCompleted().contains(t)) {
                theRaffle.transferTask(t);
                System.out.println(t.link);
                Desktop d = Desktop.getDesktop();
                d.browse(new URI(t.getLink()));

            }


        }

    }
}
