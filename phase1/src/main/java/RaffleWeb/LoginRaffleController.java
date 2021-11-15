package main.java.RaffleWeb;

import main.java.RaffleComponent.LoginRaffleUseCase;

public class LoginRaffleController {
    // this controller is going to need to get the information from the database about the raffleIdList
    // and the information from the user input which is the specific id for the raffle to log into

    private final String orgRaffleId;  // provided by user
    private final String ptcLogginInId;  // from system

    /* orgAllRaffles is a hashmap from raffleId to an array of objects that are contained in an orgRaffle object
    EG:
    key: "R1002"; corresponding value: [raffleName="raffle", numberOfWinners=2, rules="Age > 18",
        endDate=LocalDate.of(2021, 12, 25), taskIds=ArrayList<String>, ptcIds=ArrayList<String>,
        winnerIds=ArrayList<String>]
    ... and we get this hashmap for all existing raffles in the program (through a method in db)
    */

//    private HashMap<String, ArrayList<Object>> orgAllRaffles;


    public LoginRaffleController(String orgRaffleId, String ptcId){
        this.orgRaffleId = orgRaffleId;  // user input
        this.ptcLogginInId = ptcId;
//        this.orgAllRaffles = dbOrgRaffles;  // input from db
    }

    public String runLoginRaffle(){

        // if raffleId is valid, then it is passed onto the use case, otherwise, use case takes care of null input
        LoginRaffleUseCase raffleManager = new LoginRaffleUseCase(this.orgRaffleId, this.ptcLogginInId);
        return raffleManager.runRaffleLogin();

    }

}
