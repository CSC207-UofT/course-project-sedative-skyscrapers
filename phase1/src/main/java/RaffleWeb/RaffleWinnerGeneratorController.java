package main.java.RaffleWeb;

import main.java.RaffleComponent.RaffleWinnerGeneratorUseCase;

import java.util.ArrayList;
import java.util.HashMap;

public class RaffleWinnerGeneratorController {
    // this controller is going to need to get the information from the System about the organizer raffle
    // for which winners are to be calculated, no needed info from user (unless we extend program to look also have
    // weighted entries for each user)

    private String raffleId;  // provided by System

    /* orgAllRaffles is a hashmap from raffleId to an array of objects that are contained in an orgRaffle object
    EG:
    key: "R1002"; corresponding value: [raffleName="raffle", numberOfWinners=2, rules="Age > 18",
        endDate=LocalDate.of(2021, 12, 25), taskIds=ArrayList<String>, ptcIds=ArrayList<String>,
        winnerIds=ArrayList<String>]
    ... and we get this hashmap for all existing raffles in the program (through a method in db)
    */

    private HashMap<String, ArrayList<Object>> orgAllRaffles;  // provided by db


    public RaffleWinnerGeneratorController(String id, HashMap<String, ArrayList<Object>> dbOrgRaffles) {
        this.raffleId = id;
        this.orgAllRaffles = dbOrgRaffles;
    }

    public void runRaffleWinnerGenerator() {
        // here raffleId IS in orgAllRaffles, since this class is only accessible within a raffle's subpage
        RaffleWinnerGeneratorUseCase raffleManager = new RaffleWinnerGeneratorUseCase(this.raffleId,
                this.orgAllRaffles.get(this.raffleId));
        raffleManager.updateRaffleWinners();  // generate and store winners inside the use cases' raffle

        // todo
        // store these changes to the OrganizerRaffle object in the database
    }

//    public ArrayList<Object> getRaffleInfo(){
//
//        ArrayList<Object> result = null;
//
//        for (String idKey: this.orgAllRaffles.keySet()){
//            if (idKey.equals(this.raffleId)){
//                result = this.orgAllRaffles.get(this.raffleId);
//            }
//        }
//
//        // if result stays as null, this case is handled in the use case
//        return result;
//    }
}

