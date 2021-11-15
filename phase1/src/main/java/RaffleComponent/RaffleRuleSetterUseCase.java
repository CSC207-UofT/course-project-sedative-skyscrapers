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

    private String rulesString;
    /* orgAllRaffles is a hashmap from raffleId to an array of objects that are contained in an orgRaffle object
    EG:
    key: "R1002"; corresponding value: [raffleName="raffle", numberOfWinners=2, rules="Age > 18",
        endDate=LocalDate.of(2021, 12, 25), taskIds=ArrayList<String>, ptcIds=ArrayList<String>,
        winnerIds=ArrayList<String>]
    ... and we get this hashmap for all existing raffles in the program (through a method in db)
    */
    private ArrayList<Object> orgRaffleInfo;
    private OrganizerRaffleEntity orgRaffle;
    private PackageRaffleEntityInstance dataPackager;
//    private DataExtractor dataAccess;
//    private AddOrganizer dataUploader;

    public RaffleRuleSetterUseCase(String raffleId, String rulesString){
        this.rulesString = rulesString;

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

        this.orgRaffle = new OrganizerRaffleEntity((String)this.orgRaffleInfo.get(0),
                (Integer)this.orgRaffleInfo.get(1), (LocalDate)this.orgRaffleInfo.get(3));
        this.orgRaffle.setRaffleId(raffleId);
        this.orgRaffle.setRaffleRules((String)this.orgRaffleInfo.get(2));
        // taskIdList, ptcIdList and winnerIdList empty at this stage

        this.dataPackager = new PackageRaffleEntityInstance();

    }

    public void updateRules(){
        this.orgRaffle.setRaffleRules(this.rulesString);
        ArrayList<Object> packagedOrgRaffle = this.dataPackager.packageOrganizerRaffle(this.orgRaffle);
        // todo uncomment: DataAccess.uploadModifiedOrgRaffle(this.orgRaffle.getRaffleId(), packagedOrgRaffle)
    }

    // for testing purposes
    public OrganizerRaffleEntity getOrgRaffle(){
        return this.orgRaffle;
    }

    public void setOrgRaffle(OrganizerRaffleEntity orgRaffle){
        this.orgRaffle = orgRaffle;
    }
}
