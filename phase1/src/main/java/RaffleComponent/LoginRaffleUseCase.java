package main.java.RaffleComponent;

import main.java.database.DataExtractor;
import main.java.database.JoinUserToRaffle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LoginRaffleUseCase {

    private final String orgRaffleId;
    private final String ptcLoggingInId;
    private ArrayList<Object> orgRaffleInfo;
    private RaffleEntity ptcRaffle;
    private OrganizerRaffleEntity orgRaffle;
    private LoginResult loginResult;
//    private PackageRaffleEntityInstance dataPackager;
    private DataExtractor dataAccess;
    private JoinUserToRaffle dataUploader;


    public enum LoginResult {
        SUCCESS, RAFFLE_ID_NOT_RECOGNIZED
    }

    /**
     * Constructor for the use case handling the event of a participant joining a raffle
     * @param orgRaffleId the id of the organizer raffle object to link this participant raffle object to
     * @param ptcId the id of the participant who is to join the raffle represented by orgRaffleId
     */
    public LoginRaffleUseCase(String orgRaffleId, String ptcId) {
        this.orgRaffleId = orgRaffleId;  // pureRaffleId provided by user
        this.ptcLoggingInId = ptcId;  // provided by system

        try {
            this.dataAccess = new DataExtractor();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.dataUploader = new JoinUserToRaffle();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.orgRaffleInfo = this.dataAccess.getOrgRaffleInfo(orgRaffleId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.orgRaffle = new OrganizerRaffleEntity((String)this.orgRaffleInfo.get(0),
                 Integer.parseInt(this.orgRaffleInfo.get(1).toString()),  LocalDate.parse(this.orgRaffleInfo.get(2).toString(),dtf),(String)this.orgRaffleInfo.get(3));
        this.orgRaffle.setRaffleId(orgRaffleId);
        this.orgRaffle.setRaffleRules((String)this.orgRaffleInfo.get(2));
        this.orgRaffle.setTaskIdList((ArrayList<String>)this.orgRaffleInfo.get(4));
        this.orgRaffle.setParticipantIdList((ArrayList<String>)this.orgRaffleInfo.get(5));
        // winnerList automatically set by constructor to empty, as it should stay while participants can log in

//        this.dataPackager = new PackageRaffleEntityInstance();

    }

    /**
     * Executes the processes to create a RaffleEntity instance based on the information of the orgRaffle
     * to which we are attaching this participant raffle
     * @return the newly generated participant raffle id to describe this participant raffle
     */
    public String runRaffleLogin() {

        if (this.orgRaffleInfo == null){
            // no raffle object corresponding to such id in the database
            this.loginResult = LoginResult.RAFFLE_ID_NOT_RECOGNIZED;
        } else {
            // copy items from array of raffle attributes to a RaffleEntity accessible by this participant
            this.ptcRaffle = new RaffleEntity(this.orgRaffle.getRaffleName(), this.orgRaffle.getNumberOfWinners(),
                    this.orgRaffle.getEndDate());
            this.ptcRaffle.setRaffleId(this.generatePtcRaffleId());
            this.ptcRaffle.setRaffleRules(this.orgRaffle.getRaffleRules());
            this.ptcRaffle.setTaskIdList(this.orgRaffle.getTaskIdList());

            // add this participant to the raffleToCopy participantIdList
            // this makes us depend on two classes (not single responsibility), but the entity is a single one
            this.orgRaffle.getParticipantIdList().add(this.ptcLoggingInId);

//            ArrayList<Object> packagedPtcRaffle = this.dataPackager.packageParticipantRaffle(this.ptcRaffle);


            this.loginResult = LoginResult.SUCCESS;
            return this.ptcRaffle.getRaffleId();
        }

        return null;
    }

    /**
     * Generates a participant raffle id based on an organizer raffle id
     * @return the newly generated ptc raffle id
     */
    public String generatePtcRaffleId(){
        return this.ptcLoggingInId + ":" + this.orgRaffleId;  // orgRaffleId is pure
    }

    public LoginResult getLoginResult() {
        return loginResult;
    }

    // for testing purposes

    public void setOrgRaffleInfo(ArrayList<Object> orgRaffleInfo) {
        this.orgRaffleInfo = orgRaffleInfo;
    }

    public RaffleEntity getRaffle() {
        return ptcRaffle;
    }

}

