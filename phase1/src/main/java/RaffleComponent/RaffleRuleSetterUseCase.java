package main.java.RaffleComponent;

import main.java.Helpers.PackageRaffleEntityInstance;
import main.java.RaffleComponent.OrganizerRaffleEntity;
import main.java.database.AddOrganizer;
import main.java.database.DataExtractor;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RaffleRuleSetterUseCase {

    private final String rulesString;
    /* orgAllRaffles is a hashmap from raffleId to an array of objects that are contained in an orgRaffle object
    EG:
    key: "R1002"; corresponding value: [raffleName="raffle", numberOfWinners=2, rules="Age > 18",
        endDate=LocalDate.of(2021, 12, 25), taskIds=ArrayList<String>, ptcIds=ArrayList<String>,
        winnerIds=ArrayList<String>]
    ... and we get this hashmap for all existing raffles in the program (through a method in db)
    */
    private ArrayList<Object> raffleInfoSoFar;
    private OrganizerRaffleEntity orgRaffle;
//    private PackageRaffleEntityInstance dataPackager;
//    private DataExtractor dataAccess;
//    private AddOrganizer dataUploader;

    public RaffleRuleSetterUseCase(String raffleId,String rulesString, ArrayList<Object> raffleInfoSoFar){
        this.rulesString = rulesString;
        this.raffleInfoSoFar = raffleInfoSoFar;  // format [name, numOfWinners, endDate, raffleId]

//        try {
//            this.dataAccess = new DataExtractor();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            this.dataUploader = new AddOrganizer();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            this.orgRaffleInfo = this.dataAccess.getOrgRaffleInfo(raffleId);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



        this.orgRaffle = new OrganizerRaffleEntity((String)this.raffleInfoSoFar.get(0),
                (Integer)this.raffleInfoSoFar.get(1), (LocalDate)this.raffleInfoSoFar.get(2),(String)this.raffleInfoSoFar.get(3));
        this.orgRaffle.setRaffleId(raffleId);
        // taskIdList, ptcIdList and winnerIdList empty at this stage

//        this.dataPackager = new PackageRaffleEntityInstance();

    }

    public ArrayList<Object> updateRules(){
        this.orgRaffle.setRaffleRules(this.rulesString);
//        ArrayList<Object> packagedOrgRaffle = this.dataPackager.packageOrganizerRaffle(this.orgRaffle);
//        DataAccess.uploadModifiedOrgRaffle(this.orgRaffle.getRaffleId(), packagedOrgRaffle)
        this.raffleInfoSoFar.add(this.rulesString); // format [name, numOfWinners, endDate, raffleId, rules]
        return this.raffleInfoSoFar;
    }

    // for testing purposes
    public OrganizerRaffleEntity getOrgRaffle(){
        return this.orgRaffle;
    }

    public void setOrgRaffle(OrganizerRaffleEntity orgRaffle){
        this.orgRaffle = orgRaffle;
    }
}
