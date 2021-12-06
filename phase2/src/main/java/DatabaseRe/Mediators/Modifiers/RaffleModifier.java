package main.java.DatabaseRe.Mediators.Modifiers;

import main.java.DatabaseRe.TalkToDatabase.InsertUpdateQuery;
import main.java.DatabaseRe.UpdateQueries;

import java.sql.SQLException;
import java.time.LocalDate;

public class RaffleModifier {
    static InsertUpdateQuery insertUpdateQuery = new InsertUpdateQuery();
    public void raffleRules(String raffleId, String newRaffleRules) throws SQLException {
        String query = UpdateQueries.raffleRules(raffleId, newRaffleRules);
        insertUpdateQuery.run(query);
        insertUpdateQuery.close();
    }

    public void changeEndDate(String raffleID, LocalDate newData) {
        String query = UpdateQueries.raffleEndDate(raffleID, newData);
        try {
            insertUpdateQuery.run(query);
            insertUpdateQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
