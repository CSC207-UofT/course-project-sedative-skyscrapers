package main.java.RaffleComponent;

import main.java.DatabaseRe.AccessData;
import main.java.DatabaseRe.ProvideData;
import main.java.Helpers.UseCaseDateFormatter;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RaffleRuleSetterUseCase {
    private final String rulesString;
    private OrganizerRaffleEntity orgRaffle;
    private ArrayList<Object> orgRaffleInfo;
    private DataAccessPoint dataAccess;
    private DataProviderPoint dataUploader;

    /**
     * Constructor for the use case handling the event of an organizer setting the rules of a raffle
     * @param raffleId reference to the organizer raffle entity whose rules attribute is being overridden
     * @param rulesString the string of rules for the organizer raffle instance this.orgRaffle
     */
    public RaffleRuleSetterUseCase(String raffleId,String rulesString){
        this.rulesString = rulesString;

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
            this.orgRaffleInfo = this.dataAccess.getOrganizerRaffleById(raffleId);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        String date = this.orgRaffleInfo.get(3).toString();
        int[] dateData = UseCaseDateFormatter.formatDateIntoStrings(date);
        this.orgRaffle = new OrganizerRaffleEntity((String)this.orgRaffleInfo.get(0),
                Integer.parseInt(this.orgRaffleInfo.get(1).toString()),
                LocalDate.of(dateData[0], dateData[1], dateData[2]),
                (String)this.orgRaffleInfo.get(7));
        this.orgRaffle.setRaffleId(raffleId);
        // taskIdList, ptcIdList and winnerIdList empty at this stage

    }

    /**
     * Registers the rules onto the this.orgRaffle instance
     * @return the boolean representing whether this organizer raffle's rules were successfully set
     */
    public boolean updateRules(){
        this.orgRaffle.setRaffleRules(this.rulesString);
        this.dataUploader.updateRaffleRules(this.orgRaffle.getRaffleId(), this.orgRaffle.getRaffleRules());
        // any string (even the empty string is considered a valid set of rules, in case users don't need rules)
        return true;
    }


    // for testing purposes

    public OrganizerRaffleEntity getOrgRaffle(){
        return this.orgRaffle;
    }

    public void setOrgRaffle(OrganizerRaffleEntity orgRaffle){
        this.orgRaffle = orgRaffle;
    }
}
