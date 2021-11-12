package main.java.RaffleComponent;

import main.java.Helpers.RaffleIdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;

public class CreateRaffleUseCase {

    private OrganizerRaffleEntity raffle;
    private RaffleIdGenerator idGenerator;
    private static final char entityCode = 'R';
    private ArrayList<String> takenIds;

    public enum CreationResult {
        SUCCESS, FAILURE
    }

    public CreateRaffleUseCase(String raffleName, int numOfWinners, LocalDate endDate, ArrayList<String> takenIds){
        this.raffle = new OrganizerRaffleEntity(raffleName, numOfWinners, endDate);
        this.takenIds = takenIds;
        this.idGenerator = new RaffleIdGenerator(this.takenIds);
        // save the raffle object along with the raffleID in database
    }

//    public void StoreCreatedRaffle(){
//
//        // store in database
//    }

    public CreationResult runRaffleCreation(){
        ArrayList<Integer> takenRaffleIdNums = idGenerator.takenNumList(CreateRaffleUseCase.entityCode);
        // generate id from use case
        String raffleId = idGenerator.generateEntityId(CreateRaffleUseCase.entityCode, takenRaffleIdNums);

        if (!this.takenIds.contains(raffleId)){
            this.raffle.setRaffleId(raffleId);  // always true based on RaffleIdGenerator
            // update  takenIds
            this.takenIds.add(raffleId);
            return CreationResult.SUCCESS;
        }

        return CreationResult.FAILURE;

    }

    public RaffleEntity getRaffle() {
        return raffle;
    }
}
