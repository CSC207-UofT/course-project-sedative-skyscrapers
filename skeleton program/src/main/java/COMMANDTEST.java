package main.java;

public class COMMANDTEST {
    public static void main(String[] args) {

        Task t = new Task("mai", "Step 1 complete, link opened");
        Task t1 = new Task("g", "f");
        DummyRaffle R = new DummyRaffle(t, t1);
        CommandTaskA C = new CommandTaskA(R, t);
        Button I = new Button(C);
        I.press();

    }
}
