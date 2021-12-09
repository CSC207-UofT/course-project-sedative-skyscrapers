package main.java.Web;
import main.java.TaskComponent.CreateTaskUseCase;

import java.io.IOException;
import java.sql.SQLException;

public class CreateTaskController {

        // this controller is going to need to get the information from the database about the taken raffle ids
        // and the information from the user input all raffle details coming from the commandline or gui

        // todo private ArrayList<String> takenTaskIds;
        private final String taskName;
        private final String taskDescription;
        private final String taskLink;
        private String raffleID;

        public CreateTaskController(String raffleID, String taskName, String taskDescription, String taskLink){
            this.taskName = taskName;
            this.taskDescription = taskDescription;
            this.taskLink = taskLink;
            this.raffleID = raffleID;
        }

        public String runCreateTask() throws SQLException {
            // create an instance of createRaffleUseCase with user input
            CreateTaskUseCase taskManager = new CreateTaskUseCase(this.raffleID, this.taskName, this.taskDescription, this.taskLink);

            // call use case's run method to update the raffle's id
            return taskManager.runTaskCreation();


        }
}
