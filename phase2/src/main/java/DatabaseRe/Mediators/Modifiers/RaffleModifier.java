package main.java.DatabaseRe.Mediators.Modifiers;

import main.java.DatabaseRe.TalkToDatabase.InsertUpdateQuery;
import main.java.DatabaseRe.UpdateQueries;

import java.sql.SQLException;
import java.time.LocalDate;

public class RaffleModifier {

    public void raffleRules(String raffleId, String newRaffleRules) throws SQLException {
        String query = UpdateQueries.raffleRules(raffleId, newRaffleRules);
        InsertUpdateQuery.run(query);
    }

    public void changeEndDate(String raffleID, LocalDate newData) {
        String query = UpdateQueries.raffleEndDate(raffleID, newData);
        try {
            InsertUpdateQuery.run(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
