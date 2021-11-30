package main.java.RaffleComponent;

import main.java.database.AddOrganizer;
import main.java.database.DataExtractor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RaffleEndDateModifierUseCase {

    private final String FIELD_TO_BE_CHANGED = "EndDate";
    private final LocalDate newEndDate;
    /* orgAllRaffles is a hashmap from raffleId to an array of objects that are contained in an orgRaffle object
    EG:
    key: "R1002"; corresponding value: [raffleName="raffle", numberOfWinners=2, rules="Age > 18",
        endDate=LocalDate.of(2021, 12, 25), taskIds=ArrayList<String>, ptcIds=ArrayList<String>,
        winnerIds=ArrayList<String>]
    ... and we get this hashmap for all existing raffles in the program (through a method in db)
    */
//    private ArrayList<Object> raffleInfoSoFar;
    private OrganizerRaffleEntity orgRaffle;
    private ArrayList<Object> orgRaffleInfo;
    //    private PackageRaffleEntityInstance dataPackager;
    private DataAccessPoint dataAccess;
    private DataProviderPoint dataUploader;

    /**
     * Constructor for the use case handling the event of an organizer setting the rules of a raffle
     * @param orgRaffleId reference to the organizer raffle entity whose rules attribute is being overridden
     * @param rulesString the string of rules for the organizer raffle instance this.orgRaffle
     * @param raffleInfoSoFar the arraylist of object carrying the information to be passed to the next step in the raffle
     *        creation process [name, numOfWinners, endDate, raffleId]
     */
    public RaffleEndDateModifierUseCase(String orgRaffleId, LocalDate newEndDate){
        this.newEndDate = newEndDate;
//        this.raffleInfoSoFar = raffleInfoSoFar;  // format [name, numOfWinners, endDate, raffleId]

        try {
            // todo this will be the name of the file khushaal provides
            this.dataAccess = new DataExtractor();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            // todo this will be the name of the file khushaal provides
            this.dataUploader = new AddOrganizer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.orgRaffleInfo = this.dataAccess.getOrganizerRaffleById(orgRaffleId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // todo: need the orgname to be returned by the database
        this.orgRaffle = new OrganizerRaffleEntity((String)this.orgRaffleInfo.get(0),
                (Integer)this.orgRaffleInfo.get(1), (LocalDate)this.orgRaffleInfo.get(3));
        this.orgRaffle.setRaffleId(orgRaffleId);
        // taskIdList, ptcIdList and winnerIdList empty at this stage

//        this.dataPackager = new PackageRaffleEntityInstance();

    }

    /**
     * Registers the rules onto the this.orgRaffle instance and passes it to the next step in the raffle
     * creation process through this.raffleInfoSoFar
     * @return the arraylist of object carrying the information to be passed to the next step in the raffle
     *      creation process [name, numOfWinners, endDate, raffleId, rules]
     */
    public void updateEndDate(){
        this.orgRaffle.setEndDate(this.newEndDate);
//        ArrayList<Object> packagedOrgRaffle = this.dataPackager.packageOrganizerRaffle(this.orgRaffle);
//        DataAccess.uploadModifiedOrgRaffle(this.orgRaffle.getRaffleId(), packagedOrgRaffle)
//        this.raffleInfoSoFar.add(this.rulesString); // format [name, numOfWinners, endDate, raffleId, rules]
//        return this.raffleInfoSoFar;
        this.dataUploader.uploadModifiedOrgRaffle(this.orgRaffle.getRaffleId(), this.FIELD_TO_BE_CHANGED,
                this.orgRaffle.getRaffleRules());

        this.updatePtcRaffles();
    }

    public void updatePtcRaffles(){
        for (String participant : this.orgRaffle.getParticipantIdList()){
            // create ptcRaffleObject
            String ptcRaffleId = participant + ":" + this.orgRaffle.getRaffleId();

            try {
                ArrayList<Object> ptcRaffleInfo = this.dataAccess.getParticipantRaffleById(ptcRaffleId);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                this.dataUploader.uploadModifiedPtcRaffle(ptcRaffleId, this.FIELD_TO_BE_CHANGED,
                        updatedEndDate(ptcRaffleInfo));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public LocalDate updatedEndDate(ArrayList<Object> ptcRaffleInfo) {

        ParticipantRaffleEntity ptcRaffleToUpdate = new ParticipantRaffleEntity(this.orgRaffle.getRaffleName(),
                this.orgRaffle.getNumberOfWinners(), this.orgRaffle.getEndDate());
        ptcRaffleToUpdate.setRaffleRules(this.orgRaffle.getRaffleRules());

        // now extract the specific arraylist of taskIds for the specific ptcRaffle instance
        ptcRaffleToUpdate.setTaskIdList((ArrayList<String>)ptcRaffleInfo.get(4));

        // update endDate for specific participant raffle through update method of subscriber
        ptcRaffleToUpdate.updateEndDate(this.newEndDate);

        return ptcRaffleToUpdate.getEndDate();

    }


    // for testing purposes

    public OrganizerRaffleEntity getOrgRaffle(){
        return this.orgRaffle;
    }

    public void setOrgRaffle(OrganizerRaffleEntity orgRaffle){
        this.orgRaffle = orgRaffle;
    }

}
