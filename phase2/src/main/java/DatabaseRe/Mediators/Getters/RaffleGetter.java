package main.java.DatabaseRe.Mediators.Getters;

import main.java.DatabaseRe.Mediators.SelectQueries;
import main.java.DatabaseRe.Mediators.DataTools;
import main.java.DatabaseRe.TalkToDatabase.SelectQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class RaffleGetter {
    SelectQuery selectQuery = new SelectQuery();
    DataTools dataTools = new DataTools();


    public RaffleGetter() throws SQLException {
    }

    /** gets the details about the raffle through raffleID
     * @param orgRaffleId The OrganizerRaffleID
     * @return An array of strings containing raffle details in format: [raflleName, noOfWinners, rafflRules, endDate]
     */
    public ArrayList<Object> getDetails(String orgRaffleId) throws SQLException, ParseException {
        String query = SelectQueries.getDetailsQuery(orgRaffleId);
        ResultSet results = selectQuery.getResultSet(query);
        selectQuery.close();
        ArrayList<Object> row = DataTools.getRow(results);
        results.close();
        return row;
    }

    /** gets the raffleIDs that are already used by in the system
     * @return ArrayList of used raffleIDs
     */
    public ArrayList<String> getUsedRaffleIds() throws SQLException {
        ArrayList<String> usedIDs = new ArrayList<>();
        String query = SelectQueries.usedRaffleIDs;
        ResultSet results = selectQuery.getResultSet(query);
        selectQuery.close();
        while (results.next()) {
            usedIDs.add(results.getString("raffleID"));
        }
        results.close();
        return usedIDs;
    }

    public Object getWinners(String orgRaffleId) throws SQLException {
        String query = SelectQueries.getWinnersQuery(orgRaffleId);
        ResultSet results = selectQuery.getResultSet(query);
        ArrayList<String> puserID = dataTools.getStrings(results, "PuserID");
        results.close();
        selectQuery.close();
        return puserID;
    }

    public ArrayList<String> getTaskDetails(String taskId) throws SQLException, ParseException {
        String query = SelectQueries.getTaskQuery(taskId);
        ResultSet results = selectQuery.getResultSet(query);
        ArrayList<Object> raffleDetails = DataTools.getRow(results);
        results.close();
        selectQuery.close();
        return DataTools.convertToString(raffleDetails);
    }


    public Object getOrganizer(String orgRaffleId) throws SQLException {
        String query = SelectQueries.getOrganizerOfRaffle(orgRaffleId);
        ResultSet resultSet = selectQuery.getResultSet(query);
        ArrayList<String> ouserID = dataTools.getStrings(resultSet, "OuserID");
        resultSet.close();
        selectQuery.close();
        return ouserID;
    }

    public ArrayList<String> getRaffleIDfromRaffleName(String raffleName) throws SQLException {
        String query = SelectQueries.getRaffleIdFromName(raffleName);
        ResultSet resultSet = selectQuery.getResultSet(query);
        ArrayList<String> raffleID = dataTools.getStrings(resultSet, "raffleID");
        resultSet.close();
        selectQuery.close();
        return raffleID;
    }
}
