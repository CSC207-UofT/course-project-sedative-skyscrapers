package main.java;
import java.util.ArrayList;

public class COMMANDTEST {
    public static void main(String[] args) throws Exception {
        Task t1 = new Task("Task 1", "First Task", "https://google.com");
        t1.setTaskID(1);
        Task t2 = new Task("Task 2", "Second Task", "https://youtube.com");
        t2.setTaskID(2);
        Task t3 = new Task("Task 3", "Third Task", "https://instagram.com");
        t3.setTaskID(3);
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(t1);
        taskList.add(t2);
        taskList.add(t3);
        User U = new User("Tester");
        ParticipantRaffle R = new ParticipantRaffle(U, 3);
        R.setTasksReq(taskList);
        CommandTask C = new CommandTask(R, t1);
        CommandTask B = new CommandTask(R, t2);
        CommandTask D = new CommandTask(R, t3);
        Button J = new Button(B);
        Button I = new Button(C);
        Button K = new Button(D);
        I.press();
        // I.press();
        //System.out.println(R.getTasksCompleted());
        //J.press();
        //K.press();
        System.out.println(R.isValidParticipant());
        ParticipantRaffle Rafe = new ParticipantRaffle(U, 4);
        Rafe.setTasksReq(taskList);
        CommandTask E = new CommandTask(R, t3);
        I = new Button(E);
        I.press();


    }
}
