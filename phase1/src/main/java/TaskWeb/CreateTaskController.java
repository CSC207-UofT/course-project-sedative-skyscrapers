package main.java.TaskWeb;
import main.java.TaskComponent.CreateTaskUseCase;

public class CreateTaskController {

        // this controller is going to need to get the information from the database about the taken raffle ids
        // and the information from the user input all raffle details coming from the commandline or gui

        // todo private ArrayList<String> takenTaskIds;
        private final String taskName;
        private final String taskDescription;
        private final String taskLink;

        public CreateTaskController(String taskName, String taskDescription, String taskLink){
            // input from user
            this.taskName = taskName;
            this.taskDescription = taskDescription;
            this.taskLink = taskLink;
        }
    // todo make return new task id
        public String runCreateTask(){
            // create an instance of createRaffleUseCase with user input
            CreateTaskUseCase taskManager = new CreateTaskUseCase(this.taskName, this.taskDescription, this.taskLink);

            // call use case's run method to update the raffle's id
            return taskManager.runTaskCreation();

            // send raffleManager.getRaffle to DB through an {Id:OrganizerRaffleEntity mapping}
        }
}
