package main.java;

public class Task {
    private String question;
    private final String answer;
    public String userAnswer;
    private boolean taskState;  // true means completed by participant, false otherwise.
    private int taskID;

    public Task(String q, String a) {
        this.question = q;
        this.answer = a;
        this.userAnswer = a;
        this.taskID = 0;
        this.taskState = false;
    }

    //Verify participant answer
    public boolean verifyAnswer(String a, String ua) {
        return a.equals(ua);
    }

    public String getQuestion() {
        return question;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    @Override
    public String toString() {
        String completedTask;
        if (taskState) {
            completedTask = "Completed";
        } else {
            completedTask = "Yet to complete";
        }
        return "Question: " + this.question + "; Task State: " + completedTask;

    }

    public String getAnswer() {
        return answer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAns){this.userAnswer = userAns;}

    public void setTaskState(boolean newTaskState){this.taskState = newTaskState;}
}
