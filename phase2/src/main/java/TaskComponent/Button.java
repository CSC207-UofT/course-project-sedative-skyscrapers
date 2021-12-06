package main.java.TaskComponent;

public class Button {

    Command CompleteTask;

    public Button(Command newTask){
        CompleteTask = newTask;
    }
    public void press() throws Exception {
        CompleteTask.execute(); // execution of command to complete task (by pressing button to open hyperlink)
    }
}
