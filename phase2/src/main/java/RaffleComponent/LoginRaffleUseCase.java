package main.java.RaffleComponent;

import main.java.DatabaseRe.AccessData;
import main.java.DatabaseRe.ProvideData;
import main.java.Helpers.PackageRaffleEntityInstance;
import main.java.database.DataExtractor;
import main.java.database.JoinUserToRaffle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LoginRaffleUseCase {

    private final String orgRaffleId;
    private final String ptcLoggingInId;
    private ArrayList<Object> orgRaffleInfo;
    private ParticipantRaffleEntity ptcRaffle;
    private final OrganizerRaffleEntity orgRaffle;
//    private LoginResult loginResult;
//    private final PackageRaffleEntityInstance dataPackager;
    private DataAccessPoint dataAccess;
    private DataProviderPoint dataUploader;
//    private JoinUserToRaffle dataUploader;


//    public enum LoginResult {
//        SUCCESS, RAFFLE_ID_NOT_RECOGNIZED
//    }

    /**
     * Constructor for the use case handling the event of a participant joining a raffle
     * @param orgRaffleId the id of the organizer raffle object to link this participant raffle object to
     * @param ptcId the id of the participant who is to join the raffle represented by orgRaffleId
     */
    public LoginRaffleUseCase(String orgRaffleId, String ptcId) {
        this.orgRaffleId = orgRaffleId;  // pureRaffleId provided by user
        this.ptcLoggingInId = ptcId;  // provided by system

        try {
            this.dataAccess = new AccessData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            this.dataUploader = new ProvideData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            this.orgRaffleInfo = this.dataAccess.getOrganizerRaffleById(orgRaffleId);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
//        try {
//            this.orgRaffleInfo = this.dataAccess.getOrganizerRaffleById(orgRaffleId);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        this.dataPackager = new PackageRaffleEntityInstance();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = this.orgRaffleInfo.get(3).toString();
        System.out.println(date.substring(8,10) + date.substring(4, 7) + date.substring(24, 28));
        int day = Integer.parseInt(date.substring(8, 10));
        int month = convertMonthToInt(date.substring(4, 7));
        int year = Integer.parseInt(date.substring(24, 28));
        System.out.println(LocalDate.of(year, month , day));
        this.orgRaffle = new OrganizerRaffleEntity((String)this.orgRaffleInfo.get(0),
                Integer.parseInt(this.orgRaffleInfo.get(1).toString()),
                LocalDate.of(year, month, day),
                (String)this.orgRaffleInfo.get(7));
        this.orgRaffle.setRaffleId(orgRaffleId);
        this.orgRaffle.setRaffleRules((String)this.orgRaffleInfo.get(2));
        this.orgRaffle.setTaskIdList((ArrayList<String>)this.orgRaffleInfo.get(4));
        this.orgRaffle.setParticipantIdList((ArrayList<String>)this.orgRaffleInfo.get(5));
        // winnerList automatically set by constructor to empty, as it should stay while participants can log in

//        this.dataPackager = new PackageRaffleEntityInstance();

    }

    public int convertMonthToInt(String month){
        switch(month){
            case "Jan":
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "May":
                return 5;
            case "Jun":
                return 6;
            case "Jul":
                return 7;
            case "Aug":
                return 8;
            case "Sep":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            case "Dec":
                return 12;
            default:
                return 12;
        }
    }

    /**
     * Executes the processes to create a RaffleEntity instance based on the information of the orgRaffle
     * to which we are attaching this participant raffle
     * @return the newly generated participant raffle id to describe this participant raffle
     */
    public boolean runRaffleLogin() {

        if (this.orgRaffleInfo != null){
            // copy items from array of raffle attributes to a RaffleEntity accessible by this participant
            this.ptcRaffle = new ParticipantRaffleEntity(this.orgRaffle.getRaffleName(), this.orgRaffle.getNumberOfWinners(),
                    this.orgRaffle.getEndDate());
            this.ptcRaffle.setRaffleId(this.generatePtcRaffleId());
            this.ptcRaffle.setRaffleRules(this.orgRaffle.getRaffleRules());
            this.ptcRaffle.setTaskIdList(this.orgRaffle.getTaskIdList());

            // add this participant to the raffleToCopy participantIdList
            // this makes us depend on two classes (not single responsibility), but the entity is a single one
            // the adding step of the observer pattern
            this.orgRaffle.getParticipantIdList().add(this.ptcLoggingInId);

//            ArrayList<Object> packagedPtcRaffle = this.dataPackager.packageParticipantRaffle(this.ptcRaffle);
            System.out.print(this.ptcLoggingInId);
            this.dataUploader.addRaffleIDtoParticipant(this.ptcLoggingInId, this.ptcRaffle.getRaffleId());

//            try {
//                this.dataUploader.uploadModifiedRaffle(this.orgRaffle.getRaffleId(), this.FIELD_TO_BE_CHANGED,
//                        this.orgRaffle.getParticipantIdList());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            return true;
        }

        return false;
    }

    /**
     * Generates a participant raffle id based on an organizer raffle id
     * @return the newly generated ptc raffle id
     */
    public String generatePtcRaffleId(){
        return this.ptcLoggingInId + ":" + this.orgRaffleId;  // orgRaffleId is pure
    }

//    public LoginResult getLoginResult() {
//        return loginResult;
//    }

    // for testing purposes

    public void setOrgRaffleInfo(ArrayList<Object> orgRaffleInfo) {
        this.orgRaffleInfo = orgRaffleInfo;
    }

    public ParticipantRaffleEntity getPtcRaffle() {
        return ptcRaffle;
    }

}

