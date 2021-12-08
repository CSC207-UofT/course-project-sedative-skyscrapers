package main.java.UserComponent;

import main.java.DatabaseRe.AccessData;
import main.java.DatabaseRe.ProvideData;
import main.java.RaffleComponent.DataProviderPoint;

import javax.swing.*;
import java.sql.SQLException;

public class AddUserRaffleIdUseCase {

    public void addRaffleIdToPtc(String username, String raffleId){
        try {
            DataProviderPoint pd = new ProvideData();
//            AccessData accessData = new AccessData();
//            String ptcID = null;
//            try {
//                ptcID = accessData.getUserIDFromUsername(username, false);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            pd.addRaffleIDtoParticipant(username, raffleId);
        }catch(SQLException sql)
        {
            sql.getCause();
        }

    }
}

