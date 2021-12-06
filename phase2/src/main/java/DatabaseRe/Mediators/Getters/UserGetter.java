package main.java.DatabaseRe.Mediators.Getters;

import main.java.DatabaseRe.Mediators.SelectQueries;
import main.java.DatabaseRe.Mediators.DataTools;
import main.java.DatabaseRe.TalkToDatabase.SelectQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class UserGetter {
    SelectQuery selectQuery = new SelectQuery();
    DataTools dataTools = new DataTools();

    public UserGetter() throws SQLException {
    }

    public ArrayList<String> getParticipantsInRaffle(String orgRaffleId) throws SQLException {
        String query = SelectQueries.getPTCInRafflesQuery(orgRaffleId);
        ResultSet results = selectQuery.getResultSet(query);
        ArrayList<String> puserID = dataTools.getStrings(results, "PuserID");
        results.close();
        selectQuery.close();
        return puserID;
    }


    public ArrayList<String> getUserInfo(String username, boolean organizer) throws SQLException, ParseException {
        String userID = null;
        try {
            userID = getUserID(username, organizer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String query = SelectQueries.getPTCInfoQuery(userID, organizer);
        ResultSet results = selectQuery.getResultSet(query);
        ArrayList<String> strings = DataTools.convertToString(DataTools.getRow(results));
        results.close();
        selectQuery.close();
        return strings;
    }

    public ArrayList<String> getRaffleIdsOfParticipant(String ptcUsername) throws SQLException {
        String ptcID = null;
        try {
            ptcID = getUserID(ptcUsername, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String query = SelectQueries.getRafflesOfParticipantsQuery(ptcID);
        ResultSet results = selectQuery.getResultSet(query);
        ArrayList<String> raffleID = dataTools.getStrings(results, "raffleID");
        results.close();
        selectQuery.close();
        return raffleID;
    }

    public ArrayList<String> getRafflesOfOrganizer(String orgUsername) throws SQLException {
        String orgID = null;
        try {
            orgID = getUserID(orgUsername, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String query = SelectQueries.getRafflesOfOrganizer(orgID);
        ResultSet results = selectQuery.getResultSet(query);
        ArrayList<String> raffleID = dataTools.getStrings(results, "raffleID");
        results.close();
        selectQuery.close();
        return raffleID;
    }


    public ArrayList<String> getUsedUserIDs(boolean organizer) throws SQLException {
        String query = SelectQueries.usedParticipantIdsQuery;
        String columnName = "PuserID";
        if (organizer) {
            query = SelectQueries.usedOrganizerIdsQuery;
            columnName = "OuserID";
        }
        ResultSet results = selectQuery.getResultSet(query);
        ArrayList<String> strings = dataTools.getStrings(results, columnName);
        results.close();
        selectQuery.close();
        return strings;
    }

    public String getUserID(String username, boolean organizer) throws Exception {
        String query = SelectQueries.getPTCID(username);
        if (organizer) { query = SelectQueries.getOrgID(username);}
        ResultSet results = selectQuery.getResultSet(query);
        String columnName = "PuserID";
        if (organizer) { columnName = "OuserID"; }
        try {
            String s = dataTools.getStrings(results, columnName).get(0);
            results.close();
            selectQuery.close();
            return s;
        }
        catch (IndexOutOfBoundsException e) {
            throw new Exception("The user does not exists");
        }
    }

    public boolean checkParticipantID(String userID) throws SQLException {
        String query = SelectQueries.getPusername(userID);
        ResultSet results = selectQuery.getResultSet(query);
        ArrayList<String> username = dataTools.getStrings(results, "username");
        results.close();
        selectQuery.close();
        return (!username.isEmpty());

    }


    public ArrayList<String> getOrgIDfromOrganization(String organization) throws SQLException {
        String query = SelectQueries.getOrgIDfromOrganization(organization);
        ResultSet resultSet = selectQuery.getResultSet(query);
        ArrayList<String> ouserID = dataTools.getStrings(resultSet, "OuserID");
        resultSet.close();
        selectQuery.close();
        return ouserID;
    }
}
