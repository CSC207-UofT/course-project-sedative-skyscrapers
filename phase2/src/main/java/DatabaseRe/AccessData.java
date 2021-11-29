package main.java.DatabaseRe;

import main.java.DatabaseRe.Mediators.Getters.RaffleGetter;
import main.java.DatabaseRe.Mediators.Getters.TaskGetter;
import main.java.DatabaseRe.Mediators.Getters.UserGetter;
import main.java.RaffleComponent.DataAccessPoint;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class AccessData implements DataAccessPoint {
    TaskGetter taskGetter = new TaskGetter();
    UserGetter userGetter = new UserGetter();
    RaffleGetter raffleGetter = new RaffleGetter();

    public AccessData() throws SQLException {
    }

    @Override
    public ArrayList<String> getTakenRaffleIds() throws SQLException {
        // return a list of all raffle IDS that are used in RaffleDetails
        return raffleGetter.getUsedRaffleIds();
    }

    /**
     * @param orgRaffleId The RaffleID of the OrganizerRaffle
     * @return ArrayList of details in format: [raffleName="raffle", numberOfWinners=2,
     * rules="this is a string of rules", endDate=LocalDate.of(2021, 12, 25), taskIds=ArrayList<String>,
     * ptcIds=ArrayList<String>, winnerIds=ArrayList<String>]
     */
    @Override
    public ArrayList<Object> getOrganizerRaffleById(String orgRaffleId) throws SQLException, ParseException {
        return raffleGetter.getDetails(orgRaffleId);
    }

    @Override
    public ArrayList<Object> getParticipantRaffleById(String ptcRaffleID) {
        return null;
    }

    @Override
    public ArrayList<String> getTaskById(String taskId) {
        return null;
    }

    @Override
    public ArrayList<String> getParticipantInfo(String ptcUsername) {
        return null;
    }

    @Override
    public ArrayList<String> getOrganizerInfo(String orgUsername) {
        return null;
    }

    @Override
    public ArrayList<String> getParticipantRaffleIds(String ptcUsername) {
        return null;
    }

    @Override
    public String getOrganizerRaffleIds(String orgUsername) {
        return null;
    }

    @Override
    public boolean hasCompletedTask(String ptcRaffleId, String userName, String taskId) {
        return false;
    }
}
