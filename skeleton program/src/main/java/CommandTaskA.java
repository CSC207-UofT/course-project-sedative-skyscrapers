package main.java;

public class CommandTaskA implements Command {

    DummyRaffle theRaffle;
    Task TaskA;

    public CommandTaskA(DummyRaffle newRaffle, Task theTask){

        theRaffle = newRaffle;
        TaskA = theTask;

    }

    @Override
    public void execute() {

        for (Task t: theRaffle.tasksReq) {

            if (t.getTaskID() == TaskA.getTaskID()) {
                theRaffle.transferTask(t);
                System.out.println(t.userAnswer);
            }


        }

    }
}
