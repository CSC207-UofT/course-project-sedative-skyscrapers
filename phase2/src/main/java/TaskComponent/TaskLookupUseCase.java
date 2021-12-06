package main.java.TaskComponent;
import main.java.database.GetTaskDetails;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TaskLookupUseCase {
    public final String taskID;
    public GetTaskDetails extractor;

    public TaskLookupUseCase(String taskID) throws FileNotFoundException {
        this.taskID = taskID;
        this.extractor = new GetTaskDetails();
    }

    public ArrayList<String> getTaskInfo() throws FileNotFoundException{
        return extractor.getTaskDetails(this.taskID);
    }

}
