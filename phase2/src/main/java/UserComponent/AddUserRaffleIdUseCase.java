package main.java.UserComponent;

import main.java.DatabaseRe.AccessData;
import main.java.DatabaseRe.DataAccessPoint;
import main.java.DatabaseRe.ProvideData;
import main.java.DatabaseRe.DataProviderPoint;

import java.sql.SQLException;

public class AddUserRaffleIdUseCase {

    /**
     * associates the raffleID of the raffle the participant joins into the database
     */
    public void addRaffleIdToPtc(String username, String raffleId) {
        try {
            DataProviderPoint pd = new ProvideData();
            DataAccessPoint ad = new AccessData();
            String ptcId = null;
            try {
                ptcId = ad.getUserIDFromUsername(username, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            pd.addRaffleIDtoParticipant(ptcId, raffleId);
        } catch (SQLException sql) {
            sql.getCause();
        }

    }
}

