package main.java;

public class Task {
    private String question;
    private final String answer;
    private String userAnswer;

    public Task(String q, String a){
        this.question = q;
        this.answer = a;
        this.userAnswer = null;
    }

    //Verify participant answer
    public boolean verifyAnswer(String a, String ua) {
        return a.equals(ua);
    }


}
