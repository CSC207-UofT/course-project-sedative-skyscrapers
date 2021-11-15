package main.java.TaskComponent;
import main.java.Helpers.TaskIdGenerator;
import java.util.ArrayList;

public class CreateTaskUseCase {

    private Task task;
    private TaskIdGenerator idGenerator;
    private static final char entityCode = 'T';
    private ArrayList<String> takenIds;

    public CreateTaskUseCase(String name, String description, String link) {
        this.task = new Task(name, description, link);
        // todo uncomment this when implemented: this.takenIds = DataAccess.getTakenTaskIds();
        this.idGenerator = new TaskIdGenerator(this.takenIds);
        // todo: save the raffle object along with the raffleID in database
    }

    public String runTaskCreation(){

        ArrayList<Integer> takenTaskIdNums = idGenerator.takenNumList(CreateTaskUseCase.entityCode);
        // generate id from use case
        String taskID = idGenerator.generateEntityId(CreateTaskUseCase.entityCode, takenTaskIdNums);

        if (!this.takenIds.contains(taskID)){
            this.task.setTaskID(taskID);  // always true based on RaffleIdGenerator
            // update  takenIds
            this.takenIds.add(taskID);
            // todo uncomment: DataAccess.uploadCreatedTask(takenIds, this.)task
            return taskID;
        }
        return null;
    }

    public Task getTask(){
        return this.task;
    }

}
