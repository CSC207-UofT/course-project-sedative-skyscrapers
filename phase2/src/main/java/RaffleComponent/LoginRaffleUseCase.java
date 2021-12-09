package main.java.RaffleComponent;

import main.java.DatabaseRe.AccessData;
import main.java.DatabaseRe.ProvideData;
import main.java.Helpers.UseCaseDateFormatter;
import main.java.DatabaseRe.DataAccessPoint;
import main.java.DatabaseRe.DataProviderPoint;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class LoginRaffleUseCase {

    private final String orgRaffleId;
    private final String ptcLoggingInId;
    private ArrayList<Object> orgRaffleInfo;
    private ParticipantRaffleEntity ptcRaffle;
    private final OrganizerRaffleEntity orgRaffle;
    private DataAccessPoint dataAccess;
    private DataProviderPoint dataUploader;

    /**
     * Constructor for the use case handling the event of a participant joining a raffle
     *
     * @param orgRaffleId the id of the organizer raffle object to link this participant raffle object to
     * @param ptcId       the id of the participant who is to join the raffle represented by orgRaffleId
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

        String date = this.orgRaffleInfo.get(3).toString();
        int[] dateData = UseCaseDateFormatter.formatDateIntoStrings(date);
        this.orgRaffle = new OrganizerRaffleEntity((String) this.orgRaffleInfo.get(0),
                Integer.parseInt(this.orgRaffleInfo.get(1).toString()),
                LocalDate.of(dateData[0], dateData[1], dateData[2]),
                (String) this.orgRaffleInfo.get(7));
        this.orgRaffle.setRaffleId(orgRaffleId);
        this.orgRaffle.setRaffleRules((String) this.orgRaffleInfo.get(2));
        this.orgRaffle.setTaskIdList((ArrayList<String>) this.orgRaffleInfo.get(4));
        this.orgRaffle.setParticipantIdList((ArrayList<String>) this.orgRaffleInfo.get(5));

    }

    /**
     * Executes the processes to create a RaffleEntity instance based on the information of the orgRaffle
     * to which we are attaching this participant raffle
     *
     * @return the bool representing whether the id was successful, while updating this.ptcRaffle's id
     */
    public boolean runRaffleLogin() {

        if (this.orgRaffleInfo != null) {
            // copy items from array of raffle attributes to a RaffleEntity accessible by this participant
            this.ptcRaffle = new ParticipantRaffleEntity(this.orgRaffle.getRaffleName(), this.orgRaffle.getNumberOfWinners(),
                    this.orgRaffle.getEndDate());
            this.ptcRaffle.setRaffleId(this.generatePtcRaffleId());
            this.ptcRaffle.setRaffleRules(this.orgRaffle.getRaffleRules());
            this.ptcRaffle.setTaskIdList(this.orgRaffle.getTaskIdList());

            this.orgRaffle.getParticipantIdList().add(this.ptcLoggingInId);

            return true;
        }

        return false;
    }

    /**
     * Generates a participant raffle id based on an organizer raffle id
     *
     * @return the newly generated ptc raffle id
     */
    private String generatePtcRaffleId() {
        return this.ptcLoggingInId + ":" + this.orgRaffleId;  // orgRaffleId is pure
    }

    // for testing purposes

    public ParticipantRaffleEntity getPtcRaffle() {
        return ptcRaffle;
    }

}

