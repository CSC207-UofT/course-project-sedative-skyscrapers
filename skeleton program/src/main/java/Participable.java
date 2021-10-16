package main.java;

import java.util.List;

public interface Participable {
    Integer raffleID = 0;
    public String userID = null;
    public boolean completedAllTasks = false;
    List<Integer> tasksLeft = null;
    // TODO: should we make <completeTasks> a private variable?
    List<Integer> completedTasks = null;
    //

    public ParticipantRaffle joinRaffle(int raffleID);
    /* Join the raffle using the unique raffle id.
    Update the <this.raffleID> */

    public void completeTask(int taskID, int raffleID);
        /*
        Show what the task is, track that the task is done and submit
         (input from user) [we may want a different class as per the
         clean architecture] answer
         If the user has completed a task shift it from <tasksLeft> to
         <completedTasks>
         */
    public boolean checkAnswer(int raffleID, int taskID);
    /* check if the answer is correct  */

    // public void claimPrize();
    /*
    Checks if all the tasks are completed and then
    updates the <completedAllTasks> boolean
    */

    public void showTasks(int raffleID);
    /* prints the tasks the partcipant has to do and the ones done */
}
