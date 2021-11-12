package main.java.RaffleComponent;

import main.java.RaffleComponent.OrganizerRaffleEntity;

import java.time.LocalDate;
import java.util.ArrayList;

public class RaffleRuleSetterUseCase {

    private String rulesString;
    /* orgAllRaffles is a hashmap from raffleId to an array of objects that are contained in an orgRaffle object
    EG:
    key: "R1002"; corresponding value: [raffleName="raffle", numberOfWinners=2, rules="Age > 18",
        endDate=LocalDate.of(2021, 12, 25), taskIds=ArrayList<String>, ptcIds=ArrayList<String>,
        winnerIds=ArrayList<String>]
    ... and we get this hashmap for all existing raffles in the program (through a method in db)
    */
    private OrganizerRaffleEntity orgRaffle;

    public RaffleRuleSetterUseCase(String raffleId, String rulesString, ArrayList<Object> orgRaffleDetails){
        this.rulesString = rulesString;
        this.orgRaffle = new OrganizerRaffleEntity((String)orgRaffleDetails.get(0), (Integer)orgRaffleDetails.get(1),
                (LocalDate)orgRaffleDetails.get(3));
        this.orgRaffle.setRaffleId(raffleId);
    }

    public void updateRules(){
        this.orgRaffle.setRaffleRules(this.rulesString);
    }
}
