package main.java;

public class Button {

    Command CompleteTask;

    public Button(Command newTask){
        CompleteTask = newTask;
    }
    public void press() {
        CompleteTask.execute();
    }
}
