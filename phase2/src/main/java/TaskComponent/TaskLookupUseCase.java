package main.java.TaskComponent;
import main.java.DatabaseRe.AccessData;
import main.java.database.GetTaskDetails;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskLookupUseCase {
    public final String taskID;
    public DataAccessPoint extractor;

    public TaskLookupUseCase(String taskID) throws SQLException {
        this.taskID = taskID;
        this.extractor = new AccessData();
    }

    public ArrayList<String> getTaskInfo() {
        /*
        Returns task info from database
         */
        return extractor.getTaskById(this.taskID);
    }

}
