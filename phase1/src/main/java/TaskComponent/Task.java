package main.java.TaskComponent;

import java.util.HashMap;


public class Task {
    public static HashMap<Integer, Task> allTasks = new HashMap<>();
    public String name;
    public String description;
    public String link;
    /* private final String question;
    private final String answer;
    public String userAnswer;
    public String type;*/
    // private boolean taskState;  // true means completed by participant, false otherwise.
    private int taskID;

    public Task(String name, String description, String link) {
        /*this.question = q;
        this.answer = a;
        this.userAnswer = a;*/
        this.taskID = 0;
        this.name = name;
        this.description = description;
        this.link = link;
    }

    //Verify participant answer
    public boolean verifyAnswer(String a, String ua) {
        return a.equals(ua);
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getLink(){
        return link;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void setTaskLink(String link) {
        this.link = link;
    }


    @Override
    public String toString() {
       return("Name: " + name + ", Description: " + description + ", Link: " + link);

    }


}
