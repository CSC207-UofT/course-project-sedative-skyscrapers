package main.java.RaffleWeb;

import main.java.RaffleComponent.RaffleWinnerGeneratorUseCase;

import java.util.ArrayList;
import java.util.HashMap;

public class RaffleWinnerGeneratorController {
    // this controller is going to need to get the information from the System about the organizer raffle
    // for which winners are to be calculated, no needed info from user (unless we extend program to look also have
    // weighted entries for each user)

    private final String raffleId;  // provided by System

    /* orgAllRaffles is a hashmap from raffleId to an array of objects that are contained in an orgRaffle object
    EG:
    key: "R1002"; corresponding value: [raffleName="raffle", numberOfWinners=2, rules="Age > 18",
        endDate=LocalDate.of(2021, 12, 25), taskIds=ArrayList<String>, ptcIds=ArrayList<String>,
        winnerIds=ArrayList<String>]
    ... and we get this hashmap for all existing raffles in the program (through a method in db)
    */

//    private HashMap<String, ArrayList<Object>> orgAllRaffles;  // provided by db

    /**
     * Constructor for the controller handling the RaffleWinnerGeneratorUseCase
     * @param id the id referring to the raffle whose winners are to be generated
     */
    public RaffleWinnerGeneratorController(String id) {
        this.raffleId = id;
    }

    /**
     * Manages the flow of control of the RaffleWinnerGenerator use case
     * @return the arraylist of ids referring to the winning participants of this raffle
     */
    public ArrayList<String> runRaffleWinnerGenerator() {
        // here raffleId IS in orgAllRaffles, since this class is only accessible within a raffle's subpage
        RaffleWinnerGeneratorUseCase raffleManager = new RaffleWinnerGeneratorUseCase(this.raffleId);
        return raffleManager.updateRaffleWinners();  // generate and store winners inside the use cases' raffle
        // print this returned arraylist of winners to the screen
    }

}

