package main.java;

import java.util.ArrayList;

public class DummyRaffle {

    public ArrayList<Task> tasksToComplete;
    public ArrayList<Task> tasksCompleted;

    public DummyRaffle (Task t, Task t1) {
        this.tasksToComplete = new ArrayList<Task>();
        this.tasksToComplete.add(t);
        this.tasksToComplete.add(t1);
        this.tasksCompleted = new ArrayList<>();

    }

    public void transferTask(Task t1){

        for (Task t: this.tasksToComplete) {
            if (t == t1) {
                this.tasksToComplete.remove(t);
                t.setTaskState(true);
                System.out.println("This Task has been completed!");
                this.tasksCompleted.add(t);
            }
            else {
                System.out.println("Task not in tasks to be completed list");
            }
        }
    }

}
