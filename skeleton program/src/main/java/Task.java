package main.java;

public class Task {
    private String question;
    private final String answer;
    private String userAnswer;

    // both should be expanded to subclasses of task for phase1
    public boolean taskUser;  // true means the User is an organizer, false if user is a participant
    private boolean taskState;  // true means completed by participant, false otherwise

    public Task(String q, String a){
        this.question = q;
        this.answer = a;
        this.userAnswer = null;
        this.taskState = false;
    }

    //Verify participant answer
    public boolean verifyAnswer(String a, String ua) {
        return a.equals(ua);
        // since we are inside a class, this would be just consist on getting the input and comparing
        // it to the instance variable answer
    }

    // check state method called after verifyAnswer is called to set taskState to true if verifyAnswer is true

}
