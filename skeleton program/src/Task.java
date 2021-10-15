public class Task {
    private String question;
    private final String answer;
    private String userAnswer;
    private int taskID;

    public Task(String q, String a){
        this.question = q;
        this.answer = a;
        this.userAnswer = null;
        this.taskID = 0;
    }

    //Verify participant answer
    public boolean verifyAnswer(String a, String ua) {
        return a.equals(ua);
    }


    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }
}
