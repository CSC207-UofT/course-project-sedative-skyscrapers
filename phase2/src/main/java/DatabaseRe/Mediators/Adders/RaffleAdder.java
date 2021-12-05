package main.java.DatabaseRe.Mediators.Adders;

import main.java.DatabaseRe.Mediators.Getters.UserGetter;
import main.java.DatabaseRe.Mediators.InsertQueries;
import main.java.DatabaseRe.TalkToDatabase.InsertUpdateQuery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class RaffleAdder {
    static UserGetter userGetter;

    static {
        try {
            userGetter = new UserGetter();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RaffleAdder() throws SQLException {
    }

    public static void addParticipantsToRaffle(String orgRaffleId, ArrayList<String> ptcIDs) throws Exception {
        for (String ptcID: ptcIDs) {
            boolean userExists = userGetter.checkParticipantID(ptcID);
            if (userExists) {
                String query = InsertQueries.participantToRaffle(orgRaffleId, ptcID);
                InsertUpdateQuery.run(query);
            }
            else {
                throw new Exception("Invalid Username " + ptcID + ". Please Check.");
            }
        }
    }


    public void addRaffleDetails(String orgRaffleId, String raffleName, Integer numberOfWinners,
                                 String raffleRules, Date endDate) throws SQLException {
        String query = InsertQueries.raffleDetails(orgRaffleId, raffleName, numberOfWinners, raffleRules, endDate);
        InsertUpdateQuery.run(query);
    }
}

