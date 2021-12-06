package main.java.DatabaseRe.Mediators.Adders;

import main.java.DatabaseRe.Mediators.Getters.UserGetter;
import main.java.DatabaseRe.Mediators.InsertQueries;
import main.java.DatabaseRe.TalkToDatabase.InsertUpdateQuery;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RaffleAdder {
    static UserGetter userGetter;
    static InsertUpdateQuery insertUpdateQuery = new InsertUpdateQuery();

    static {
        try {
            userGetter = new UserGetter();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RaffleAdder() throws SQLException {
    }

    public static void addParticipantsToRaffle(String orgRaffleId, ArrayList<String> partID) throws Exception {
        for (String ptcID: partID) {
            boolean userExists = userGetter.checkParticipantID(ptcID);
            if (userExists) {
                String query = InsertQueries.participantToRaffle(orgRaffleId, ptcID);
                insertUpdateQuery.run(query);
                insertUpdateQuery.close();
            }
            else {
                throw new Exception("Invalid Username " + ptcID + ". Please Check.");
            }
        }
    }


    public void addRaffleDetails(String orgRaffleId, String raffleName, Integer numberOfWinners,
                                 String raffleRules, LocalDate endDate)  {
        String query = InsertQueries.raffleDetails(orgRaffleId, raffleName, numberOfWinners, raffleRules, endDate);
        try {
            insertUpdateQuery.run(query);
            insertUpdateQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

