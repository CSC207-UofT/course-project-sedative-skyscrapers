package main.java.RaffleWeb;

import main.java.RaffleComponent.CreateRaffleUseCase;

import java.time.LocalDate;
import java.util.ArrayList;

// will need to implement the interface in charge of sending and retrieving files from the database
public class CreateRaffleController {
    // this controller is going to need to get the information from the database about the taken raffle ids
    // and the information from the user input all raffle details coming from the commandline or gui

    private ArrayList<String> takenRaffleIds;
    private String raffleName;
    private int numberOfWinners;
    private LocalDate endDate;

    public CreateRaffleController(ArrayList<String> takenIds, String raffleName, int numOfWinners, LocalDate endDate){
        this.takenRaffleIds = takenIds;  // input from DB

        // input from user
        this.raffleName = raffleName;
        this.numberOfWinners = numOfWinners;
        this.endDate = endDate;
    }
    // todo make return new raffle id
    public void runCreateRaffle(){
        // create an instance of createRaffleUseCase with user input
        CreateRaffleUseCase raffleManager = new CreateRaffleUseCase(this.raffleName, this.numberOfWinners,
                this.endDate, this.takenRaffleIds);

        // call use case's run method to update the raffle's id
        raffleManager.runRaffleCreation();

        // send raffleManager.getRaffle to DB through an {Id:OrganizerRaffleEntity mapping}
    }
}
