package main.java.TaskComponent;

import java.util.HashMap;


public class Task {
    private String name;
    private String description;
    private String link;
    private String taskID;

    public Task(String name, String description, String link) {
        this.name = name;
        this.description = description;
        this.link = link;
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

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    // public void setTaskLink(String link) {this.link = link;}


    @Override
    public String toString() {
       return("Name: " + name + ", Description: " + description + ", Link: " + link);

    }


}
