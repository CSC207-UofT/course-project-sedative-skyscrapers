package main.java.RaffleComponent;

import main.java.RaffleComponent.OrganizerRaffleEntity;

import java.time.LocalDate;
import java.util.ArrayList;

// the use case for when a raffle organizer wants to decide the winners of a raffle
public class RaffleWinnerGeneratorUseCase {

//    private final ArrayList<String> participantIdList;
//    private final int numberOfWinners;
//    private final ArrayList<Object> orgRaffleInfo;
    private OrganizerRaffleEntity orgRaffle;

    public RaffleWinnerGeneratorUseCase(String raffleId, ArrayList<Object> orgRaffleDetails) {

//        this.orgRaffleInfo = orgRaffleDetails;
        this.orgRaffle = new OrganizerRaffleEntity((String)orgRaffleDetails.get(0), (Integer)orgRaffleDetails.get(1),
                (LocalDate)orgRaffleDetails.get(3));
        this.orgRaffle.setRaffleId(raffleId);
        this.orgRaffle.setParticipantIdList((ArrayList<String>)orgRaffleDetails.get(5));

    }

    public void updateRaffleWinners(){
        this.orgRaffle.setWinnerList(this.generateWinners());  // database updated
    }

    public ArrayList<String> generateWinners(){
        int i;
        ArrayList<String> winnersSoFar = new ArrayList<>();
        ArrayList<Integer> winningNumsSoFar = new ArrayList<>();

        for (i = 0; i < this.orgRaffle.getNumberOfWinners(); i++) {
            int winningEntry = calculateWinningEntry(winningNumsSoFar);
            winningNumsSoFar.add(winningEntry);
            winnersSoFar.add(this.orgRaffle.getParticipantIdList().get(winningEntry));  // winningEntry is the index
        }

        return winnersSoFar;  // returns arrayList of userId strings
    }

    public int calculateWinningEntry(ArrayList<Integer> winningNumsSoFar){

        int winningEntry = (int)(this.orgRaffle.getParticipantIdList().size() * Math.random());

        while (winningNumsSoFar.contains(winningEntry)){
            winningEntry = (int)(this.orgRaffle.getParticipantIdList().size() * Math.random());  // recalculate
        }

        return winningEntry;
    }
}
