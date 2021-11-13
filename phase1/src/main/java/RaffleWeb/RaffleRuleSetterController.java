package main.java.RaffleWeb;

import main.java.RaffleComponent.RaffleRuleSetterUseCase;

import java.util.ArrayList;
import java.util.HashMap;

public class RaffleRuleSetterController {
    // rules MUST be set before publishing (sending to DB) a raffle object to be joined by participants
    // therefore, this controller MUST be called right after a raffle is created (before letting user wander off)

    private String raffleId;  // provided by System
    private String rulesString; // provided by user through text box

    /* orgAllRaffles is a hashmap from raffleId to an array of objects that are contained in an orgRaffle object
    EG:
    key: "R1002"; corresponding value: [raffleName="raffle", numberOfWinners=2, rules="Age > 18",
        endDate=LocalDate.of(2021, 12, 25), taskIds=ArrayList<String>, ptcIds=ArrayList<String>,
        winnerIds=ArrayList<String>]
    ... and we get this hashmap for all existing raffles in the program (through a method in db)
    */

//    private HashMap<String, ArrayList<Object>> orgAllRaffles;  // provided by db

    public RaffleRuleSetterController(String id, String rulesString, HashMap<String, ArrayList<Object>> dbOrgRaffles){
        this.raffleId = id;
        this.rulesString = rulesString;
//        this.orgAllRaffles = dbOrgRaffles;
    }

    public void runRaffleRuleSetter(){
        // here raffleId IS in orgAllRaffles, since this class is only accessible within a raffle's subpage
        RaffleRuleSetterUseCase raffleManager = new RaffleRuleSetterUseCase(this.raffleId, this.rulesString);
        raffleManager.updateRules(); // updates the rules in the raffleManager raffle
    }


}
