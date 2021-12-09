package main.java.UserComponent;

import main.java.DatabaseRe.AccessData;

import java.sql.SQLException;
import java.util.ArrayList;

import main.java.DatabaseRe.DataAccessPoint;

/**
 * the class gets the raffeIDs of the raffles associated with the user through the database using dependency injection
 */
public class GetUserRaffleIdUseCase {
    private DataAccessPoint DataAccess;

    public GetUserRaffleIdUseCase() {
        try {
            this.DataAccess = new AccessData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * gets raffleIDs of the raffles the participants joined using participant username
     */
    public ArrayList<String> getPtcRaffleIdsFromDatabase(String username) {
        return this.DataAccess.getParticipantRaffleIds(username);
    }

    /**
     * gets raffleIDs of the raffles the organizer created
     */
    public String[] getOrgRafflesIdsFromDatabase(String username) {
        ArrayList<String> orgRafflesIdsFromDatabase = this.DataAccess.getOrganizerRaffleIds(username);
        int orgRaffleIdSize = orgRafflesIdsFromDatabase.size();
        String[] orgRaffleIds = new String[orgRaffleIdSize];
        for (int i = 0; i < orgRaffleIdSize; i++) {
            orgRaffleIds[i] = orgRafflesIdsFromDatabase.get(i);
        }
        return orgRaffleIds;
    }
}
