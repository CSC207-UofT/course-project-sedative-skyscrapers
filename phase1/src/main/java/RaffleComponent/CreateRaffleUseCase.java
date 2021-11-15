package main.java.RaffleComponent;

import main.java.Helpers.PackageRaffleEntityInstance;
import main.java.Helpers.RaffleIdGenerator;
import main.java.database.AddOrganizer;
import main.java.database.DataExtractor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CreateRaffleUseCase {

    private OrganizerRaffleEntity raffle;
    private RaffleIdGenerator idGenerator;
    private static final char entityCode = 'R';
    private ArrayList<String> takenIds;
    private CreationResult creationOutcome;
//    private PackageRaffleEntityInstance dataPackager;
    private DataExtractor dataAccess;

//    private AddOrganizer dataUploader;


    public enum CreationResult {
        SUCCESS, FAILURE
    }

    public CreateRaffleUseCase(String raffleName, int numOfWinners, LocalDate endDate){
        this.raffle = new OrganizerRaffleEntity(raffleName, numOfWinners, endDate);

        try {
            this.dataAccess = new DataExtractor();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        try {
//            this.dataUploader = new AddOrganizer();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            this.takenIds = this.dataAccess.getUsedRaffleIDs();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.idGenerator = new RaffleIdGenerator(this.takenIds);
//        this.dataPackager = new PackageRaffleEntityInstance();
    }

    public String runRaffleCreation(){

        ArrayList<Integer> takenRaffleIdNums = idGenerator.takenNumList(CreateRaffleUseCase.entityCode);
        // generate id from use case
        String raffleId = idGenerator.generateEntityId(CreateRaffleUseCase.entityCode, takenRaffleIdNums);

        if (!this.takenIds.contains(raffleId)){
            this.raffle.setRaffleId(raffleId);  // always true based on RaffleIdGenerator
            // update  takenIds
            this.takenIds.add(raffleId);

//            ArrayList<Object> packagedOrgRaffle = this.dataPackager.packageOrganizerRaffle(this.raffle);
//
//            try {
//                this.dataUploader.uploadCreatedRaffle(this.raffle.getRaffleId(), packagedOrgRaffle);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            this.creationOutcome = CreationResult.SUCCESS;
            return raffleId;  // pure raffleId
        }

        // this case never really executes since the idGenerator makes sure there is no id repetition
        this.creationOutcome = CreationResult.FAILURE;
        return null;
    }

    // for testing purposes
    public RaffleEntity getRaffle() {
        return this.raffle;
    }

    public CreationResult getCreationOutcome() {
        return creationOutcome;
    }

    public void setTakenIds(ArrayList<String> takenIds) {
        this.takenIds = takenIds;
    }

}
