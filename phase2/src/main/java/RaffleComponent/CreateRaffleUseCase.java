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

    private final OrganizerRaffleEntity raffle;
    private final RaffleIdGenerator idGenerator;
    private static final char entityCode = 'R';
    private ArrayList<String> takenIds;
    private CreationResult creationOutcome;
    private final PackageRaffleEntityInstance dataPackager;
    private DataExtractor dataAccess;
    private AddOrganizer dataUploader;
    private ArrayList<Object>  raffleInfoSoFar;
    private String generatedRaffleId;
    private final String orgUsername;


    public enum CreationResult {
        SUCCESS, FAILURE
    }

    /**
     * Constructor for the use case of having an organizer create a raffle
     * @param raffleName the string name attribute of the raffle being created
     * @param numOfWinners the number of winners to be awarded in the raffle as dictated by the organizer
     * @param endDate the day this raffle ends
     * @param orgUsername the username of the organizer creating this raffle
     */
    public CreateRaffleUseCase(String raffleName, int numOfWinners, LocalDate endDate, String orgUsername){
        this.raffle = new OrganizerRaffleEntity(raffleName, numOfWinners, endDate, orgUsername);

        try {
            this.dataAccess = new DataExtractor();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.dataUploader = new AddOrganizer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.takenIds = this.dataAccess.getUsedRaffleIDs();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.idGenerator = new RaffleIdGenerator(this.takenIds);
       this.dataPackager = new PackageRaffleEntityInstance();
        this.raffleInfoSoFar = new ArrayList<>();
        this.raffleInfoSoFar.add(raffleName);
        this.raffleInfoSoFar.add(numOfWinners);
        this.raffleInfoSoFar.add(endDate);
        this.raffleInfoSoFar.add(orgUsername);
        this.orgUsername = orgUsername;
    }

    /**
     * Runs the processes to create an OrganizerRaffle instance
     * @return the arraylist of object carrying the information to be passed to the next step in the raffle
     * creation process [name, numOfWinners, endDate, raffleId]
     */
    public ArrayList<Object> runRaffleCreation(){

        ArrayList<Integer> takenRaffleIdNums = idGenerator.takenNumList(CreateRaffleUseCase.entityCode);
        // generate id from use case
        this.generatedRaffleId = idGenerator.generateEntityId(CreateRaffleUseCase.entityCode, takenRaffleIdNums);

        if (!this.takenIds.contains(this.generatedRaffleId)){
            this.raffle.setRaffleId(this.generatedRaffleId);  // always true based on RaffleIdGenerator
            // update  takenIds
            this.takenIds.add(this.generatedRaffleId);
            this.raffleInfoSoFar.add(this.generatedRaffleId);

            ArrayList<Object> packagedOrgRaffle = this.dataPackager.packageOrganizerRaffle(this.raffle);
            try {
                this.dataUploader.uploadCreatedRaffle(this.orgUsername, this.generatedRaffleId, packagedOrgRaffle);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.creationOutcome = CreationResult.SUCCESS;
            return this.raffleInfoSoFar;
        }

        // this case is never really executed since the idGenerator makes sure there is no id repetition
        this.creationOutcome = CreationResult.FAILURE;
        return null;
    }

    public String getGeneratedRaffleId(){
        return this.generatedRaffleId;
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
