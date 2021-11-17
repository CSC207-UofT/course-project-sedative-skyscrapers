package main.java.RaffleWeb;

import main.java.RaffleComponent.CreateRaffleUseCase;

import java.time.LocalDate;
import java.util.ArrayList;

// will need to implement the interface in charge of sending and retrieving files from the database
public class CreateRaffleController {
    // this controller is going to need to get the information from the database about the taken raffle ids
    // and the information from the user input all raffle details coming from the commandline or gui


    private final String raffleName;
    private final int numberOfWinners;
    private final LocalDate endDate;
    public ArrayList<Object> raffleInfoSoFar;
    private String raffleId;
    private String username;


    public CreateRaffleController(String orgUsername, String raffleName, int numOfWinners, LocalDate endDate){

        this.username = orgUsername;
        this.raffleName = raffleName;
        this.numberOfWinners = numOfWinners;
        this.endDate = endDate;

    }

    public ArrayList<Object> runCreateRaffle(){
        // create an instance of createRaffleUseCase with user input
        CreateRaffleUseCase raffleManager = new CreateRaffleUseCase(this.raffleName, this.numberOfWinners,
                this.endDate, this.username);

        // call use case's run method to update the raffle's id
        this.raffleInfoSoFar = raffleManager.runRaffleCreation();  // returns [name, numOfWinners, endDate, raffleId]
        this.raffleId = raffleManager.getGeneratedRaffleId();
        return this.raffleInfoSoFar;
    }
}
