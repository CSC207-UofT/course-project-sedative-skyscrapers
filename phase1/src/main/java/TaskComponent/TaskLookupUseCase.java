package main.java.TaskComponent;
import java.util.ArrayList;

public class TaskLookupUseCase {
    public final String taskID;

    public TaskLookupUseCase(String taskID) { this.taskID = taskID;}

    public ArrayList<String> getTaskInfo(){
        // todo uncomment: ArrayList<Object> theTaskInfo = DataAccess.getTaskById(this.taskId);
        ArrayList<String> taskInfo = new ArrayList<>();
        // returns [name of task, description, link]
        taskInfo = new ArrayList<String>(theTaskInfo.get(1), theTaskInfo.get(2), theTaskInfo.get(3));
        return taskInfo;
    }

}
