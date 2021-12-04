package main.java.DatabaseRe.Mediators.Getters;

import main.java.DatabaseRe.Mediators.Query;
import main.java.DatabaseRe.Mediators.QueryTools;
import main.java.DatabaseRe.TalkToDatabase.SelectQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class RaffleGetter {
    SelectQuery selectQuery = new SelectQuery();
    QueryTools queryTools = new QueryTools();


    public RaffleGetter() throws SQLException {
    }

    /** gets the details about the raffle through raffleID
     * @param orgRaffleId The OrganizerRaffleID
     * @return An array of strings containing raffle details in format: [raflleName, noOfWinners, rafflRules, endDate]
     */
    public ArrayList<Object> getDetails(String orgRaffleId) throws SQLException, ParseException {
        String query = Query.getDetailsQuery(orgRaffleId);
        ResultSet results = selectQuery.getResultSet(query);
        return (QueryTools.getRow(results));
    }

    /** gets the raffleIDs that are already used by in the system
     * @return ArrayList of used raffleIDs
     */
    public ArrayList<String> getUsedRaffleIds() throws SQLException {
        ArrayList<String> usedIDs = new ArrayList<>();
        String query = Query.usedRaffleIDs;
        ResultSet results = selectQuery.getResultSet(query);

        while (results.next()) {
            usedIDs.add(results.getString("raffleID"));
        }

        return usedIDs;
    }

    public Object getWinners(String orgRaffleId) throws SQLException {
        String query = Query.getWinnersQuery(orgRaffleId);
        ResultSet results = selectQuery.getResultSet(query);
        return queryTools.getStrings(results, "PuserID");
    }

    public ArrayList<String> getTaskDetails(String taskId) throws SQLException, ParseException {
        String query = Query.getTaskQuery(taskId);
        ResultSet results = selectQuery.getResultSet(query);
        ArrayList<Object> raffleDetails = QueryTools.getRow(results);
        return QueryTools.convertToString(raffleDetails);
    }


}
