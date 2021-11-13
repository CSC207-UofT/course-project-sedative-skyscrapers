package main.java;
import main.java.CreateTaskUseCase;

import java.time.LocalDate;
import java.util.ArrayList;

public class CreateTaskController {

        // this controller is going to need to get the information from the database about the taken raffle ids
        // and the information from the user input all raffle details coming from the commandline or gui

        //private ArrayList<String> takenRaffleIds;
        private final String taskName;
        private final String taskDescription;
        private final String taskLink;

        public CreateTaskController(String taskName, String taskDescription, String taskLink){
            // input from user
            this.taskName = taskName;
            this.taskDescription = taskDescription;
            this.taskLink = taskLink;
        }
        public void runCreateTask(){
            // create an instance of createRaffleUseCase with user input
            CreateTaskUseCase taskManager = new CreateTaskUseCase(this.taskName, this.taskDescription, this.taskLink);

            // call use case's run method to update the raffle's id
            //taskManager.runtaskCreation();

            // send raffleManager.getRaffle to DB through an {Id:OrganizerRaffleEntity mapping}
        }
    }
}
