package main.java.RaffleComponent;

import main.java.Helpers.PackageRaffleEntityInstance;
import main.java.RaffleComponent.OrganizerRaffleEntity;
import main.java.database.AddOrganizer;
import main.java.database.DataExtractor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

// the use case for when a raffle organizer wants to decide the winners of a raffle
public class RaffleWinnerGeneratorUseCase {

    private ArrayList<Object> orgRaffleInfo;
    private OrganizerRaffleEntity orgRaffle;
//    private PackageRaffleEntityInstance dataPackager;
    private DataExtractor dataAccess;
//    private AddOrganizer dataUploader;

    public RaffleWinnerGeneratorUseCase(String raffleId) {

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
            this.orgRaffleInfo = this.dataAccess.getOrgRaffleInfo(raffleId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.orgRaffle = new OrganizerRaffleEntity((String)this.orgRaffleInfo.get(0),
                (Integer)this.orgRaffleInfo.get(1), (LocalDate)this.orgRaffleInfo.get(2),(String)this.orgRaffleInfo.get(3));
        this.orgRaffle.setRaffleId(raffleId);
        this.orgRaffle.setRaffleRules((String)this.orgRaffleInfo.get(2));
        this.orgRaffle.setTaskIdList((ArrayList<String>) this.orgRaffleInfo.get(4));
        this.orgRaffle.setParticipantIdList((ArrayList<String>) this.orgRaffleInfo.get(5));
        // no winners set yet

//        this.dataPackager = new PackageRaffleEntityInstance();

    }

    public ArrayList<String> updateRaffleWinners(){
        // for now any participant can be selected as a winner, phase2 this will be updated to only valid ones
        this.orgRaffle.setWinnerList(this.generateWinners());

        // winners not saved currently, just returned

//        ArrayList<Object> packagedOrgRaffle = this.dataPackager.packageOrganizerRaffle(this.orgRaffle);
//        // DataAccess.uploadModifiedOrgRaffle(this.orgRaffle.getRaffleId(), packagedOrgRaffle)
        return this.orgRaffle.getWinnerList();
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

    // for testing purposes only
    public void setOrgRaffle(OrganizerRaffleEntity orgRaffle){
        this.orgRaffle = orgRaffle;
    }

    public OrganizerRaffleEntity getOrgRaffle(){
        return this.orgRaffle;
    }
}
