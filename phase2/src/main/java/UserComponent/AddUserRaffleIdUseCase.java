package main.java.UserComponent;

import main.java.DatabaseRe.ProvideData;
import main.java.RaffleComponent.DataProviderPoint;

import javax.swing.*;
import java.sql.SQLException;

public class AddUserRaffleIdUseCase {

    public void addRaffleIdToPtc(String username, String raffleId){
        try {
            DataProviderPoint pd = new ProvideData();
            pd.addRaffleIDtoParticipant(username, raffleId);
        }catch(SQLException sql)
        {
            sql.getCause();
        }

    }
}
