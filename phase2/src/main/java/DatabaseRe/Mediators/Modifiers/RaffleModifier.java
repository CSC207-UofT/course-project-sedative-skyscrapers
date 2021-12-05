package main.java.DatabaseRe.Mediators.Modifiers;

import main.java.DatabaseRe.TalkToDatabase.InsertUpdateQuery;
import main.java.DatabaseRe.UpdateQueries;

import java.sql.SQLException;

public class RaffleModifier {

    public void raffleRules(String raffleId, String newRaffleRules) throws SQLException {
        String query = UpdateQueries.raffleRules(raffleId, newRaffleRules);
        InsertUpdateQuery.run(query);
    }
}
