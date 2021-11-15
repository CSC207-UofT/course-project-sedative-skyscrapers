package main.java.RaffleWeb;

import main.java.RaffleComponent.CreateRaffleUseCase;

import java.time.LocalDate;
import java.util.ArrayList;

// will need to implement the interface in charge of sending and retrieving files from the database
public class CreateRaffleController {
    // this controller is going to need to get the information from the database about the taken raffle ids
    // and the information from the user input all raffle details coming from the commandline or gui


    private String raffleName;
    private int numberOfWinners;
    private LocalDate endDate;
    public ArrayList<Object> raffleInfoSoFar;


    public CreateRaffleController(String raffleName, int numOfWinners, LocalDate endDate){

        this.raffleInfoSoFar = new ArrayList<>();
        this.raffleInfoSoFar.add(raffleName);
        this.raffleInfoSoFar.add(numOfWinners);
        this.raffleInfoSoFar.add(null);
        this.raffleInfoSoFar.add(endDate);

        // input from user
        this.raffleName = raffleName;
        this.numberOfWinners = numOfWinners;
        this.endDate = endDate;
//        this.orgUsername = orgUsername;
    }
    // todo make return new raffle id

    public String runCreateRaffle(){
        // create an instance of createRaffleUseCase with user input
        CreateRaffleUseCase raffleManager = new CreateRaffleUseCase(this.raffleName, this.numberOfWinners,
                this.endDate);

        // call use case's run method to update the raffle's id
        return raffleManager.runRaffleCreation();  // returns pure raffleId for organizerRaffle

    }
}
