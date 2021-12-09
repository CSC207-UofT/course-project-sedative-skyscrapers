
package main.java.Web;


/**
 * This class builds the TaskController objects with their respective attributes.
 */

public class TaskDirector {
    private TaskController TaskContr;

    public TaskDirector(){
        this.reset();
    }

    private void reset(){
        this.TaskContr = new TaskController();
    }

    public void taskBuildCreator(String raffleID, String taskName, String description, String taskLink){
        /*
        sets the values of all required variables in order to instantiate a task
         */
        this.TaskContr.setRaffleID(raffleID);
        this.TaskContr.setDescription(description);
        this.TaskContr.setTaskLink(taskLink);
        this.TaskContr.setTaskName(taskName);
    }

    public void taskBuildExecutor(String raffleID, String taskID){
        /*
        sets values of all variables required to execute a task (completing a task)
         */
        this.TaskContr.setRaffleID(raffleID);
        this.TaskContr.setTaskID(taskID);
    }

    public void taskBuildLookup(String taskID){
        /*
        sets values of variables required to lookup tasks using taskID

         */
        this.TaskContr.setTaskID(taskID);
    }

    public TaskController getTaskContr() {
        return TaskContr;
    }
}