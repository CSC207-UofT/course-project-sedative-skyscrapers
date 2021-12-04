package main.java.DatabaseRe.Mediators.Getters;

import main.java.DatabaseRe.Mediators.Query;
import main.java.DatabaseRe.Mediators.QueryTools;
import main.java.DatabaseRe.TalkToDatabase.SelectQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class UserGetter {
    SelectQuery selectQuery = new SelectQuery();
    QueryTools queryTools = new QueryTools();

    public UserGetter() throws SQLException {
    }

    public ArrayList<String> getParticipantsInRaffle(String orgRaffleId) throws SQLException {
        String query = Query.getPTCInRafflesQuery(orgRaffleId);
        ResultSet results = selectQuery.getResultSet(query);
        return queryTools.getStrings(results, "PuserID");
    }


    public ArrayList<String> getUserInfo(String username, boolean organizer) throws SQLException, ParseException {
        String userID = getUserID(username, organizer);
        String query = Query.getPTCInfoQuery(userID, organizer);
        ResultSet results = selectQuery.getResultSet(query);
        return QueryTools.convertToString(QueryTools.getRow(results));
    }

    public ArrayList<String> getRaffleIdsOfParticipant(String ptcUsername) throws SQLException {
        String ptcID = getUserID(ptcUsername, false);
        String query = Query.getRafflesOfParticipantsQuery(ptcID);
        ResultSet results = selectQuery.getResultSet(query);
        return queryTools.getStrings(results, "raffleID");
    }

    public ArrayList<String> getRafflesOfOrganizer(String orgUsername) throws SQLException {
        String orgID = getUserID(orgUsername, true);
        String query = Query.getRafflesOfOrganizer(orgID);
        ResultSet results = selectQuery.getResultSet(query);
        return queryTools.getStrings(results, "raffleID");
    }


    public ArrayList<String> getUsedUserIDs(boolean organizer) throws SQLException {
        String query = Query.usedParticipantIdsQuery;
        String columnName = "PuserID";
        if (organizer) {
            query = Query.usedOrganizerIdsQuery;
            columnName = "OuserID";
        }
        ResultSet results = selectQuery.getResultSet(query);
        return queryTools.getStrings(results, columnName);
    }

    public String getUserID(String username, boolean organizer) throws SQLException {
        String query = Query.getPTCID(username);
        if (organizer) { query = Query.getOrgID(username);}
        ResultSet results = selectQuery.getResultSet(query);
        String columnName = "PuserID";
        if (organizer) { columnName = "OuserID"; }
        try {
            return queryTools.getStrings(results, columnName).get(0);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("The user does not exists");
        }
        return null;
    }


}
