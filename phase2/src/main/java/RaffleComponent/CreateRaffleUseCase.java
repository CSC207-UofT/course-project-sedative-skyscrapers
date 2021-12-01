package main.java.RaffleComponent;

import main.java.Helpers.PackageRaffleEntityInstance;
import main.java.Helpers.EntityIdGenerator;
import main.java.database.AddOrganizer;
import main.java.database.DataExtractor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CreateRaffleUseCase {

    private final OrganizerRaffleEntity raffle;
    private final EntityIdGenerator idGenerator;
    private static final char entityCode = 'R';
    private ArrayList<String> takenIds;
    private final String orgUsername;
//    private boolean creationOutcome;
    private final PackageRaffleEntityInstance dataPackager;
    private DataAccessPoint dataAccess;
    private DataProviderPoint dataUploader;
//    private ArrayList<Object>  raffleInfoSoFar;
    private String generatedRaffleId;

// changed to true and false
//    public enum CreationResult {
//        SUCCESS, FAILURE
//    }

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
            // todo this will be the name of the file khushaal provides
            this.dataAccess = new AccessData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            // todo this will be the name of the file khushaal provides
            this.dataUploader = new ProvideData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.takenIds = this.dataAccess.getTakenRaffleIds();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.idGenerator = new EntityIdGenerator(this.takenIds);
        this.dataPackager = new PackageRaffleEntityInstance();
//        this.raffleInfoSoFar = new ArrayList<>();
//        this.raffleInfoSoFar.add(raffleName);
//        this.raffleInfoSoFar.add(numOfWinners);
//        this.raffleInfoSoFar.add(endDate);
//        this.raffleInfoSoFar.add(orgUsername);
        this.orgUsername = orgUsername;
    }

    /**
     * Runs the processes to create an OrganizerRaffle instance
     * @return the arraylist of object carrying the information to be passed to the next step in the raffle
     * creation process [name, numOfWinners, endDate, raffleId]
     */
    public boolean runRaffleCreation(){

        ArrayList<Integer> takenRaffleIdNums = idGenerator.takenNumList(CreateRaffleUseCase.entityCode);
        // generate id from use case
        this.generatedRaffleId = idGenerator.generateEntityId(CreateRaffleUseCase.entityCode, takenRaffleIdNums);

        if (!this.takenIds.contains(this.generatedRaffleId)){
            this.raffle.setRaffleId(this.generatedRaffleId);  // always true based on RaffleIdGenerator
            // update  takenIds
            this.takenIds.add(this.generatedRaffleId);
//            this.raffleInfoSoFar.add(this.generatedRaffleId);

            ArrayList<Object> packagedOrgRaffle = this.dataPackager.packageOrganizerRaffle(this.raffle);
            try {
                // todo revisit this uploading method with khushaal, what about the raffle Id?
                //  might change the packaging method to not include the empty arrayLists
                this.dataUploader.uploadCreatedRaffle(this.orgUsername, packagedOrgRaffle);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

        // this case is never really executed since the idGenerator makes sure there is no id repetition
        return false;
    }

    public String getGeneratedRaffleId(){
        return this.generatedRaffleId;
    }

    // for testing purposes
    public OrganizerRaffleEntity getRaffle() {
        return this.raffle;
    }

//    public CreationResult getCreationOutcome() {
//        return creationOutcome;
//    }

    public void setTakenIds(ArrayList<String> takenIds) {
        this.takenIds = takenIds;
    }

}
