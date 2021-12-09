package main.java.UserComponent;

import main.java.DatabaseRe.AccessData;
import main.java.DatabaseRe.ProvideData;
import main.java.RaffleComponent.DataProviderPoint;

import javax.swing.*;
import java.sql.SQLException;

public class AddUserRaffleIdUseCase {

    /**
     * associates the raffleID of the raffle the participant joins into the database
     */
    public void addRaffleIdToPtc(String username, String raffleId) {
        try {
            DataProviderPoint pd = new ProvideData();
            pd.addRaffleIDtoParticipant(username, raffleId);
        } catch (SQLException sql) {
            sql.getCause();
        }

    }
}

