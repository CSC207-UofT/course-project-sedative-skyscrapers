package main.java;

import java.util.ArrayList;

public class DummyRaffle {

    public ArrayList<Task> tasksReq;
    public ArrayList<Task> tasksCompleted;

    public DummyRaffle (Task t, Task t1, Task t2) {
        this.tasksReq = new ArrayList<Task>();
        this.tasksReq.add(t);
        this.tasksReq.add(t1);
        this.tasksReq.add(t2);
        this.tasksCompleted = new ArrayList<>();

    }

    public void transferTask(Task t1){

        ArrayList<Task> completedCollector = new ArrayList<>();
        for (Task t: this.tasksReq) {
            if (t == t1 && !completedCollector.contains(t1)) {
                // this.tasksReq.remove(t);
                System.out.println("This Task has been completed!");
                this.tasksCompleted.add(t);
                completedCollector.add(t);
                //break;
            }
        }
    }

}
