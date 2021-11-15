package main.java.TaskComponent;
import main.java.Helpers.TaskIdGenerator;
import main.java.database.AddOrganizer;
import main.java.database.DataExtractor;
import main.java.database.GetTaskDetails;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CreateTaskUseCase {

    private Task task;
    private TaskIdGenerator idGenerator;
    private static final char entityCode = 'T';
    private ArrayList<String> takenIds;
    private GetTaskDetails extractor;
    private AddOrganizer writer;

    public CreateTaskUseCase(String name, String description, String link) throws IOException {
        this.task = new Task(name, description, link);
        try {
            this.extractor = new GetTaskDetails();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.takenIds = this.extractor.getUsedTaskIDs();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.idGenerator = new TaskIdGenerator(this.takenIds);
        this.writer = new AddOrganizer();
    }

    public String runTaskCreation(){

        ArrayList<Integer> takenTaskIdNums = idGenerator.takenNumList(CreateTaskUseCase.entityCode);
        // generate id from use case
        String taskID = idGenerator.generateEntityId(CreateTaskUseCase.entityCode, takenTaskIdNums);

        if (!this.takenIds.contains(taskID)){
            this.task.setTaskID(taskID);  // always true based on RaffleIdGenerator
            // update  takenIds
            this.takenIds.add(taskID);

            try {
                this.writer.uploadCreatedTask(this.task.getTaskID(),
                        this.task.getName(), this.task.getLink(), this.task.getDescription());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return taskID;
        }
        return null;
    }

    public Task getTask(){
        return this.task;
    }

}
