package main.java.TaskComponent;
import main.java.DatabaseRe.AccessData;
import main.java.DatabaseRe.ProvideData;
import main.java.Helpers.TaskIdGenerator;
import main.java.database.AddOrganizer;
import main.java.database.GetTaskDetails;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreateTaskUseCase {

    private Task task;
    private TaskIdGenerator idGenerator;
    private static final char entityCode = 'T';
    private ArrayList<String> takenIds;
    private AccessData extractor;
    private ProvideData writer;
    private String raffleId;

    public CreateTaskUseCase(String raffleID, String name, String description, String link) throws SQLException {
        this.raffleId = raffleID;
        this.task = new Task(name, description, link); //creation of task object
        this.extractor = new AccessData();
        this.takenIds = this.extractor.getTakenTaskIds();
        this.idGenerator = new TaskIdGenerator(this.takenIds);// generating taskID so that it is different from preexisting taskIDs
        this.writer = new ProvideData();
    }

    public String runTaskCreation(){

        ArrayList<Integer> takenTaskIdNums = idGenerator.takenNumList(CreateTaskUseCase.entityCode);
        // generate id from use case
        String taskID = idGenerator.generateEntityId(CreateTaskUseCase.entityCode, takenTaskIdNums);

        if (!this.takenIds.contains(taskID)){
            this.task.setTaskID(taskID);  // always true based on RaffleIdGenerator
            // update  takenIds
            this.takenIds.add(taskID);
            ArrayList<String> taskInfo = new ArrayList<>();
            taskInfo.add(this.task.getName());
            taskInfo.add(this.task.getLink());
            taskInfo.add(this.task.getDescription());

            this.writer.addDetailsOfTask(this.raffleId, taskInfo);
            return taskID;
        }
        return null;
    }

    public Task getTask(){
        return this.task;
    }

}
