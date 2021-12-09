package main.java.RaffleComponent;

import main.java.DatabaseRe.AccessData;
import main.java.DatabaseRe.ProvideData;
import main.java.Helpers.UseCaseDateFormatter;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RaffleEndDateModifierUseCase {

    private final LocalDate newEndDate;
    private OrganizerRaffleEntity orgRaffle;
    private ArrayList<Object> orgRaffleInfo;
    private DataAccessPoint dataAccess;
    private DataProviderPoint dataUploader;

    /**
     * Constructor for the use case handling the event of an organizer updating the endDate of a raffle
     * @param orgRaffleId reference to the organizer raffle entity whose rules attribute is being overridden
     * @param newEndDate the new endDate to be given to both the organizer raffle and related ptcRaffles
     */
    public RaffleEndDateModifierUseCase(String orgRaffleId, LocalDate newEndDate){
        this.newEndDate = newEndDate;

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
        this.orgRaffle = new OrganizerRaffleEntity((String)this.orgRaffleInfo.get(0),
                (Integer)this.orgRaffleInfo.get(1), LocalDate.of(dateData[0], dateData[1], dateData[2]),
                (String)this.orgRaffleInfo.get(6));
        this.orgRaffle.setRaffleId(orgRaffleId);
    }

    /**
     * Registers the new endDate onto the this.orgRaffle instance
     * @return the bool representing whether the endDate update was successful
     */
    public boolean updateEndDate(){
        this.orgRaffle.setEndDate(this.newEndDate);
        this.dataUploader.changeRaffleEndDate(this.orgRaffle.getRaffleId(), this.orgRaffle.getEndDate());

        this.updatePtcRaffles();
        return true;
    }

    public void updatePtcRaffles(){
        for (String participant : this.orgRaffle.getParticipantIdList()){
            // create ptcRaffleObject
            String ptcRaffleId = participant + ":" + this.orgRaffle.getRaffleId();

            ArrayList<Object> ptcRaffleInfo = null;
            try {
                ptcRaffleInfo = this.dataAccess.getParticipantRaffleById(ptcRaffleId);
            } catch (SQLException | ParseException e) {
                e.printStackTrace();
            }
            if (ptcRaffleInfo != null) {
                this.dataUploader.changeRaffleEndDate(ptcRaffleId, updatedEndDate(ptcRaffleInfo));
            }
        }
    }


    private LocalDate updatedEndDate(ArrayList<Object> ptcRaffleInfo) {

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
