package main.java;

public class COMMANDTEST {
    public static void main(String[] args) {

        Task t = new Task("mai", "Step 1 complete, link opened");
        t.setTaskID(1);
        Task t1 = new Task("g", "f");
        t1.setTaskID(2);
        Task t2 = new Task("Java", "loda");
        DummyRaffle R = new DummyRaffle(t, t1, t2);
        CommandTaskA C = new CommandTaskA(R, t);
        CommandTaskA B = new CommandTaskA(R, t1);
        Button J = new Button(B);
        Button I = new Button(C);
        I.press();
        J.press();

    }
}
